package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 */

public interface HeroDetailService {
    /**
     * @param heroNameId Ahri.json
     * @return Ahri.json
     */
    @GET("{heroNameId}")
    Observable<String> getHeroDetailInfo(
            @Path("heroNameId") String heroNameId
    );
}
