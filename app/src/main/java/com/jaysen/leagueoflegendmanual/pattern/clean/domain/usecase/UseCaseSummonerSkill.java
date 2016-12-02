package com.jaysen.leagueoflegendmanual.pattern.clean.domain.usecase;

import android.util.Log;

import com.jaysen.leagueoflegendmanual.pattern.clean.UseCase;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.BaseDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.SummonerSkillRepository;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.SummonerSkillEntity;

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

public class UseCaseSummonerSkill extends UseCase<UseCaseSummonerSkill.RequestParam, UseCaseSummonerSkill.Response> {

    public static final String TAG = "UseCaseSummoner";
    @Inject
    SummonerSkillRepository mDataRepository;

    @Inject
    UseCaseSummonerSkill() {
    }

    public void refreshCache() {
        mDataRepository.refreshCache();
        run();
    }

    @Override
    public void executeUseCase(RequestParam requestParam) {
        mDataRepository.getDataSource(new BaseDataSource.LoadDataCallback<List<SummonerSkillEntity>>() {
            @Override
            public void onDataLoaded(List<SummonerSkillEntity> data) {
                Log.i(TAG, Thread.currentThread().getName());
                Observable.just(data)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<List<SummonerSkillEntity>>() {
                            @Override
                            public void call(List<SummonerSkillEntity> vodEntities) {
                                Log.i(TAG + " just", Thread.currentThread().getName());
                                Response response = new Response(vodEntities);
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

    }

    public static final class Response implements UseCase.Response {

        public List<SummonerSkillEntity> getEntities() {
            return entities;
        }

        List<SummonerSkillEntity> entities;

        Response(List<SummonerSkillEntity> entities) {
            this.entities = entities;
        }
    }
}
