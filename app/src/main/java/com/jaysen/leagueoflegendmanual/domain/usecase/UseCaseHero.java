package com.jaysen.leagueoflegendmanual.domain.usecase;

import com.jaysen.leagueoflegendmanual.UseCase;
import com.jaysen.leagueoflegendmanual.data.source.HeroDataRepository;

import javax.inject.Inject;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 */

public class UseCaseHero extends UseCase<UseCaseHero.RequestParam, UseCaseHero.Response> {
    @Inject
    UseCaseHero(HeroDataRepository dataRepository) {
    }

    @Override
    public void executeUseCase(RequestParam requestParam) {

    }

    public static final class RequestParam implements UseCase.RequestParam {

    }

    public static final class Response implements UseCase.Response {

    }
}
