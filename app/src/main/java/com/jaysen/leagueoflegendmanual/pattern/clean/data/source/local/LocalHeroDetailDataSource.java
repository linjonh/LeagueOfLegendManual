package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.local;

import android.support.annotation.NonNull;
import android.util.Log;

import com.jaysen.leagueoflegendmanual.BuildConfig;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.AbsDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.DaoSession;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroDetailInfoEntity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 * // TODO: 2016/11/18  1.load data from local database, contained filter
 * // TODO: 2016/11/18  2.update data from remote data source
 */

public class LocalHeroDetailDataSource extends AbsDataSource {
    private Subscription subscription;

    @Inject
    DaoSession mDaoSession;

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    private String nameId;

    @Inject
    LocalHeroDetailDataSource() {
    }

    @Override
    public void getDataSource(@NonNull final LoadDataCallback callback) {
        checkNotNull(callback);
        subscription = Observable.just(mDaoSession.getHeroDetailInfoEntityDao().queryRaw(" WHERE NAME_ID='?' ", nameId))
                .subscribeOn(Schedulers.io())
                .map(new Func1<List<HeroDetailInfoEntity>, HeroDetailInfoEntity>() {
                    @Override
                    public HeroDetailInfoEntity call(List<HeroDetailInfoEntity> heroDetailInfoEntities) {
                        if (heroDetailInfoEntities != null && heroDetailInfoEntities.size() > 0) {
                            return heroDetailInfoEntities.get(0);
                        }
                        return null;
                    }
                })
                .subscribe(new Subscriber<HeroDetailInfoEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (BuildConfig.DEBUG)
                            e.printStackTrace();
                        if (!isUnsubscribed())

                            callback.onDataNotAvailable();
                    }

                    @Override
                    public void onNext(HeroDetailInfoEntity heroEntities) {
                        Log.i("LocalHeroDataSource", Thread.currentThread().getName());
                        if (!isUnsubscribed()) {
                            callback.onDataLoaded(heroEntities);
                        }
                    }
                });
    }


    public void deleteAllLocalDataSource() {
        try {
            mDaoSession.getHeroDetailInfoEntityDao().deleteAll();
        } catch (Exception e) {
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
        HeroDetailInfoEntity datas = (HeroDetailInfoEntity) dataSets;
        try {
            long id = mDaoSession.getHeroDetailInfoEntityDao().insert(datas);
            Log.d("HERO", "insert hero id: " + id);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }

    }
}
