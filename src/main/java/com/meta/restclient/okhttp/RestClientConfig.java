package com.meta.restclient.okhttp;

public class RestClientConfig {
    private static final RestClientConfig instance = new RestClientConfig();
    private boolean debug = false;

    private RestClientConfig() {
    }

    public static RestClientConfig getInstance() {
        return instance;
    }

    boolean isDebug() {
        return this.debug;
    }

    public void debug() {
        this.debug = true;
    }
}
