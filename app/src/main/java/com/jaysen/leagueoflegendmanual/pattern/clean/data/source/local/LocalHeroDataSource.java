package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.local;

import android.support.annotation.NonNull;
import android.util.Log;

import com.jaysen.leagueoflegendmanual.BuildConfig;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.AbsDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.DaoSession;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroEntity;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 * // TODO: 2016/11/18  1.load data from local database, contained filter
 * // TODO: 2016/11/18  2.update data from remote data source
 */

public class LocalHeroDataSource extends AbsDataSource {
    private Subscription subscription;

    @Inject
    DaoSession mDaoSession;

    @Inject
    LocalHeroDataSource() {
    }

    @Override
    public void getDataSource(@NonNull final LoadDataCallback callback) {
        checkNotNull(callback);
        subscription = mDaoSession.getHeroEntityDao()
                .rx()
                .loadAll()
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<HeroEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (BuildConfig.DEBUG)
                            e.printStackTrace();
                        callback.onDataNotAvailable();
                    }

                    @Override
                    public void onNext(List<HeroEntity> heroEntities) {
                        Log.i("LocalHeroDataSource",Thread.currentThread().getName());
                        if (!isUnsubscribed()) {
                            callback.onDataLoaded(heroEntities);
                        }
                    }
                });
    }


    public void deleteAllLocalDataSource() {
        mDaoSession.getHeroEntityDao().deleteAll();
    }

    @Override
    public void refreshCache() {

    }

    @Override
    public void unSubscribe() {
        subscription.unsubscribe();
    }

    @Override
    public <T> void saveDataSource(T dataSets) {
        List<HeroEntity> datas = (List<HeroEntity>) dataSets;
//        mDaoSession.getHeroEntityDao().insertInTx(datas);
        for (HeroEntity item : datas) {
            long id = mDaoSession.getHeroEntityDao().insert(item);
            if (BuildConfig.DEBUG) {
                Log.d("HERO", "insert hero id: " + id);
            }
        }
    }
}
