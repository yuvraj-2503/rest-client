package com.meta.restclient.okhttp;

public class Url {
    private String url;
    private QueryParams queryParams;
    private static final String SEPARATOR = "/";
    private static final String QUESTION = "?";

    public String getUrl() {
        return this.url;
    }

    public QueryParams getQueryParams() {
        return this.queryParams;
    }

    public void setQueryParams(QueryParams queryParams) {
        this.queryParams = queryParams;
    }

    public Url(String baseUrl, String... pathParams) {
        this.url = baseUrl;
        if (pathParams != null && pathParams.length > 0) {
            String relative = String.join("/", pathParams);
            this.url = this.url + "/" + relative;
        }
    }

    public Url queryParam(String key, String value) {
        if (this.queryParams == null) {
            this.queryParams = new QueryParams();
        }

        this.queryParams.add(key, value);
        return this;
    }

    public String getAsString() {
        if (this.queryParams != null) {
            return this.url + "?" + this.queryParams.join();
        } else {
            return this.url;
        }
    }
}
