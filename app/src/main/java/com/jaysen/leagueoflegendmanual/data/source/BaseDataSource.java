package com.jaysen.leagueoflegendmanual.data.source;

import android.support.annotation.NonNull;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/18.
 * TODO: 2016/11/18 define pattern for common case to use
 */

public interface BaseDataSource {

    interface LoadDataCallback<D> {

        void onDataLoaded(D data);

        void onDataNotAvailable();
    }

    <T> T getService(Class<T> tClass);

    void getDataSource(@NonNull LoadDataCallback callback);

    void refreshcache();
}
