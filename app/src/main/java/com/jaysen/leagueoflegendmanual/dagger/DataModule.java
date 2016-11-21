package com.jaysen.leagueoflegendmanual.dagger;

import com.jaysen.leagueoflegendmanual.APP;
import com.jaysen.leagueoflegendmanual.data.source.service.HeroService;
import com.jaysen.leagueoflegendmanual.data.source.service.URLAddress;
import com.jaysen.leagueoflegendmanual.domain.model.DaoMaster;
import com.jaysen.leagueoflegendmanual.domain.model.DaoSession;

import org.greenrobot.greendao.database.Database;

import java.util.Hashtable;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 */
@Module
public class DataModule {


    @Provides
    public DaoSession provideDaoSession(APP app) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(app, "hero-db");
        Database                db     = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

    @Provides
    public Map<Class, String> provideUrlMap() {
        Map<Class, String> classStringMap = new Hashtable<>();
        classStringMap.put(HeroService.class, URLAddress.LOL_DUOWAN_HOST);
        return classStringMap;
    }

    @Provides
    @Singleton
    public OkHttpClient provideClient() {
        return new OkHttpClient
                .Builder()
                .build();
    }
}
