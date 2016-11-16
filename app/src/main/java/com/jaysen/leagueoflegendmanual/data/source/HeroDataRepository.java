package com.jaysen.leagueoflegendmanual.data.source;

import com.jaysen.leagueoflegendmanual.domain.model.HeroEntity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 */

public class HeroDataRepository {

    //TODO 注入retrofit实例

    @Inject
    HeroDataRepository() {

    }

    public Observable<List<HeroEntity>> getLocalHeroEntities() {

        return null;
    }

}
