package com.yuvraj.restclient;

import com.yuvraj.util.preconditions.CheckGroup;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Yuvraj
 */
@Getter
public class Url {
    private String url;
    @Setter
    private QueryParams queryParams;
    private final String SEPARATOR = "/";
    private final String QUESTION = "?";

    public Url(String baseUrl, String... pathParams) {
        this.url = baseUrl;
        if (!CheckGroup.nullOrEmpty(pathParams)) {
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
            return url + "?" + this.queryParams.join();
        } else {
            return this.url;
        }
    }
}
