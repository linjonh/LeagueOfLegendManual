package com.jaysen.leagueoflegendmanual.dagger;

import com.jaysen.leagueoflegendmanual.APP;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 */
@Module
public class AppModule {
    private APP mApp;

    public AppModule(APP app) {
        mApp = app;
    }

    @Provides
    APP provideAPP() {
        return mApp;
    }
}
