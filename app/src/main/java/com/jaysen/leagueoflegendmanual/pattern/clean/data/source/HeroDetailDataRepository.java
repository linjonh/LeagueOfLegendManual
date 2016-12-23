package com.jaysen.leagueoflegendmanual.pattern.clean.data.source;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.local.LocalHeroDetailDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.remote.RemoteHeroDetailDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroDetailInfoEntity;

import javax.inject.Inject;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 * hero data repository
 */

public class HeroDetailDataRepository extends AbsDataSource {
    @Inject
    LocalHeroDetailDataSource  mLocalHeroDetailDataSource;
    @Inject
    RemoteHeroDetailDataSource mRemoteHeroDetailDataSource;
    private boolean isCacheDirty;

    @Inject
    HeroDetailDataRepository() {
    }

    public void setQueryHeroNameID(String heroNameID) {
        this.heroNameID = heroNameID;
    }

    private String heroNameID;

    /**
     * get main data source
     *
     * @param callback
     */
    @Override
    public void getDataSource(@NonNull final LoadDataCallback callback) {
        Preconditions.checkNotNull(callback);
        if (isCacheDirty) {
            getRemoteData(callback);
        } else {
            Preconditions.checkNotNull(heroNameID);
            mLocalHeroDetailDataSource.setNameId(heroNameID);
            mLocalHeroDetailDataSource.getDataSource(new LoadDataCallback<HeroDetailInfoEntity>() {
                @Override
                public void onDataLoaded(HeroDetailInfoEntity data) {
                    // 2016/11/21 add other update logic and cache
                    if (data == null) {
                        getRemoteData(callback);
                    } else {
                        callback.onDataLoaded(data);
                    }
                }

                @Override
                public void onDataNotAvailable() {
                    getRemoteData(callback);
                }
            });
        }

    }

    /**
     * remote network
     *
     * @param callback
     */
    private void getRemoteData(@NonNull final LoadDataCallback callback) {
        Preconditions.checkNotNull(callback);
        Preconditions.checkNotNull(heroNameID);
        mRemoteHeroDetailDataSource.setHeroNameID(heroNameID);
        mRemoteHeroDetailDataSource.getDataSource(new LoadDataCallback<HeroDetailInfoEntity>() {
            @Override
            public void onDataLoaded(final HeroDetailInfoEntity data) {
                // 2016/11/21 add other delete local data logic and update cache and loac database
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mLocalHeroDetailDataSource.deleteAllLocalDataSource();
                        mLocalHeroDetailDataSource.saveDataSource(data);
                    }
                }).start();
                callback.onDataLoaded(data);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    /**
     * for outer useCase class called to refresh
     */
    @Override
    public void refreshCache() {
        isCacheDirty = true;
    }

    @Override
    public void unSubscribe() {
        mLocalHeroDetailDataSource.unSubscribe();
        mRemoteHeroDetailDataSource.unSubscribe();
    }
}
