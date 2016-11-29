package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 */

public interface EquipmentService {
    @GET("hero/")
    Observable<String> getHeroEntities();
}
