package com.jaysen.leagueoflegendmanual.pattern.clean.data.source;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.local.LocalSummonerDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.remote.RemoteSummonerDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.SummonerSkillEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 * hero data repository
 */

public class SummonerSkillRepository extends AbsDataSource {
    @Inject
    LocalSummonerDataSource  mLocalSummonerDataSource;
    @Inject
    RemoteSummonerDataSource mRemoteSummonerDataSource;
    private boolean isCacheDirty;

    @Inject
    SummonerSkillRepository() {
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
            mLocalSummonerDataSource.getDataSource(new LoadDataCallback<List<SummonerSkillEntity>>() {
                @Override
                public void onDataLoaded(List<SummonerSkillEntity> data) {
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
        mRemoteSummonerDataSource.getDataSource(new LoadDataCallback<List<SummonerSkillEntity>>() {
            @Override
            public void onDataLoaded(final List<SummonerSkillEntity> data) {
                // 2016/11/21 add other delete local data logic and update cache and loac database
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mLocalSummonerDataSource.deleteAllLocalDataSource();
                        mLocalSummonerDataSource.saveDataSource(data);
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
        mLocalSummonerDataSource.unSubscribe();
        mRemoteSummonerDataSource.unSubscribe();
    }
}
