package com.jaysen.leagueoflegendmanual.pattern.clean.data.source;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.local.LocalVodDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.remote.RemoteVodDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroEntity;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.VodEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 * hero data repository
 */

public class VodRepository extends AbsDataSource {
    @Inject
    LocalVodDataSource  mLocalVodDataSource;
    @Inject
    RemoteVodDataSource mRemoteVodDataSource;
    private boolean isCacheDirty;

    @Inject
    VodRepository() {
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
            mLocalVodDataSource.getDataSource(new LoadDataCallback<List<VodEntity>>() {
                @Override
                public void onDataLoaded(List<VodEntity> data) {
                    // 2016/11/21 add other update logic and cache
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
        mRemoteVodDataSource.getDataSource(new LoadDataCallback<List<VodEntity>>() {
            @Override
            public void onDataLoaded(final List<VodEntity> data) {
                // 2016/11/21 add other delete local data logic and update cache and loac database
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mLocalVodDataSource.deleteAllLocalDataSource();
                        mLocalVodDataSource.saveDataSource(data);
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
        mLocalVodDataSource.unSubscribe();
        mRemoteVodDataSource.unSubscribe();
    }
}
