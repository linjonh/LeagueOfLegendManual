package com.jaysen.leagueoflegendmanual;

import android.app.Application;

import com.jaysen.leagueoflegendmanual.dagger.ApplicationComponent;
import com.jaysen.leagueoflegendmanual.dagger.DaggerApplicationComponent;


/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 */

public class APP extends Application {
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.create();
        applicationComponent.inject(this);
    }


}
