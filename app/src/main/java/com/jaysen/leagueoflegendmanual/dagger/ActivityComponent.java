package com.jaysen.leagueoflegendmanual.dagger;

import dagger.Subcomponent;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 */
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    @Subcomponent.Builder
    interface Builder {
        Builder activityModule(ActivityModule activityModule);

        ActivityComponent build();
    }
}
