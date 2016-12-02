package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.BuildConfig;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.AbsDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service.CommonService;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroEntity;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.VodEntity;
import com.jaysen.leagueoflegendmanual.util.MyUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/16.
 * TODO 1.use retrofit request pattern to request remote data and save data to local database.
 */

public class RemoteVodDataSource extends AbsDataSource {
    private Subscription subscription;

    @Inject
    public RemoteVodDataSource() {
    }

    @Override
    public void getDataSource(@NonNull final LoadDataCallback callback) {
        Preconditions.checkNotNull(callback);
        subscription = getService(CommonService.class)
                .getVods()
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (BuildConfig.DEBUG)
                            e.printStackTrace();
                        if (!isUnsubscribed())
                            callback.onDataNotAvailable();
                    }

                    @Override
                    public void onNext(String json) {
//                        Log.d("Remote onenxt", html);
                        Log.i("RemoteHeroDataSource", Thread.currentThread().getName());
                        if (!isUnsubscribed()) {
                            ArrayList<VodEntity> data = parseVodsJson(json);
                            callback.onDataLoaded(data);
//                            callback.onDataLoaded();
                        }
                    }
                });
    }

    private ArrayList<VodEntity> parseVodsJson(String json) {
        // TODO: 2016/12/2  
        return null;
    }

    @Override
    public void unSubscribe() {
        subscription.unsubscribe();
    }


}
