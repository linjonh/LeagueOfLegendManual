package com.jaysen.leagueoflegendmanual.ui.equipment;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.pattern.clean.UseCase;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.EquipmentEntity;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.usecase.UseCaseEquipment;
import com.jaysen.leagueoflegendmanual.pattern.mvp.Presenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jaysen.lin@foxmail.com on 2016/12/2.
 */

public class EquipmentPresenter extends
                                Presenter<Presenter.View<List<EquipmentEntity>>, UseCaseEquipment.RequestParam> {
    @Inject
    UseCaseEquipment mUseCaseEquipment;

    @Inject
    public EquipmentPresenter() {
        //no instance
    }

    @Override
    public void loadData() {
        Preconditions.checkNotNull(mUseCaseEquipment);
        Preconditions.checkNotNull(getMvpView());
        Preconditions.checkNotNull(getmParam());
        mUseCaseEquipment.setmRequestParam(getmParam());
        mUseCaseEquipment.setmUseCaseCallBack(
                new UseCase.UseCaseCallBack<UseCaseEquipment.Response>() {
                    @Override
                    public void onSuccess(UseCaseEquipment.Response response) {
                        getMvpView().onLoadSuccess(response.getEquipmentEntities());
                    }

                    @Override
                    public void onError() {
                        getMvpView().onLoadFailed();
                    }
                });
//        mUseCaseEquipment.run();
        mUseCaseEquipment.refreshCache();
    }
}
