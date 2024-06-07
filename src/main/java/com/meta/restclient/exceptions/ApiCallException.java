package com.meta.restclient.exceptions;

public class ApiCallException extends RuntimeException {
    private final String subMessage;

    public ApiCallException(String message, String subMessage) {
        super(message);
        this.subMessage = subMessage;
    }

    public ApiCallException(String message, Throwable cause, String subMessage) {
        super(message, cause);
        this.subMessage = subMessage;
    }

    public String getSubMessage() {
        return this.subMessage;
    }

    public String getMessage() {
        return super.getMessage() + ", Details : " + this.subMessage;
    }
}
