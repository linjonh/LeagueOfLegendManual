package com.jaysen.leagueoflegendmanual.dagger;

import com.jaysen.leagueoflegendmanual.data.source.AbsDataSource;

import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 */
@Singleton
@Subcomponent(modules = {DataModule.class})
public interface DataSourceComponent {
    void inject(AbsDataSource source);

    @Subcomponent.Builder
    interface Builder {
        DataSourceComponent build();

        Builder dataModule(DataModule dataModule);

    }
}
