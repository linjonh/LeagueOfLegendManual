package com.jaysen.leagueoflegendmanual.data.source.remote;

import android.support.annotation.NonNull;

import com.jaysen.leagueoflegendmanual.data.source.BaseDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 * // TODO: 2016/11/18  1.load data from local database, contained filter
 * // TODO: 2016/11/18  2.update data from remote data source
 */

public class RemoteHeroDataSource implements BaseDataSource {



    @Inject
    RemoteHeroDataSource() {
    }

    @Override
    public <T> T getService(Class<T> tClass) {
        return null;
    }

    @Override
    public void getDataSource(@NonNull LoadDataCallback callback) {

    }

    @Override
    public void refreshcache() {

    }
}
