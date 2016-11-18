package com.jaysen.leagueoflegendmanual.data.source.local;

import android.support.annotation.NonNull;

import com.jaysen.leagueoflegendmanual.data.source.BaseDataSource;

import javax.inject.Inject;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 * TODO 1.use retrofit request pattern to request remote data and save data to local database.
 */

public class LocalHeroDataSource implements BaseDataSource {
    @Inject
    LocalHeroDataSource() {

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
