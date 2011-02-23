package com.lulu.publish.client;

/**
 * Generic exception used when a more specific error cannot be identified.
 */
public class PublishApiException extends Exception {

    /**
     * Constructs with a null message.
     */
    public PublishApiException() {
    }

    /**
     * Constructs with the given cause.
     *
     * @param cause exception cause
     */
    public PublishApiException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs with the given detail message.
     *
     * @param message error details
     */
    public PublishApiException(String message) {
        super(message);
    }

    /**
     * Constructs with the given detail message and cause.
     *
     * @param message error details
     * @param cause   exception cause
     */
    public PublishApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
