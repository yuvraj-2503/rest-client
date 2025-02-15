package com.yuvraj.restclient;

import lombok.Getter;

/**
 * @author Yuvraj
 */
@Getter
public class QueryParam {
    private final String key;
    private final String value;
    private final String EQUALS = "=";

    public QueryParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String join() {
        return this.key + this.EQUALS + this.value;
    }
}
