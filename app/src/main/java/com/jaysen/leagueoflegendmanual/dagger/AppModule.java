package com.jaysen.leagueoflegendmanual.dagger;

import com.jaysen.leagueoflegendmanual.ui.MyApplicationLike;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 */
@Module(subcomponents = {ActivityComponent.class,DataSourceComponent.class})
public class AppModule {
    private MyApplicationLike mApp;

    public AppModule(MyApplicationLike app) {
        mApp = app;
    }

    @Provides
    MyApplicationLike provideAPP() {
        return mApp;
    }
}
