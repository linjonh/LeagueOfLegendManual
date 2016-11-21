package com.jaysen.leagueoflegendmanual.domain.usecase;

import com.jaysen.leagueoflegendmanual.UseCase;
import com.jaysen.leagueoflegendmanual.data.source.BaseDataSource;
import com.jaysen.leagueoflegendmanual.data.source.HeroDataRepository;
import com.jaysen.leagueoflegendmanual.domain.model.HeroEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 */

public class UseCaseHero extends UseCase<UseCaseHero.RequestParam, UseCaseHero.Response> {

    @Inject
    HeroDataRepository mDataRepository;

    @Inject
    UseCaseHero() {
    }

    @Override
    public void executeUseCase(RequestParam requestParam) {
        mDataRepository.getDataSource(new BaseDataSource.LoadDataCallback<List<HeroEntity>>() {
            @Override
            public void onDataLoaded(List<HeroEntity> data) {
                Response response = new Response(data);
                getmUseCaseCallBack().onSuccess(response);
            }

            @Override
            public void onDataNotAvailable() {
                getmUseCaseCallBack().onError();
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
