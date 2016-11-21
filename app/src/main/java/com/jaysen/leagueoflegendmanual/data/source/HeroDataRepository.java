package com.jaysen.leagueoflegendmanual.data.source;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.data.source.local.LocalHeroDataSource;
import com.jaysen.leagueoflegendmanual.data.source.remote.RemoteHeroDataSource;
import com.jaysen.leagueoflegendmanual.domain.model.HeroEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 * hero data repository
 */

public class HeroDataRepository extends AbsDataSource {
    @Inject
    LocalHeroDataSource  mLocalHeroBaseDataSource;
    @Inject
    RemoteHeroDataSource mRemoteHeroBaseDataSource;
    private boolean isDirty = true;

    @Inject
    HeroDataRepository() {
    }

    /**
     * get main data source
     *
     * @param callback
     */
    @Override
    public void getDataSource(@NonNull final LoadDataCallback callback) {
        Preconditions.checkNotNull(callback);
        if (isDirty) {
            getRemoteData(callback);
        } else {
            mLocalHeroBaseDataSource.getDataSource(new LoadDataCallback<List<HeroEntity>>() {
                @Override
                public void onDataLoaded(List<HeroEntity> data) {
                    // TODO: 2016/11/21 add other update logic and cache
                    callback.onDataLoaded(data);
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
        mRemoteHeroBaseDataSource.getDataSource(new LoadDataCallback<List<HeroEntity>>() {
            @Override
            public void onDataLoaded(List<HeroEntity> data) {
                // TODO: 2016/11/21 add other delete local data logic and update cache and loac database
                mLocalHeroBaseDataSource.deleteAllLocalDataSource();
                mLocalHeroBaseDataSource.saveDataSource(data);
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
        isDirty = true;
    }

    @Override
    public void unSubscribe() {
        mLocalHeroBaseDataSource.unSubscribe();
        mRemoteHeroBaseDataSource.unSubscribe();
    }
}
