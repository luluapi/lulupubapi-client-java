package com.lulu.publish.response;

import java.net.HttpURLConnection;

/**
 * @param <T> The type of payload that is expected to be held in the response.
 */
public class ApiResponse<T> {

    private int httpStatusCode;
    private T payload;

    public ApiResponse(int httpStatusCode, T payload) {
        this.httpStatusCode = httpStatusCode;
        this.payload = payload;
    }

    public boolean isError() {
        return httpStatusCode != HttpURLConnection.HTTP_OK;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public T getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "ApiResponse{"
                + "httpStatusCode=" + httpStatusCode
                + ", payload=" + payload
                + '}';
    }
}
