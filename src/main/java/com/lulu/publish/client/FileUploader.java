package com.lulu.publish.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lulu.publish.web.ProtocolRegistrar;

/**
 * Handles Multipart-file uploads for the Publish API.
 */
public class FileUploader {

    private static final Logger LOG = LoggerFactory.getLogger(FileUploader.class); // NOPMD

    private String authenticationToken;
    private String uploadToken;

    /**
     * Construct with given credentials.
     *
     * @param uploadToken         token retrieved indicating upload has been authorized
     * @param authenticationToken login session token
     */
    public FileUploader(String uploadToken, String authenticationToken) {
        this.authenticationToken = authenticationToken;
        this.uploadToken = uploadToken;

        ProtocolRegistrar.registerTrustAllProtocolSocketFactory();
    }

    /**
     * Upload the given files to the target URL using Multipart-file upload.
     *
     * @param targetUrl url to which to upload
     * @param targetFiles files to upload
     * @return true if upload is successful
     * @throws IOException if upload fails because of a bad target URL or missing input files
     */
    public boolean upload(String targetUrl, File... targetFiles) throws IOException {
        boolean result = false;

        PostMethod filePost = new PostMethod(targetUrl);
        filePost.getParams().setBooleanParameter(HttpMethodParams.USE_EXPECT_CONTINUE, false);
        Collection<Part> partCollection = new ArrayList<Part>();
        partCollection.add(new StringPart("auth_token", authenticationToken));
        partCollection.add(new StringPart("upload_token", uploadToken));
        for (File targetFile : targetFiles) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Uploading " + targetFile.getName() + " to " + targetUrl);
            }
            partCollection.add(new FilePart(targetFile.getName(), targetFile));
        }
        filePost.setRequestEntity(new MultipartRequestEntity(partCollection.toArray(new Part[partCollection.size()]), filePost.getParams()));

        HttpClient client = new HttpClient();

        int status = client.executeMethod(filePost);
        if (status == HttpStatus.SC_OK) {
            result = true;
        }
        filePost.releaseConnection();
        return result;
    }
}
