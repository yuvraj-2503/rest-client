package com.meta.restclient.okhttp;

public class QueryParam {
    private final String key;
    private final String value;
    private static final String EQUALS = "=";

    public QueryParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public String join() {
        return this.key + "=" + this.value;
    }
}

