package com.jaysen.leagueoflegendmanual.ui.HeroInfos;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.pattern.mvp.Presenter;
import com.jaysen.leagueoflegendmanual.pattern.clean.UseCase;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroEntity;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.usecase.UseCaseHero;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 */

public class HeroFragmentPresenter implements Presenter<Presenter.View<List<HeroEntity>>> {

    public View<List<HeroEntity>> getmView() {
        return mView;
    }

    public void setmView(View<List<HeroEntity>> mView) {
        this.mView = mView;
    }

    View<List<HeroEntity>> mView;

    @Inject
    HeroFragmentPresenter() {
    }

    @Inject
    UseCaseHero mUseCaseHero;


    @Override
    public void loadData() {
        Preconditions.checkNotNull(mUseCaseHero);
        mUseCaseHero.setmRequestParam(new UseCaseHero.RequestParam());
        mUseCaseHero.setmUseCaseCallBack(new UseCase.UseCaseCallBack<UseCaseHero.Response>() {
            @Override
            public void onSuccess(UseCaseHero.Response response) {
                Preconditions.checkNotNull(getmView());
                getmView().onLoadSuccess(response.getHeroEntities());
            }

            @Override
            public void onError() {
                Preconditions.checkNotNull(getmView());
                getmView().onLoadFailed();
            }
        });
        mUseCaseHero.run();
    }

    @Override
    public View getMvpView() {
        return mView;
    }


}
