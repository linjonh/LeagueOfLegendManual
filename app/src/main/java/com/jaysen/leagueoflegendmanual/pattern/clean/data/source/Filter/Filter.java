package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.filter;

import android.support.annotation.Nullable;

import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.BaseDataSource;

public interface Filter<T> {
    void filter(String nameId, @Nullable BaseDataSource.LoadDataCallback<T> callback);
}