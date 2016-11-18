package com.jaysen.leagueoflegendmanual.dagger;

import com.jaysen.leagueoflegendmanual.data.source.service.RetrofitHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/18.
 */
@Module
public class ApplicationModule {

    @Provides
    @Singleton
    public OkHttpClient provideClient() {
        return new OkHttpClient
                .Builder()
                .build();
    }

    @Provides
    public Retrofit provideRetrofit(OkHttpClient client) {
        return RetrofitHandler.newInstance("", client);
    }
}
