package com.pengchao.hellokotlin.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pengchao on 2017/8/28.
 */

public class RetrofitManager {
    private static final int TIME_OUT = 30; //超时时间


    private static RetrofitManager instance;

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }

        return instance;
    }

    private Retrofit mRetrofit;

    private RetrofitManager() {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://git.oschina.net/api/")
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }


    public <T> T create(final Class<T> service) {
        return RetrofitManager.getInstance().getRetrofit().create(service);
    }
}
