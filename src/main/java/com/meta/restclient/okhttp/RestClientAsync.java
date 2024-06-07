package com.meta.restclient.okhttp;

import java.util.concurrent.CompletableFuture;

public interface RestClientAsync {
    CompletableFuture<HttpResponse> get(Request request);

    CompletableFuture<HttpResponse> delete(Request request);

    CompletableFuture<HttpResponse> post(BodyRequest request);

    CompletableFuture<HttpResponse> put(BodyRequest request);

    CompletableFuture<HttpResponse> patch(BodyRequest request);
}
