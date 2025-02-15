package com.yuvraj.restclient;

import lombok.Getter;

/**
 * @author Yuvraj
 */
public class RestClientConfig {
    @Getter
    private static final RestClientConfig instance = new RestClientConfig();
    private boolean debug = false;

    private RestClientConfig() {
    }

    boolean isDebug() {
        return this.debug;
    }

    public void debug() {
        this.debug = true;
    }
}

