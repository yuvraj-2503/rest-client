package com.meta.restclient.okhttp;

public class Request {
    private final Url url;
    private final Headers headers;

    public Request(Url url) {
        this.url = url;
        this.headers = null;
    }

    public Request(Url url, Headers headers) {
        this.url = url;
        this.headers = headers;
    }

    public Url getUrl() {
        return url;
    }

    public Headers getHeaders() {
        return headers;
    }
}
