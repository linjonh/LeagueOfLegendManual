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

    <D> void getDataSource(@NonNull LoadDataCallback<D> callback);

    void refreshCache();

    /**
     * for rx observable pattern to unSubscribe when end rx lifecycle.
     */
    void unSubscribe();
}
