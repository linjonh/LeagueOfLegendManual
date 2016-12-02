package com.jaysen.leagueoflegendmanual.pattern.clean.domain.usecase;

import android.util.Log;

import com.jaysen.leagueoflegendmanual.pattern.clean.UseCase;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.BaseDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.HeroDataRepository;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroEntity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 */

public class UseCaseHero extends UseCase<UseCaseHero.RequestParam, UseCaseHero.Response> {

    @Inject
    HeroDataRepository mDataRepository;

    @Inject
    UseCaseHero() {
    }

    public void refreshCache() {
        mDataRepository.refreshCache();
        run();
    }

    @Override
    public void executeUseCase(RequestParam requestParam) {
        mDataRepository.getDataSource(new BaseDataSource.LoadDataCallback<List<HeroEntity>>() {
            @Override
            public void onDataLoaded(List<HeroEntity> data) {
                Log.i("UseCaseHero", Thread.currentThread().getName());
                Observable.just(data)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<List<HeroEntity>>() {
                            @Override
                            public void call(List<HeroEntity> heroEntities) {
                                Log.i("UseCaseHero just", Thread.currentThread().getName());
                                Response response = new Response(heroEntities);
                                getmUseCaseCallBack().onSuccess(response);
                            }
                        });

            }

            @Override
            public void onDataNotAvailable() {
                Observable.empty()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Object>() {
                            @Override
                            public void onCompleted() {
                                getmUseCaseCallBack().onError();
                            }

                            @Override
                            public void onError(Throwable e) {
                                //// STOPSHIP: 2016/12/2
                            }

                            @Override
                            public void onNext(Object o) {
                                //// STOPSHIP: 2016/12/2
                            }
                        });
            }
        });
    }

    public static final class RequestParam implements UseCase.RequestParam {

    }

    public static final class Response implements UseCase.Response {
        public List<HeroEntity> getHeroEntities() {
            return heroEntities;
        }

        List<HeroEntity> heroEntities;

        Response(List<HeroEntity> heroEntities) {
            this.heroEntities = heroEntities;
        }
    }
}
