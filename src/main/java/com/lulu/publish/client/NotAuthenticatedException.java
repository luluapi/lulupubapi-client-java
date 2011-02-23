package com.lulu.publish.client;

/**
 * Exception thrown when API calls are attempted without authenticating.
 */
public class NotAuthenticatedException extends PublishApiException {

    /**
     * Construct without details.
     */
    public NotAuthenticatedException() {
    }

    /**
     * Construct with cause.
     *
     * @param cause cause
     */
    public NotAuthenticatedException(Throwable cause) {
        super(cause);
    }

    /**
     * Construct with message.
     *
     * @param message message
     */
    public NotAuthenticatedException(String message) {
        super(message);
    }

    /**
     * Construct with message and cause.
     *
     * @param message message
     * @param cause   cause
     */
    public NotAuthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
