package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.local;

import android.support.annotation.NonNull;
import android.util.Log;

import com.jaysen.leagueoflegendmanual.BuildConfig;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.AbsDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.DaoSession;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroDetailInfoEntity;
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

public class LocalHeroDetatilDataSource extends AbsDataSource {
    private Subscription subscription;

    @Inject
    DaoSession mDaoSession;

    public void setmKey(Long mKey) {
        this.mKey = mKey;
    }

    private Long mKey;

    @Inject
    LocalHeroDetatilDataSource() {
    }

    @Override
    public void getDataSource(@NonNull final LoadDataCallback callback) {
        checkNotNull(callback);
        subscription = mDaoSession
                .getHeroDetailInfoEntityDao()
                .rx()
                .load(mKey)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<HeroDetailInfoEntity>() {
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
                    public void onNext(HeroDetailInfoEntity heroEntities) {
                        Log.i("LocalHeroDataSource",Thread.currentThread().getName());
                        if (!isUnsubscribed()) {
                            callback.onDataLoaded(heroEntities);
                        }
                    }
                });
    }


    public void deleteAllLocalDataSource() {
        try {
            mDaoSession.getHeroEntityDao().deleteAll();
        }catch (Exception e){
            e.printStackTrace();
        }
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
//        HeroDetailInfoEntity datas = (HeroDetailInfoEntity) dataSets;
//        mDaoSession.getHeroDetailInfoEntityDao().insertInTx(datas);
//        for (HeroEntity item : datas) {
//            try {
//                long id = mDaoSession.getHeroEntityDao().insert(item);
//                if (BuildConfig.DEBUG) {
//                    Log.d("HERO", "insert hero id: " + id);
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
    }
}
