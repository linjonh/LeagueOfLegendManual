package com.jaysen.leagueoflegendmanual.ui.summoner;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.pattern.clean.UseCase;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.SummonerSkillEntity;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.usecase.UseCaseSummonerSkill;
import com.jaysen.leagueoflegendmanual.pattern.mvp.Presenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by lin on 2016/12/3.
 */

public class SummonerPresenter extends
                               Presenter<Presenter.View<List<SummonerSkillEntity>>, UseCaseSummonerSkill.RequestParam> {
    @Inject
    UseCaseSummonerSkill mUseCaseSummonerSkill;

    @Inject
    public SummonerPresenter() {
    }

    @Override
    public void loadData() {
        Preconditions.checkNotNull(getmParam());
        Preconditions.checkNotNull(getMvpView());
        mUseCaseSummonerSkill.setmRequestParam(getmParam());
        mUseCaseSummonerSkill.setmUseCaseCallBack(new UseCase.UseCaseCallBack<UseCaseSummonerSkill.Response>() {
            @Override
            public void onSuccess(UseCaseSummonerSkill.Response response) {
                getMvpView().onLoadSuccess(response.getEntities());
            }

            @Override
            public void onError() {
                getMvpView().onLoadFailed();
            }
        });
//        mUseCaseSummonerSkill.run();
        mUseCaseSummonerSkill.refreshCache();
    }
}
