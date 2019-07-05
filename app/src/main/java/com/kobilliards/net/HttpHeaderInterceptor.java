package com.kobilliards.net;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHeaderInterceptor implements Interceptor{
    private Map<String,String> mHeader;

    public HttpHeaderInterceptor(Map<String, String> mHeader) {
        this.mHeader = mHeader;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();

        for (Map.Entry<String, String> entry : mHeader.entrySet()) {
            builder.header(entry.getKey(), entry.getValue());
        }

        Request.Builder requestBuilder = builder.method(originalRequest.method(), originalRequest.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
