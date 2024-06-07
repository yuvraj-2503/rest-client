package com.meta.restclient.okhttp;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;

public class OkHttpRestClientAsync implements RestClientAsync{
    private final OkHttpClient client;
    private static final MediaType APP_JSON = MediaType.get("application/json");

    public OkHttpRestClientAsync(OkHttpClient client) {
        this.client = client;
    }

    public OkHttpRestClientAsync() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (RestClientConfig.getInstance().isDebug()) {
            builder.addInterceptor(new Slf4jLogInterceptor());
        }

        builder.writeTimeout(Duration.ofMinutes(10L));
        builder.readTimeout(Duration.ofMinutes(10L));
        builder.connectTimeout(Duration.ofSeconds(10L));
        this.client = new OkHttpClient(builder);
    }

    @Override
    public CompletableFuture<HttpResponse> get(Request request) {
        okhttp3.Request.Builder req = (new okhttp3.Request.Builder()).url(request.getUrl().getAsString()).get();
        loadHeaders(request, req);
        Call call = client.newCall(req.build());
        CompletableFuture<HttpResponse> future = new CompletableFuture<>();
        call.enqueue(new HttpResponseCallback(future));
        return future;
    }

    @Override
    public CompletableFuture<HttpResponse> delete(Request request) {
        okhttp3.Request.Builder req = (new okhttp3.Request.Builder()).url(request.getUrl().getAsString()).delete();
        loadHeaders(request, req);
        Call call = client.newCall(req.build());
        CompletableFuture<HttpResponse> future = new CompletableFuture<>();
        call.enqueue(new HttpResponseCallback(future));
        return future;
    }

    private void loadHeaders(Request request, okhttp3.Request.Builder req) {
        if (request.getHeaders() != null) {
            Iterator<Header> headers = request.getHeaders().iterator();

            while(headers.hasNext()) {
                Header header = headers.next();
                req.addHeader(header.getKey(), header.getValue());
            }
        }

    }

    @Override
    public CompletableFuture<HttpResponse> post(BodyRequest request) {
        RequestBody body = getRequestBody(request);
        okhttp3.Request.Builder req = (new okhttp3.Request.Builder()).url(request.getUrl().getAsString()).post(body);
        loadHeaders(request, req);
        Call call = client.newCall(req.build());
        CompletableFuture<HttpResponse> future = new CompletableFuture<>();
        call.enqueue(new HttpResponseCallback(future));
        return future;
    }

    private RequestBody getRequestBody(BodyRequest request) {
        return request.getBody() != null ? RequestBody.create(request.getBody(), APP_JSON) : RequestBody.create("", null);
    }

    @Override
    public CompletableFuture<HttpResponse> put(BodyRequest request) {
        RequestBody body = getRequestBody(request);
        okhttp3.Request.Builder req = (new okhttp3.Request.Builder()).url(request.getUrl().getAsString()).put(body);
        loadHeaders(request, req);
        Call call = client.newCall(req.build());
        CompletableFuture<HttpResponse> future = new CompletableFuture<>();
        call.enqueue(new HttpResponseCallback(future));
        return future;
    }

    @Override
    public CompletableFuture<HttpResponse> patch(BodyRequest request) {
        RequestBody body = getRequestBody(request);
        okhttp3.Request.Builder req = (new okhttp3.Request.Builder()).url(request.getUrl().getAsString()).patch(body);
        loadHeaders(request, req);
        Call call = client.newCall(req.build());
        CompletableFuture<HttpResponse> future = new CompletableFuture<>();
        call.enqueue(new HttpResponseCallback(future));
        return future;
    }

    private static class HttpResponseCallback implements Callback {
        private final CompletableFuture<HttpResponse> future;

        public HttpResponseCallback(CompletableFuture<HttpResponse> future) {
            this.future = future;
        }

        public void onFailure(@NotNull Call call, @NotNull IOException e) {
            this.future.completeExceptionally(e);
        }

        public void onResponse(@NotNull Call call, Response response) throws IOException {
            int code = response.code();
            String body = response.body().string();
            this.future.complete(new HttpResponse(code, body));
        }
    }
}
