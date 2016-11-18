package com.jaysen.leagueoflegendmanual.data.source;

import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 * hero data repository
 */

public class HeroDataRepository implements BaseDataSource {
    @Inject
    BaseDataSource mLocalHeroBaseDataSource;
    @Inject
    BaseDataSource mRemoteHeroBaseDataSource;

    @Inject
    HeroDataRepository() {
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
