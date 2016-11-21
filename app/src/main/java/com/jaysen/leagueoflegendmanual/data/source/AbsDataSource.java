package com.jaysen.leagueoflegendmanual.data.source;

import com.jaysen.leagueoflegendmanual.APP;
import com.jaysen.leagueoflegendmanual.dagger.DataModule;
import com.jaysen.leagueoflegendmanual.data.source.service.RetrofitHandler;

import java.util.Map;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/21.
 */

public abstract class AbsDataSource implements BaseDataSource {

    @Inject
    OkHttpClient       mOkHttpClient;
    @Inject
    Map<Class, String> mClassUrlMap;

    public AbsDataSource() {
    }

    @Override
    public <D> D getService(Class<D> tClass) {
        //required for remote data source,but not required for local data source.
        String url = mClassUrlMap.get(tClass);
        return RetrofitHandler.newInstance(url, mOkHttpClient).create(tClass);
    }

    @Override
    public <T> void saveDataSource(T dataSets) {
        //stub
    }

    @Override
    public void deleteAllLocalDataSource() {
        //stub
    }

    @Override
    public void refreshCache() {
        //stub
    }
}
