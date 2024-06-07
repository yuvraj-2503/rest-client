package com.meta.restclient.okhttp;

public class HttpResponse {
    private final int statusCode;
    private final String payload;

    public HttpResponse(int statusCode, String payload) {
        this.statusCode = statusCode;
        this.payload = payload;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getPayload() {
        return this.payload;
    }
}

