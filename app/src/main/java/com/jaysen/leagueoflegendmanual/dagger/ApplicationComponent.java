package com.jaysen.leagueoflegendmanual.dagger;

import com.jaysen.leagueoflegendmanual.APP;

import dagger.Component;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 */
@Component(modules = {AppModule.class})
public interface ApplicationComponent {
    void inject(APP app);
    DataSourceComponent.Builder getDataSourceBuilder();
}
