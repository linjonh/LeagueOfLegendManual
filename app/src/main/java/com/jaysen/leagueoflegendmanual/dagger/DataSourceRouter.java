package com.jaysen.leagueoflegendmanual.dagger;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 *
 * @see ApplicationComponent{@link ApplicationComponent}
 * @deprecated
 */

public class DataSourceRouter {
    private Provider<DataSourceComponent.Builder> mDataSourceComponentProvider;

    @Inject
    DataSourceRouter(Provider<DataSourceComponent.Builder> dataSourceComponentProvider) {
        mDataSourceComponentProvider = dataSourceComponentProvider;
    }

    DataSourceComponent getDataSourceComponent() {
        return mDataSourceComponentProvider.get()
                .dataModule(new DataModule())
                .build();
    }
}
