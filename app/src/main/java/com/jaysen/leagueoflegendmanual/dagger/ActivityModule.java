package com.jaysen.leagueoflegendmanual.dagger;

import dagger.Module;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 */
@Module()
public class ActivityModule {
    public String provideString() {
        return "hello";
    }
}
