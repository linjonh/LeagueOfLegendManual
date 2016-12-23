package com.jaysen.leagueoflegendmanual.ui.vod;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.pattern.clean.UseCase;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.VodEntity;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.usecase.UseCaseVod;
import com.jaysen.leagueoflegendmanual.pattern.mvp.Presenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jaysen.lin@foxmail.com on 2016/12/23.
 */

public class VodPresenter extends Presenter<Presenter.View<List<VodEntity>>, UseCaseVod.RequestParam> {

    @Inject
    UseCaseVod useCaseVod;

    @Inject
    public VodPresenter() {
    }

    @Override
    public void loadData() {
        Preconditions.checkNotNull(getmParam());
        Preconditions.checkNotNull(getMvpView());
        useCaseVod.setmRequestParam(getmParam());
        useCaseVod.setmUseCaseCallBack(new UseCase.UseCaseCallBack<UseCaseVod.Response>() {
            @Override
            public void onSuccess(UseCaseVod.Response response) {
                getMvpView().onLoadSuccess(response.getEntities());
            }

            @Override
            public void onError() {
                getMvpView().onLoadFailed();
            }
        });
//        useCaseVod.run();
        useCaseVod.refreshCache();
    }
}
