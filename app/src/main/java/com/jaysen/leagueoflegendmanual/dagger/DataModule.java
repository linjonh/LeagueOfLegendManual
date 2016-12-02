package com.jaysen.leagueoflegendmanual.dagger;

import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service.CommonService;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service.URLAddress;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.DaoMaster;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.DaoSession;
import com.jaysen.leagueoflegendmanual.ui.MyApplicationLike;
import com.jaysen.leagueoflegendmanual.util.HttpLoggingInterceptor;

import org.greenrobot.greendao.database.Database;

import java.util.Hashtable;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 * data source provide module
 */
@Module
public class DataModule {
    @Provides
    public DaoSession provideDaoSession(MyApplicationLike app) {
        DaoMaster.DevOpenHelper helper = new DaoMaster
                .DevOpenHelper(app.getApplication(), "hero-db");
        Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

    @Provides
    public Map<Class, String> provideUrlMap() {
        Map<Class, String> classStringMap = new Hashtable<>();
        classStringMap.put(CommonService.class, URLAddress.Base_URL);
        return classStringMap;
    }

    @Provides
    @Singleton
    public OkHttpClient provideClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();
    }
}
