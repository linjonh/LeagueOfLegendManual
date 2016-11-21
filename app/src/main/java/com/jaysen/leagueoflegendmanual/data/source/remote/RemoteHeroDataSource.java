package com.jaysen.leagueoflegendmanual.data.source.remote;

import android.support.annotation.NonNull;

import com.jaysen.leagueoflegendmanual.APP;
import com.jaysen.leagueoflegendmanual.BuildConfig;
import com.jaysen.leagueoflegendmanual.data.source.AbsDataSource;
import com.jaysen.leagueoflegendmanual.data.source.service.HeroService;
import com.jaysen.leagueoflegendmanual.domain.model.HeroEntity;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 * TODO 1.use retrofit request pattern to request remote data and save data to local database.
 */

public class RemoteHeroDataSource extends AbsDataSource {
    private Subscription subscription;

    @Inject
    public RemoteHeroDataSource(APP app) {
        super(app);
    }

    @Override
    public void getDataSource(@NonNull final LoadDataCallback callback) {
        subscription = getService(HeroService.class)
                .getHeroEntities()
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
                        if (!isUnsubscribed()) {
                            callback.onDataLoaded(heroEntities);
                        }
                    }
                });
    }

    @Override
    public void unSubscribe() {
        subscription.unsubscribe();
    }
}
