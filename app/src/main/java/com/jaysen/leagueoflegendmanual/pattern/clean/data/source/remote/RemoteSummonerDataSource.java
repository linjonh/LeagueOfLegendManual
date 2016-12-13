package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.BuildConfig;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.AbsDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service.CommonService;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.SummonerSkillEntity;

import org.json.JSONObject;

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

public class RemoteSummonerDataSource extends AbsDataSource {
    private Subscription subscription;

    @Inject
    public RemoteSummonerDataSource() {
    }

    @Override
    public void getDataSource(@NonNull final LoadDataCallback callback) {
        Preconditions.checkNotNull(callback);
        subscription = getService(CommonService.class)
                .getSummonerList()
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
                            ArrayList<SummonerSkillEntity> data = parseSummonerJson(json);
                            callback.onDataLoaded(data);
//                            callback.onDataLoaded();
                        }
                    }
                });
    }

    private ArrayList<SummonerSkillEntity> parseSummonerJson(String json) {
        //  2016/12/3
        ArrayList<SummonerSkillEntity> vodEntities = new ArrayList<>();
        try {
            JSONObject       jsonObject = new JSONObject(json);
            JSONObject       data       = jsonObject.getJSONObject("data");
            Iterator<String> keys       = data.keys();
            while (keys.hasNext()) {
                String              key             = keys.next();
                JSONObject          item            = data.getJSONObject(key);
                SummonerSkillEntity entity          = new SummonerSkillEntity();
                entity.setName(item.getString("name"));
                entity.setNameId(key);
                entity.setDescription(item.getString("description"));
                entity.setKey(item.getString("key"));
                entity.setMaxrank(item.getInt("maxrank"));
                entity.setImage(item.getJSONObject("image").getString("full"));

                vodEntities.add(entity);
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
        return vodEntities;
    }

    @Override
    public void unSubscribe() {
        subscription.unsubscribe();
    }


}
