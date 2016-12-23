package com.jaysen.leagueoflegendmanual.pattern.clean.data.source;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.VodEntity;

import java.util.List;

/**
 * Created by jaysen.lin@foxmail.com on 2016/12/23.
 * not support inject, must be instantiated by useCase
 */

public class DefaultRepository extends AbsDataSource {
    private AbsDataSource localDataSource;
    private AbsDataSource remoteDataSource;
    private boolean isCacheDirty;

    /**
     *
     * @param localDataSource local
     * @param remoteDataSource remote
     */
    public DefaultRepository(AbsDataSource localDataSource, AbsDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public <D> void getDataSource(@NonNull final LoadDataCallback<D> callback) {
        Preconditions.checkNotNull(callback);
        if (isCacheDirty) {
            getRemoteData(callback);
        } else {
            localDataSource.getDataSource(new LoadDataCallback<D>() {
                @Override
                public void onDataLoaded(D data) {
                    //  2016/12/23 add other update logic and cache
                    if (data instanceof List) {
                        List tmp = (List) data;
                        if (tmp.size() == 0) {
                            getRemoteData(callback);
                        } else {
                            callback.onDataLoaded(data);
                        }
                    } else if (data == null) {
                        getRemoteData(callback);
                    }else {
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
        remoteDataSource.getDataSource(new LoadDataCallback<List<VodEntity>>() {
            @Override
            public void onDataLoaded(final List<VodEntity> data) {
                // 2016/12/23 add other delete local data logic and update cache and loac database
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        localDataSource.deleteAllLocalDataSource();
                        localDataSource.saveDataSource(data);
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
        localDataSource.unSubscribe();
        remoteDataSource.unSubscribe();
    }
}
