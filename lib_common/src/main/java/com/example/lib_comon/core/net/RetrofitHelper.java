package com.example.lib_comon.core.net;

//import com.brucej.wanandroid_java.api.RestApi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private OkHttpClient okHttpClient;


    private RetrofitHelper() {
        retrofitMap = new HashMap<>();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .addNetworkInterceptor(loggingInterceptor)
                .build();
    }

    private static RetrofitHelper helper;

    public static RetrofitHelper getInstance() {
        if (helper == null) {
            synchronized (RetrofitHelper.class) {
                if (helper == null) {
                    helper = new RetrofitHelper();
                }
            }
        }
        return helper;
    }

    private Map<String, Retrofit> retrofitMap;

    private synchronized Retrofit getRetrofit(String host) {
        Retrofit retrofit = retrofitMap.get(host);
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(host)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            retrofitMap.put(host, retrofit);
        }
        return retrofit;
    }

//    public RestApi getRestApi() {
//        Retrofit retrofit = getRetrofit(RestApi.HOST);
//        return retrofit.create(RestApi.class);
//    }

    public <T> T getApi(String host, Class<? extends T> serviceClass) {
        Retrofit retrofit = getRetrofit(host);
        return retrofit.create(serviceClass);
    }

}
