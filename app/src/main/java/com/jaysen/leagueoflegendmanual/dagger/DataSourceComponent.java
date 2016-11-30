package com.jaysen.leagueoflegendmanual.dagger;

import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.AbsDataSource;
import com.jaysen.leagueoflegendmanual.ui.HeroInfos.HeroListFragment;
import com.jaysen.leagueoflegendmanual.ui.herodetail.HeroDetailInfoActivity;

import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 * data source sub component
 */
@Singleton
@Subcomponent(modules = {DataModule.class})
public interface DataSourceComponent {
    void inject(AbsDataSource source);

    void inject(HeroListFragment heroListFragment);

    void inject(HeroDetailInfoActivity heroDetailInfoActivity);

    @Subcomponent.Builder
    interface Builder {
        DataSourceComponent build();

        Builder dataModule(DataModule dataModule);

    }
}
