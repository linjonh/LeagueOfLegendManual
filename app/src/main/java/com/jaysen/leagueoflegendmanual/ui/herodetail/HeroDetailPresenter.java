package com.jaysen.leagueoflegendmanual.ui.herodetail;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.pattern.clean.UseCase;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroDetailInfoEntity;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.usecase.UseCaseHeroDetail;
import com.jaysen.leagueoflegendmanual.pattern.mvp.Presenter;

import javax.inject.Inject;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/30.
 */

public class HeroDetailPresenter extends
        Presenter<Presenter.View<HeroDetailInfoEntity>, UseCaseHeroDetail.RequestParam> {
    @Inject
    UseCaseHeroDetail mUseCaseHeroDetail;

    @Inject
    public HeroDetailPresenter() {
    }

    @Override
    public void loadData() {
        Preconditions.checkNotNull(mUseCaseHeroDetail);
        Preconditions.checkNotNull(getmParam());
        Preconditions.checkNotNull(getMvpView());
        mUseCaseHeroDetail.setmRequestParam(getmParam());
        mUseCaseHeroDetail.setmUseCaseCallBack(new UseCase.UseCaseCallBack<UseCaseHeroDetail.Response>() {
            @Override
            public void onSuccess(UseCaseHeroDetail.Response response) {
                getMvpView().onLoadSuccess(response.getHeroDetailEntity());
            }

            @Override
            public void onError() {
                getMvpView().onLoadFailed();
            }
        });
//        mUseCaseHeroDetail.run();
        mUseCaseHeroDetail.refreshCache();
    }
}
