package com.jaysen.leagueoflegendmanual.data.source.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/17.
 * retrofit client
 */

public class RetrofitHandler {
    public static Retrofit newInstance(String baseUrl) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//response call adapter
                .addConverterFactory(GsonConverterFactory.create())//response Converter to java bean.
                .client(okHttpClient)//request http client
                .build();
    }
}
