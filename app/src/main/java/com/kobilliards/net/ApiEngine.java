package com.kobilliards.net;


import com.kobilliards.constants.UrlConstant;
import com.kobilliards.utils.AppUtils;
import com.kobilliards.utils.JsonUtils;
import com.kobilliards.utils.PhoneUtils;
import com.kobilliards.utils.log.LogUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


public class ApiEngine {
    private static final int DEFAULT_TIMEOUT = 15;////请求超时时长，单位秒
    private static final int DEFAULT_CACHE_SIZE = 1024 * 1024 * 20; //大小默认20Mb

    private volatile static ApiEngine mInstance;

    private Retrofit mRetrofit;

    private ApiEngine(){
        File cacheFile = new File(AppUtils.getAppContext().getCacheDir(), "OkHttpCache");
        Cache cache = new Cache(cacheFile, DEFAULT_CACHE_SIZE);
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//设置请求超时时长
                .addInterceptor(getHttpLoggingInterceptor())//启用Log日志
                .addInterceptor(getHttpHeaderInterceptor())
                .sslSocketFactory(createSSLSocketFactory())//设置https访问(验证证书)
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .cache(cache);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(UrlConstant.API_HOST)
                //配置转化库，采用Gson
                .addConverterFactory(GsonConverterFactory.create())
                //配置回调库，采用RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //设置OKHttpClient为网络客户端
                .client(okHttpClientBuilder.build()).build();
    }


    public static ApiEngine getInstance() {
        if (mInstance == null) {
            synchronized (ApiEngine.class) {
                if (mInstance == null) {
                    mInstance = new ApiEngine();
                }
            }
        }
        return mInstance;
    }

    public ApiService getApiService() {
        return mRetrofit.create(ApiService.class);
    }

    private HttpLoggingInterceptor getHttpLoggingInterceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                try {
                    String text = URLDecoder.decode(message, "utf-8");
                    JsonUtils jsonUtils = new JsonUtils();
                    if (!jsonUtils.validate(text)) {
                        LogUtil.d("HttpLog", text);
                    } else {
                        LogUtil.json("HttpLog", text);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    LogUtil.e("HttpLog", message);
                }

            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    private HttpHeaderInterceptor getHttpHeaderInterceptor(){
        HashMap<String,String> headers = new HashMap<>();
        headers.put("Proxy-Client-IP", PhoneUtils.getIPAddress(AppUtils.getAppContext()));
        headers.put("WL-Proxy-Client-IP", PhoneUtils.getIPAddress(AppUtils.getAppContext()));
        headers.put("HTTP_CLIENT_IPHTTP_X_FORWARDED_FOR", PhoneUtils.getIPAddress(AppUtils.getAppContext()));
        headers.put("deviceId",PhoneUtils.getDeviceId(AppUtils.getAppContext()));
        return new HttpHeaderInterceptor(headers);
    }


    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{createTrustAllManager()}, new SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {

        }
        return sslSocketFactory;
    }

    private X509TrustManager createTrustAllManager() {
        X509TrustManager tm = null;
        try {
            tm =   new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    //do nothing，接受任意客户端证书
                }
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    //do nothing，接受任意服务端证书
                }
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
        } catch (Exception e) {

        }
        return tm;
    }





}
