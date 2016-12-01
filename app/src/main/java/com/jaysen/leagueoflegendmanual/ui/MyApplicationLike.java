package com.jaysen.leagueoflegendmanual.ui;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jaysen.leagueoflegendmanual.dagger.AppModule;
import com.jaysen.leagueoflegendmanual.dagger.ApplicationComponent;
import com.jaysen.leagueoflegendmanual.dagger.DaggerApplicationComponent;
import com.jaysen.leagueoflegendmanual.dagger.DataModule;
import com.jaysen.leagueoflegendmanual.dagger.DataSourceComponent;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.app.ApplicationLifeCycle;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by jaysen.lin@foxmail.com on 2016/12/1.
 * * because you can not use any other class in your application, we need to
 * move your implement of Application to {@link ApplicationLifeCycle}
 * As Application, all its direct reference class should be in the main dex.
 * <p>
 * We use tinker-android-anno to make sure all your classes can be patched.
 * <p>
 * application: if it is start with '.', we will add SampleApplicationLifeCycle's package name
 * <p>
 * flags:
 * TINKER_ENABLE_ALL: support dex, lib and resource
 * TINKER_DEX_MASK: just support dex
 * TINKER_NATIVE_LIBRARY_MASK: just support lib
 * TINKER_RESOURCE_MASK: just support resource
 * <p>
 * loaderClass: define the tinker loader class, we can just use the default TinkerLoader
 * <p>
 * loadVerifyFlag: whether check files' md5 on the load time, defualt it is false.
 */
@DefaultLifeCycle(
        application = "com.jaysen.leagueoflegendmanual.ui.MyApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)
public class MyApplicationLike extends DefaultApplicationLike {

    public MyApplicationLike(
            Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag,
            long applicationStartElapsedTime, long applicationStartMillisTime,
            Intent tinkerResultIntent, Resources[] resources, ClassLoader[] classLoader,
            AssetManager[] assetManager) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime,
              applicationStartMillisTime, tinkerResultIntent, resources, classLoader, assetManager);
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static DataSourceComponent getDataSourceComponent() {
        return getApplicationComponent()
                .getDataSourceBuilder()
                .dataModule(new DataModule())
                .build();
    }

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        applicationComponent.inject(this);
        Fresco.initialize(getApplication());
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallbacks(
            Application.ActivityLifecycleCallbacks callback) {
        getApplication().registerActivityLifecycleCallbacks(callback);
    }
}
