package com.jaysen.leagueoflegendmanual.pattern.clean.data.source;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.local.LocalHeroDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.remote.RemoteHeroDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroEntity;

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
    private boolean isCacheDirty;

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
        if (isCacheDirty) {
            getRemoteData(callback);
        } else {
            mLocalHeroBaseDataSource.getDataSource(new LoadDataCallback<List<HeroEntity>>() {
                @Override
                public void onDataLoaded(List<HeroEntity> data) {
                    // TODO: 2016/11/21 add other update logic and cache
                    if (data == null || data.size() == 0) {
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
        mRemoteHeroBaseDataSource.getDataSource(new LoadDataCallback<List<HeroEntity>>() {
            @Override
            public void onDataLoaded(final List<HeroEntity> data) {
                // TODO: 2016/11/21 add other delete local data logic and update cache and loac database
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mLocalHeroBaseDataSource.deleteAllLocalDataSource();
                        mLocalHeroBaseDataSource.saveDataSource(data);
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
        mLocalHeroBaseDataSource.unSubscribe();
        mRemoteHeroBaseDataSource.unSubscribe();
    }
}
