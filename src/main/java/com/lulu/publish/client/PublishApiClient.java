package com.lulu.publish.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lulu.publish.model.FileContext;
import com.lulu.publish.model.ConversionManifest;
import com.lulu.publish.model.ConversionStatus;
import com.lulu.publish.model.Project;
import com.lulu.publish.response.ApiResponse;
import com.lulu.publish.response.AuthenticationResponse;
import com.lulu.publish.response.BaseCostResponse;
import com.lulu.publish.response.ConversionResponse;
import com.lulu.publish.response.CreateResponse;
import com.lulu.publish.response.ErrorResponse;
import com.lulu.publish.response.ListResponse;
import com.lulu.publish.response.ProjectResponse;
import com.lulu.publish.response.UploadTokenResponse;
import com.lulu.publish.util.JsonMapper;
import com.lulu.publish.util.JsonMapperException;
import com.lulu.publish.util.StreamUtils;
import com.lulu.publish.web.ProtocolRegistrar;

/**
 * Provides a client interface to the Lulu publish API.
 */
public class PublishApiClient {

    private static final Logger LOG = LoggerFactory.getLogger(PublishApiClient.class); // NOPMD

    private static final int TRANSFER_BLOCK_SIZE = 1 << 24; // For downloads, arbitrary block size

    private String apiKey;
    private String email;
    private String password;
    private String authenticationEndpoint = "https://www.lulu.com/account/endpoints/authenticator.php";
    private String apiUrlTemplate = "https://apps.lulu.com/api/publish/v1/%s";
    private String apiUploadUrl = "https://pubapp.lulu.com/api/publish/v1/upload";
    private String authenticationToken;
    
    private ErrorResponse error;
    
    /**
     * Construct the client with the default configuration file.
     * <p/>
     * This file is "publishapi.properties" in the user's home directory.
     *
     * @throws IOException if a parseable properties file does not exist in the home directory
     */
    public PublishApiClient() throws IOException {
        this(new PublishApiConfiguration());
    }

    /**
     * Construct with the specified configuration file.
     *
     * @param configFile properties file
     * @throws IOException if the properties file could not be located or parsed
     */
    public PublishApiClient(String configFile) throws IOException {
        this(new PublishApiConfiguration(configFile));
    }

    /**
     * Construct with the given configuration.
     * <p/>
     * Also sets up SSL to accept <b>all</b> certificates, even self-signed. This is not recommended for production use.
     *
     * @param configuration holder for configuration values
     */
    public PublishApiClient(PublishApiConfiguration configuration) {
        LOG.info(configuration.toString());
        apiKey = configuration.getApiKey();
        email = configuration.getEmail();
        password = configuration.getPassword();

        ProtocolRegistrar.registerTrustAllProtocolSocketFactory();
    }

    /**
     * Authenticate using the credentials found in the properties file with which client was constructed.
     *
     * @return whether authentication was successful
     * @throws PublishApiException when an unexpected error occurs
     */
    public boolean login() throws PublishApiException {
        return login(email, password);
    }

    /**
     * Authenticate with the provided credentials.
     *
     * @param email    lulu account email address
     * @param password lulu account password
     * @return whether authentication was successful
     * @throws PublishApiException when an unexpected error occurs
     */
    public boolean login(String email, String password) throws PublishApiException {
        ApiResponse response = performApiCall(authenticationEndpoint,
                generateParameters("username", email, "password", password, "responseType", "json"),
                AuthenticationResponse.class);

        if (!response.isError()) {
            AuthenticationResponse payload = (AuthenticationResponse) response.getPayload();
            LOG.info(payload.toString());
            if (payload.isAuthenticated()) {
                authenticationToken = payload.getAuthToken();
                return true;
            }
        }
        return false;
    }

    public ErrorResponse getError() {
        return error;
    }

    /**
     * Create a new project using the provided data.
     *
     * @param project project to create
     * @return the project with content id set
     * @throws NotAuthenticatedException if not logged in
     * @throws PublishApiException       when an unexpected error occurs
     */
    public Project create(Project project) throws PublishApiException {
        assertAuthenticated();

        ApiResponse response = performApiCall(String.format(apiUrlTemplate, "create"),
                generateParameters("api_key", apiKey, "auth_token", authenticationToken, "project", serializeProject(project)),
                CreateResponse.class);

        if (!response.isError()) {
            project.setContentId(((CreateResponse) response.getPayload()).getContentId());
            return project;
        }
        error = (ErrorResponse) response.getPayload();
        throw new PublishApiException("Failed to create the project:  " + error.getErrorValue());
    }

    /**
     * Update the provided project.
     * <p/>
     * Can perform delta updates. At a minimum, the content id must be supplied.
     *
     * @param project project to update
     * @return the project after update
     * @throws NotAuthenticatedException if not logged in
     * @throws PublishApiException       when an unexpected error occurs
     */
    public Project update(Project project) throws PublishApiException {
        assertAuthenticated();

        ApiResponse response = performApiCall(String.format(apiUrlTemplate, "update"),
                generateParameters("api_key", apiKey, "auth_token", authenticationToken, "project", serializeProject(project)),
                ProjectResponse.class);

        if (!response.isError()) {
            return ((ProjectResponse) response.getPayload()).getProject();
        }
        error = (ErrorResponse) response.getPayload();
        throw new PublishApiException("Failed to update the project.");
    }

    private String serializeProject(Project project) throws PublishApiException {
        String projectJson;
        try {
            projectJson = JsonMapper.toJson(project);
        } catch (JsonMapperException e) {
            throw new PublishApiException("Could not serialize project.", e);
        }
        return projectJson;
    }

    /**
     * Get the base cost for a project with the given specifications.
     *
     * @param project   project specification - needs physical attributes (books) or drm (ebooks)
     * @param pageCount number of pages (books)
     * @return base cost response
     * @throws NotAuthenticatedException if not logged in
     * @throws PublishApiException       when an unexpected error occurs
     */
    public BaseCostResponse baseCost(Project project, int pageCount) throws PublishApiException {
        assertAuthenticated();

        ApiResponse response = performApiCall(String.format(apiUrlTemplate, "base_cost"),
                generateParameters("api_key", apiKey, "auth_token", authenticationToken,
                        "project", serializeProject(project), "page_count", Integer.toString(pageCount)),
                BaseCostResponse.class);

        if (!response.isError()) {
            return (BaseCostResponse) response.getPayload();
        }
        error = (ErrorResponse) response.getPayload();
        throw new PublishApiException("Failed to get base cost.");
    }

    /**
     * Returns the project with the provided id.
     *
     * @param contentId project identifier
     * @return project
     * @throws NotAuthenticatedException if not logged in
     * @throws PublishApiException       when an unexpected error occurs
     */
    public Project read(int contentId) throws PublishApiException {
        assertAuthenticated();

        ApiResponse response = performApiCall(String.format(apiUrlTemplate, "read/id/").concat(Integer.toString(contentId)),
                generateParameters("api_key", apiKey, "auth_token", authenticationToken),
                ProjectResponse.class);

        if (!response.isError()) {
            return ((ProjectResponse) response.getPayload()).getProject();
        }
        error = (ErrorResponse) response.getPayload();
        throw new PublishApiException("Failed to read the project.");
    }

    /**
     * Returns a list of the projects owned by the logged in user.
     *
     * @return list of project identifiers
     * @throws NotAuthenticatedException if not logged in
     * @throws PublishApiException       when unexpected error occurs
     */
    public List<Integer> list() throws PublishApiException {
        assertAuthenticated();

        ApiResponse response = performApiCall(String.format(apiUrlTemplate, "list"),
                generateParameters("api_key", apiKey, "auth_token", authenticationToken),
                ListResponse.class);

        if (response.isError()) {
            error = (ErrorResponse) response.getPayload();
            return new ArrayList<Integer>();
        } else {
            return ((ListResponse) response.getPayload()).getContentIds();
        }
    }

    /**
     * Deletes the specified project.
     *
     * @param contentId project identifier
     * @throws NotAuthenticatedException if not logged in
     * @throws PublishApiException       when unexpected error occurs
     */
    public void delete(int contentId) throws PublishApiException {
        assertAuthenticated();

        ApiResponse response = performApiCall(String.format(apiUrlTemplate, "delete/id/").concat(Integer.toString(contentId)),
                generateParameters("api_key", apiKey, "auth_token", authenticationToken),
                List.class);

        if (response.isError()) {
            error = (ErrorResponse) response.getPayload();
            throw new PublishApiException("Failed to delete the project.");
        }
    }

    /**
     * Upload the provided files for use in a project.
     *
     * @param files collection of files
     * @throws FileNotFoundException if attempt is made to upload a non-existent file
     * @throws PublishApiException   if an unexpected error occurs
     */
    public void upload(Collection<File> files) throws PublishApiException, FileNotFoundException {
        String uploadToken = requestUploadToken();
        try {
            performUpload(files, uploadToken);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw new PublishApiException("Upload failed unexpectedly.", e);
        }
    }

    private void performUpload(Collection<File> files, String uploadToken) throws IOException {
        FileUploader uploader = new FileUploader(uploadToken, authenticationToken);
        uploader.upload(apiUploadUrl, files.toArray(new File[files.size()]));
    }

    private String requestUploadToken() throws PublishApiException {
        assertAuthenticated();

        ApiResponse response = performApiCall(String.format(apiUrlTemplate, "request_upload_token"),
                generateParameters("api_key", apiKey, "auth_token", authenticationToken),
                UploadTokenResponse.class);

        if (response.isError()) {
            error = (ErrorResponse) response.getPayload();
            throw new PublishApiException("Failed to obtain file upload token.");
        }
        return ((UploadTokenResponse) response.getPayload()).getToken();
    }

    /**
     * Download the indicated project file.
     *
     * @param contentId      project identifier
     * @param fileContext    which file to download
     * @param outputLocation where to put the file
     * @throws PublishApiException if an unexpected error occurs
     */
    public void download(int contentId, FileContext fileContext, File outputLocation) throws PublishApiException {
        assertAuthenticated();

        String downloadUrl = String.format(apiUrlTemplate,
                "download/id/".concat(Integer.toString(contentId))
                        .concat("/what/").concat(fileContext.toString())
                        .concat("?api_key=").concat(apiKey)
                        .concat("&auth_token=").concat(authenticationToken));
        if (LOG.isInfoEnabled()) {
            LOG.info("API call to <" + downloadUrl + ">");
        }
        try {
            HttpClient httpClient = new HttpClient();
            GetMethod getMethod = new GetMethod(downloadUrl);
            int responseCode = httpClient.executeMethod(getMethod);
            if (responseCode == HttpStatus.SC_OK) {
                ReadableByteChannel rbc = Channels.newChannel(getMethod.getResponseBodyAsStream());
                FileOutputStream fos = new FileOutputStream(outputLocation);
                fos.getChannel().transferFrom(rbc, 0, TRANSFER_BLOCK_SIZE);
            } else {
                LOG.info("Download failed with response: " + getMethod.getResponseBodyAsString());
                throw new PublishApiException("Bad response code returned attempting to download file: " + responseCode);
            }
        } catch (IOException e) {
            throw new PublishApiException("Unable to download indicated file.", e);
        }
    }
    
    /**
     * Converts source files
     * 
     * @param manifest the manifest for conversion
     * @return the id of conversion
     * @throws PublishApiException if an unexpected error occurs
     */
    public Long convert(ConversionManifest manifest) throws PublishApiException {
        assertAuthenticated();
        
        String call = String.format(apiUrlTemplate, "conversion").concat("?api_key=").concat(apiKey).concat("&auth_token=").concat(authenticationToken);
        if (LOG.isInfoEnabled()) {
            LOG.info("API call to <".concat(call).concat(">"));
        }
        
        PostMethod postMethod = new PostMethod(call);
        postMethod.addRequestHeader(new Header("Content-Type", "application/json"));
        postMethod.addRequestHeader(new Header("Accept", "application/json"));
        postMethod.setRequestBody(serializeManifest(manifest));
        
        HttpClient client = new HttpClient();
        String output = "";
        ConversionResponse response;
        try {
            int returnCode = client.executeMethod(postMethod);
            output = StreamUtils.inputStreamToString(postMethod.getResponseBodyAsStream());
            if (LOG.isInfoEnabled()) {
                LOG.info("API call response: " + output);
            }
            response = JsonMapper.fromJson(output, ConversionResponse.class);
            if (returnCode == HttpsURLConnection.HTTP_OK) { 
                return response.getConversion().getConversionId();
            } else {
                throw new PublishApiException("Failed to convert the files of the project:  " + response.getDetails());
            }

        } catch (IOException e) {
            throw new PublishApiException("Unexpected error calling API endpoint.", e);
        } catch (JsonMapperException e) {
            throw new PublishApiException("API endpoint returned unparseable response: " + output, e);
        } finally {
            postMethod.releaseConnection();
        }
        
    }
    
    private String serializeManifest(ConversionManifest manifest) throws PublishApiException {
        String manifestJson;
        try {
            manifestJson = JsonMapper.toJson(manifest);
        } catch (JsonMapperException e) {
            throw new PublishApiException("Could not serialize manifest.", e);
        }
        return manifestJson;
    }
    
    /**
     * Determines status of conversion
     * 
     * @param jobId the id of conversion
     * @return status
     * @throws PublishApiException if an unexpected error occurs
     */
    public ConversionStatus convertStatus(Long jobId) throws PublishApiException {
        
        String call = String.format(apiUrlTemplate, String.format("conversion/%s", String.valueOf(jobId))).concat("?api_key=").concat(apiKey).concat(
                "&auth_token=").concat(authenticationToken);
        
        if (LOG.isInfoEnabled()) {
            LOG.info("API call to <".concat(call).concat(">"));
        }

        GetMethod getMethod = new GetMethod(call);
        getMethod.addRequestHeader(new Header("Accept", "application/json"));
        HttpClient client = new HttpClient();
        String output = "";
        ConversionResponse response;
        try {
            int returnCode = client.executeMethod(getMethod);
            output = StreamUtils.inputStreamToString(getMethod.getResponseBodyAsStream());
            if (LOG.isInfoEnabled()) {
                LOG.info("API call response: " + output);
            }
            response = JsonMapper.fromJson(output, ConversionResponse.class);
            
            if (returnCode == HttpsURLConnection.HTTP_OK) { 
                return response.getConversion().getStatus();
            } else {
                throw new PublishApiException("Failed to get the convertation status:  " + response.getDetails());
            }
        } catch (IOException e) {
            throw new PublishApiException("Unexpected error calling API endpoint.", e);
        } catch (JsonMapperException e) {
            throw new PublishApiException("API endpoint returned unparseable response: " + output, e);
        } finally {
            getMethod.releaseConnection();
        }
        
    }

    private void assertAuthenticated() throws NotAuthenticatedException {
        if (authenticationToken == null || authenticationToken.length() == 0) {
            throw new NotAuthenticatedException("Attempted perform action without authenticating.");
        }
    }

    private NameValuePair[] generateParameters(String... keysAndValues) {
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        for (int index = 0; index < keysAndValues.length; index += 2) {
            parameters.add(new NameValuePair(keysAndValues[index], keysAndValues[index + 1]));
        }
        return parameters.toArray(new NameValuePair[parameters.size()]);
    }

    private <T> ApiResponse performApiCall(String call, NameValuePair[] urlParameters, Class<T> clazz) throws PublishApiException {
        if (LOG.isInfoEnabled()) {
            LOG.info("API call to <".concat(call).concat(">"));
        }

        PostMethod postMethod = new PostMethod(call);
        postMethod.addParameters(urlParameters);

        HttpClient client = new HttpClient();
        ApiResponse response;
        String output = "";
        try {
            int returnCode = client.executeMethod(postMethod);
            if (returnCode == HttpsURLConnection.HTTP_INTERNAL_ERROR) {
                output = StreamUtils.inputStreamToString(postMethod.getResponseBodyAsStream());
                response = new ApiResponse<ErrorResponse>(returnCode, JsonMapper.fromJson(output, ErrorResponse.class));
            } else {
                output = StreamUtils.inputStreamToString(postMethod.getResponseBodyAsStream());
                response = new ApiResponse<T>(returnCode, JsonMapper.fromJson(output, clazz));
            }
            if (LOG.isInfoEnabled()) {
                LOG.info("API call response: " + output);
            }
            return response;
        } catch (IOException e) {
            throw new PublishApiException("Unexpected error calling API endpoint.", e);
        } catch (JsonMapperException e) {
            throw new PublishApiException("API endpoint returned unparseable response: " + output, e);
        } finally {
            postMethod.releaseConnection();
        }
    }

}
