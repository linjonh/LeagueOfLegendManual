package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service;

import com.jaysen.leagueoflegendmanual.util.StringConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/17.
 * retrofit client
 */

public class RetrofitHandler {
    public static Retrofit newInstance(String baseUrl,OkHttpClient okHttpClient) {
        //retrofit adapter
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//response call adapter
//                .addConverterFactory(GsonConverterFactory.create())//response Converter to java bean.
                .addConverterFactory(StringConverterFactory.create())//response Converter to java bean.
                .client(okHttpClient)//request http client
                .build();
    }
}
