package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 */

public interface CommonService {
    //    public static final String LOL_HERO_LIST_URL                  = Base_URL;
//    public static final String LOL_HERO_DETAIL_URL                = Base_URL + "heros/";
//    public static final String LOL_EQUIPMENT_URL                  = Base_URL + "item.json";
//    public static final String LOL_summoner_URL                   = Base_URL + "summoner.json";
    @GET("hero.json")
    Observable<String> getHeroEntities();

    /**
     * @param heroNameId Ahri.json
     * @return Ahri.json
     */
    @GET("heros/{heroNameId}")
    Observable<String> getHeroDetailInfo(
            @Path("heroNameId") String heroNameId
    );

    @GET("item.json")
    Observable<String> getEquipmentsList();

    @GET("summoner.json")
    Observable<String> getSummonerList();

    @GET("vod")
    Observable<String> getVods();
}
