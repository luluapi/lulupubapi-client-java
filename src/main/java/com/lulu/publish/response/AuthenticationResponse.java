package com.lulu.publish.response;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Represents a response from the authentication endpoint.
 */
public class AuthenticationResponse {

    @JsonProperty("authenticated")
    private boolean authenticated;
    @JsonProperty("message")
    private String message;
    @JsonProperty("authToken")
    private String authToken;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{"
                + "authenticated=" + authenticated
                + ", message='" + message + '\''
                + ", authToken='" + authToken + '\''
                + '}';
    }
}
