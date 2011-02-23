package com.lulu.publish.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class UploadTokenResponse {

    private static final Logger LOG = LoggerFactory.getLogger(UploadTokenResponse.class); // NOPMD

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UploadTokenResponse{"
                + "token='" + token + '\''
                + '}';
    }
}
