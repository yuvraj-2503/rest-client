package com.yuvraj.restclient;

import com.yuvraj.util.json.Json;

/**
 * @author Yuvraj
 */
public class BodyRequest extends Request {
    private final String body;
    private static final Json json = Json.create();

    public BodyRequest(Url url) {
        super(url);
        this.body = null;
    }

    public BodyRequest(Url url, Headers headers) {
        super(url, headers);
        this.body = null;
    }

    public BodyRequest(Url url, String body) {
        super(url);
        this.body = body;
    }

    public BodyRequest(Url url, Headers headers, String body) {
        super(url, headers);
        this.body = body;
    }

    public BodyRequest(Url url, Object body) {
        this(url, json.encode(body));
    }

    public BodyRequest(Url url, Headers headers, Object body) {
        this(url, headers, json.encode(body));
    }

    public String getBody() {
        return this.body;
    }
}

