package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.BuildConfig;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.AbsDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service.CommonService;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.EquipmentEntity;
import com.jaysen.leagueoflegendmanual.util.MyUtils;

import org.json.JSONArray;
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

public class RemoteEquipmentDataSource extends AbsDataSource {
    private Subscription subscription;

    @Inject
    public RemoteEquipmentDataSource() {
    }

    @Override
    public void getDataSource(@NonNull final LoadDataCallback callback) {
        Preconditions.checkNotNull(callback);
        subscription = getService(CommonService.class)
                .getEquipmentsList()
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
                            ArrayList<EquipmentEntity> data = parseEquipments(json);
                            callback.onDataLoaded(data);
//                            callback.onDataLoaded();
                        }
                    }


                });
    }

    private ArrayList<EquipmentEntity> parseEquipments(String json) {
        //  2016/12/2
        ArrayList<EquipmentEntity> entities = new ArrayList<>();
        try {
            JSONObject       jsonObject = new JSONObject(json);
            JSONObject       data       = jsonObject.getJSONObject("data");
            Iterator<String> keys       = data.keys();
            while (keys.hasNext()) {
                String          key             = keys.next();
                JSONObject      item            = data.getJSONObject(key);
                EquipmentEntity equipmentEntity = new EquipmentEntity();
                equipmentEntity.setName(item.getString("name"));
                equipmentEntity.setEquipmentId(key);
                equipmentEntity.setDescription(item.getString("description"));
                equipmentEntity.setPlaintext(item.getString("plaintext"));
                JSONObject gold = item.getJSONObject("gold");
                equipmentEntity.setGoldBase(gold.getInt("base"));
                equipmentEntity.setGoldSell(gold.getInt("sell"));
                equipmentEntity.setGoldTotal(gold.getInt("total"));
                equipmentEntity.setTags(MyUtils.getAppandedString(item.getJSONArray("tags")));
                equipmentEntity.setImage(item.getJSONObject("image").getString("full"));
                if (item.has("into")) {
                    JSONArray into = item.getJSONArray("into");
                    equipmentEntity.setInto(MyUtils.getAppandedString(into));
                }
                if (item.has("from")) {
                    JSONArray from = item.getJSONArray("from");
                    equipmentEntity.setFrom(MyUtils.getAppandedString(from));
                }
                entities.add(equipmentEntity);
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
        return entities;
    }

    @Override
    public void unSubscribe() {
        subscription.unsubscribe();
    }


}
