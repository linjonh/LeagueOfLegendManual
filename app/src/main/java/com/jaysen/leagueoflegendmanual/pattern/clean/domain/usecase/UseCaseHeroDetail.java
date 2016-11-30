package com.jaysen.leagueoflegendmanual.pattern.clean.domain.usecase;

import android.util.Log;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.pattern.clean.UseCase;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.BaseDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.HeroDetailDataRepository;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroDetailInfoEntity;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 * 详情useCase
 */

public class UseCaseHeroDetail extends
        UseCase<UseCaseHeroDetail.RequestParam, UseCaseHeroDetail.Response> {

    @Inject
    HeroDetailDataRepository mDataRepository;

    @Inject
    UseCaseHeroDetail() {
    }

    /**
     * used for has been set requestParam situations.
     */
    public void refreshCache() {
        mDataRepository.refreshCache();
        run();
    }

    @Override
    public void executeUseCase(RequestParam requestParam) {
        Preconditions.checkNotNull(requestParam);
        Preconditions.checkNotNull(requestParam.heroNameId);
        mDataRepository.setQueryHeroNameID(requestParam.heroNameId);
        mDataRepository.getDataSource(new BaseDataSource.LoadDataCallback<HeroDetailInfoEntity>() {
            @Override
            public void onDataLoaded(HeroDetailInfoEntity data) {
                Log.i("UseCaseHero", Thread.currentThread().getName());
                Observable.just(data)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<HeroDetailInfoEntity>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(HeroDetailInfoEntity heroDetailInfoEntity) {
                                Log.i("UseCaseHeroDetail just", Thread.currentThread().getName());
                                Response response = new Response(heroDetailInfoEntity);
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

                            }

                            @Override
                            public void onNext(Object o) {

                            }
                        });
            }
        });
    }

    public static final class RequestParam implements UseCase.RequestParam {
        public String heroNameId;
    }

    public static final class Response implements UseCase.Response {
        public HeroDetailInfoEntity getHeroDetailEntity() {
            return heroDetailEntities;
        }

        HeroDetailInfoEntity heroDetailEntities;

        Response(HeroDetailInfoEntity heroEntity) {
            this.heroDetailEntities = heroEntity;
        }
    }
}
