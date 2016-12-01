package com.jaysen.leagueoflegendmanual.dagger;

import com.jaysen.leagueoflegendmanual.ui.MyApplicationLike;

import dagger.Component;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 * main component to obtain subComponent through registering subComponent
 */
@Component(modules = {AppModule.class})
public interface ApplicationComponent {
    void inject(MyApplicationLike app);

    DataSourceComponent.Builder getDataSourceBuilder();
//    DataSourceComponent getDataSourceComponent();//compile error
}
