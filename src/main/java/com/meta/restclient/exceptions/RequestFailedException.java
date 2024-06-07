package com.meta.restclient.exceptions;

import com.meta.restclient.okhttp.HttpResponse;

public class RequestFailedException extends RuntimeException{
    private final HttpResponse response;

    public RequestFailedException(String message, HttpResponse response) {
        super(message);
        this.response = response;
    }

    public RequestFailedException(String message, Throwable cause, HttpResponse response) {
        super(message, cause);
        this.response = response;
    }

    public HttpResponse getResponse() {
        return response;
    }
}
