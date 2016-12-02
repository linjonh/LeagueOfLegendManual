package com.jaysen.leagueoflegendmanual.pattern.clean.domain.usecase;

import android.util.Log;

import com.jaysen.leagueoflegendmanual.pattern.clean.UseCase;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.BaseDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.EquipmentRepository;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.HeroDataRepository;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.EquipmentEntity;
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

public class UseCaseEquipment extends
        UseCase<UseCaseEquipment.RequestParam, UseCaseEquipment.Response> {

    public static final String TAG = "UseCaseEquipment";
    @Inject
    EquipmentRepository mDataRepository;

    @Inject
    UseCaseEquipment() {
    }

    public void refreshCache() {
        mDataRepository.refreshCache();
        run();
    }

    @Override
    public void executeUseCase(RequestParam requestParam) {
        mDataRepository.getDataSource(new BaseDataSource.LoadDataCallback<List<EquipmentEntity>>() {
            @Override
            public void onDataLoaded(List<EquipmentEntity> data) {
                Log.i(TAG, Thread.currentThread().getName());
                Observable.just(data)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<List<EquipmentEntity>>() {
                            @Override
                            public void call(List<EquipmentEntity> entities) {
                                Log.i(TAG + " just", Thread.currentThread().getName());
                                Response response = new Response(entities);
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
                                //stub
                            }

                            @Override
                            public void onNext(Object o) {
                                //stub
                            }
                        });
            }
        });
    }

    public static final class RequestParam implements UseCase.RequestParam {

    }

    public static final class Response implements UseCase.Response {

        public List<EquipmentEntity> getEquipmentEntities() {
            return equipmentEntities;
        }

        List<EquipmentEntity> equipmentEntities;

        Response(List<EquipmentEntity> entities) {
            this.equipmentEntities = entities;
        }
    }
}
