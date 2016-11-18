package com.jaysen.leagueoflegendmanual.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/18.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(Application app);
}
