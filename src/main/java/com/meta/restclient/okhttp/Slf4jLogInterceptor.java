package com.meta.restclient.okhttp;

import okhttp3.*;
import okhttp3.Request;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Slf4jLogInterceptor implements Interceptor {
    private static final Logger log = LoggerFactory.getLogger(OkHttpRestClientAsync.class);

    public Slf4jLogInterceptor() {
    }

    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        log.debug("request method : {}", request.method());
        log.debug("request url : {}", request.url());
        log.debug("request headers : {}", request.headers());
        RequestBody printedRequestBody = request.body();
        if (printedRequestBody != null) {
            okio.Buffer buffer = new Buffer();
            printedRequestBody.writeTo(buffer);
            log.debug("request payload : {}", buffer.readUtf8());
        }

        Response response = chain.proceed(request);
        log.debug("response code : {}", response.code());
        log.debug("response headers : {}", response.headers());
        ResponseBody body = response.peekBody(Long.MAX_VALUE);
        log.debug("response payload : {}", body.string());
        return response;
    }
}