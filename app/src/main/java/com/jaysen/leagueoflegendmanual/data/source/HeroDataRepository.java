package com.jaysen.leagueoflegendmanual.data.source;

import android.support.annotation.NonNull;

import com.jaysen.leagueoflegendmanual.APP;

import javax.inject.Inject;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 * hero data repository
 */

public class HeroDataRepository extends AbsDataSource {
    @Inject
    BaseDataSource mLocalHeroBaseDataSource;
    @Inject
    BaseDataSource mRemoteHeroBaseDataSource;

    @Inject
    HeroDataRepository(APP app) {
        super(app);
    }


    @Override
    public void getDataSource(@NonNull LoadDataCallback callback) {

    }

    @Override
    public void refreshCache() {

    }

    @Override
    public void unSubscribe() {
        mLocalHeroBaseDataSource.unSubscribe();
        mRemoteHeroBaseDataSource.unSubscribe();
    }
}
