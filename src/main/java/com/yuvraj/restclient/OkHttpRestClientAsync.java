package com.yuvraj.restclient;

import okhttp3.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
public class OkHttpRestClientAsync implements RestClientAsync {
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

    public CompletableFuture<HttpResponse> get(Request request) {
        okhttp3.Request.Builder req = (new okhttp3.Request.Builder()).url(request.getUrl().getAsString()).get();
        this.loadHeaders(request, req);
        Call call = this.client.newCall(req.build());
        CompletableFuture<HttpResponse> future = new CompletableFuture();
        call.enqueue(new HttpResponseCallback(future));
        return future;
    }

    public CompletableFuture<HttpResponse> delete(Request request) {
        okhttp3.Request.Builder req = (new okhttp3.Request.Builder()).url(request.getUrl().getAsString()).delete();
        this.loadHeaders(request, req);
        Call call = this.client.newCall(req.build());
        CompletableFuture<HttpResponse> future = new CompletableFuture();
        call.enqueue(new HttpResponseCallback(future));
        return future;
    }

    private void loadHeaders(Request request, okhttp3.Request.Builder req) {
        if (request.getHeaders() != null) {
            Iterator var3 = request.getHeaders().iterator();

            while(var3.hasNext()) {
                Header header = (Header)var3.next();
                req.addHeader(header.getKey(), header.getValue());
            }
        }

    }

    public CompletableFuture<HttpResponse> post(BodyRequest request) {
        RequestBody body = this.getRequestBody(request);
        okhttp3.Request.Builder req = (new okhttp3.Request.Builder()).url(request.getUrl().getAsString()).post(body);
        this.loadHeaders(request, req);
        Call call = this.client.newCall(req.build());
        CompletableFuture<HttpResponse> future = new CompletableFuture();
        call.enqueue(new HttpResponseCallback(future));
        return future;
    }

    private RequestBody getRequestBody(BodyRequest request) {
        return request.getBody() != null ? RequestBody.create(request.getBody(), APP_JSON) : RequestBody.create("", (MediaType)null);
    }

    public CompletableFuture<HttpResponse> put(BodyRequest request) {
        RequestBody body = this.getRequestBody(request);
        okhttp3.Request.Builder req = (new okhttp3.Request.Builder()).url(request.getUrl().getAsString()).put(body);
        this.loadHeaders(request, req);
        Call call = this.client.newCall(req.build());
        CompletableFuture<HttpResponse> future = new CompletableFuture();
        call.enqueue(new HttpResponseCallback(future));
        return future;
    }

    public CompletableFuture<HttpResponse> patch(BodyRequest request) {
        RequestBody body = this.getRequestBody(request);
        okhttp3.Request.Builder req = (new okhttp3.Request.Builder()).url(request.getUrl().getAsString()).patch(body);
        this.loadHeaders(request, req);
        Call call = this.client.newCall(req.build());
        CompletableFuture<HttpResponse> future = new CompletableFuture();
        call.enqueue(new HttpResponseCallback(future));
        return future;
    }

    private static class HttpResponseCallback implements Callback {
        private final CompletableFuture<HttpResponse> future;

        public HttpResponseCallback(CompletableFuture<HttpResponse> future) {
            this.future = future;
        }

        public void onFailure(Call call, IOException e) {
            this.future.completeExceptionally(e);
        }

        public void onResponse(Call call, Response response) throws IOException {
            int code = response.code();
            String body = response.body().string();
            this.future.complete(new HttpResponse(code, body));
        }
    }
}
