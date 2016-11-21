package com.jaysen.leagueoflegendmanual.data.source.service;

import com.jaysen.leagueoflegendmanual.domain.model.HeroEntity;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 */

public interface HeroService {
    @GET("hero/")
    Observable<String> getHeroEntities();
}
