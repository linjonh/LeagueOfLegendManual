package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.local;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jaysen.leagueoflegendmanual.BuildConfig;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.AbsDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.Filter;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.DaoSession;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.EquipmentEntity;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroEntity;

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

public class LocalEquipmentDataSource extends AbsDataSource implements Filter<EquipmentEntity> {
    private Subscription subscription;
    private Subscription filterSubcription;

    @Inject
    DaoSession mDaoSession;

    @Inject
    LocalEquipmentDataSource() {
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
                        if (!isUnsubscribed())
                            callback.onDataNotAvailable();
                    }

                    @Override
                    public void onNext(List<HeroEntity> heroEntities) {
                        Log.i("LocalHeroDataSource", Thread.currentThread().getName());
                        if (!isUnsubscribed()) {
                            callback.onDataLoaded(heroEntities);
                        }
                    }
                });
    }


    public void deleteAllLocalDataSource() {
        try {
            mDaoSession.getEquipmentEntityDao().deleteAll();
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
        filterSubcription.unsubscribe();
    }

    @Override
    public <T> void saveDataSource(T dataSets) {
        List<EquipmentEntity> datas = (List<EquipmentEntity>) dataSets;
        try {
            mDaoSession.getEquipmentEntityDao().insertOrReplaceInTx(datas);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * for hero detail interface click equipment item
     * @param nameId
     * @param callback
     */
    @Override
    public void filter(String nameId, @Nullable final LoadDataCallback<EquipmentEntity> callback) {
        checkNotNull(nameId);
        checkNotNull(callback);
        filterSubcription = Observable.just(
                mDaoSession.getEquipmentEntityDao().queryRaw(" WHERE EQ_ID='?' ", nameId))
                .subscribeOn(Schedulers.io())
                .map(new Func1<List<EquipmentEntity>, EquipmentEntity>() {
                    @Override
                    public EquipmentEntity call(List<EquipmentEntity> equipmentEntities) {
                        if (equipmentEntities != null && equipmentEntities.size() > 0) {
                            return equipmentEntities.get(0);
                        }
                        return null;
                    }
                })
                .subscribe(new Subscriber<EquipmentEntity>() {
                    @Override
                    public void onCompleted() {
                        /* no-op */
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (BuildConfig.DEBUG)
                            e.printStackTrace();
                        if (!isUnsubscribed())
                            callback.onDataNotAvailable();
                    }

                    @Override
                    public void onNext(EquipmentEntity equipment) {
                        if (!isUnsubscribed())
                            callback.onDataLoaded(equipment);
                    }
                });
    }
}
