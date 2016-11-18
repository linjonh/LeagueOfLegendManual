package com.jaysen.leagueoflegendmanual;

import android.app.Application;

import com.jaysen.leagueoflegendmanual.dagger.ApplicationModule;
import com.jaysen.leagueoflegendmanual.dagger.DaggerApplicationComponent;
import com.jaysen.leagueoflegendmanual.domain.model.DaoMaster;
import com.jaysen.leagueoflegendmanual.domain.model.DaoSession;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 */

public class APP extends Application {
    private DaoSession daoSession;
    @Inject
    Retrofit mRetrofit;
    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");
        Database                db     = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule()).build().inject(this);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
