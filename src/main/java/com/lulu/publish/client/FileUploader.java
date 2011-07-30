package com.lulu.publish.client;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lulu.publish.web.ProtocolRegistrar;

/**
 * Handles Multipart-file uploads for the Publish API.
 */
public class FileUploader {

    private static final Logger LOG = LoggerFactory.getLogger(FileUploader.class); // NOPMD

    private String authenticationToken;
    private String apiKey;
    private HttpClient httpClient;

    /**
     * Construct with given credentials.
     *
     * @param uploadToken         token retrieved indicating upload has been authorized
     * @param authenticationToken login session token
     * @throws IOException if SSL context could not be initialized
     */
    public FileUploader(String authenticationToken, String apiKey) throws IOException {
        this.authenticationToken = authenticationToken;
        this.apiKey = apiKey;

        httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        ProtocolRegistrar.registerTrustAllSslContextWithHttpClient(httpClient);
    }

    /**
     * Upload the given files to the target URL using Multipart-file upload.
     *
     * @param targetUrl url to which to upload
     * @param targetFiles files to upload
     * @return true if upload is successful
     * @throws IOException if upload fails because of a bad target URL or missing input files
     */
    public String upload(String targetUrl, File file) throws IOException {
    	String fullURL = targetUrl
                         + "?auth_token=" + URLEncoder.encode(authenticationToken, "UTF-8")
                         + "&api_key=" + URLEncoder.encode(apiKey, "UTF-8");
        HttpPost httpPost = new HttpPost(fullURL);
        MultipartEntity multipartEntity = new MultipartEntity();
        if (LOG.isInfoEnabled()) {
            LOG.info("Uploading " + file.getName() + " to " + fullURL);
        }
        ContentBody fileBody = new FileBody(file);
        multipartEntity.addPart(file.getName(), fileBody);

        httpPost.setEntity(multipartEntity);
        httpPost.setHeader("Accept", "application/json");
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();
        String output = "";
        if (responseEntity != null) {
            output = EntityUtils.toString(responseEntity);
            responseEntity.consumeContent();
        }
        httpClient.getConnectionManager().shutdown();
        return output;
    }

}
