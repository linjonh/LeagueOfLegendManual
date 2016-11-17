package com.jaysen.leagueoflegendmanual;

import android.app.Application;


import com.jaysen.leagueoflegendmanual.domain.model.DaoMaster;
import com.jaysen.leagueoflegendmanual.domain.model.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 */

public class APP extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db");
        Database                db     = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
