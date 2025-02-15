package com.yuvraj.restclient;

import lombok.Getter;

/**
 * @author Yuvraj
 */
@Getter
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
}
