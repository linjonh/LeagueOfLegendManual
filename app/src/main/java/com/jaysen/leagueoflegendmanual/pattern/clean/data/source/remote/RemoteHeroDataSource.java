package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.BuildConfig;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.AbsDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service.HeroService;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroEntity;
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

public class RemoteHeroDataSource extends AbsDataSource {
    private Subscription subscription;

    @Inject
    public RemoteHeroDataSource() {
    }

    @Override
    public void getDataSource(@NonNull final LoadDataCallback callback) {
        Preconditions.checkNotNull(callback);
        subscription = getService(HeroService.class)
                .getHeroEntities()
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (BuildConfig.DEBUG)
                            e.printStackTrace();
                        callback.onDataNotAvailable();
                    }

                    @Override
                    public void onNext(String json) {
//                        Log.d("Remote onenxt", html);
                        Log.i("RemoteHeroDataSource", Thread.currentThread().getName());
                        if (!isUnsubscribed()) {
                            ArrayList<HeroEntity> data = parseHeroListJson(json);
                            callback.onDataLoaded(data);
//                            callback.onDataLoaded();
                        }
                    }
                });
    }

    @Override
    public void unSubscribe() {
        subscription.unsubscribe();
    }

    /**
     * parse my web site
     *
     * @param json resource
     * @return data list
     */
    private ArrayList<HeroEntity> parseHeroListJson(String json) {
        ArrayList<HeroEntity> heroEntityList = new ArrayList<>();
        try {
            JSONObject       jsonObject = new JSONObject(json);
            final JSONObject keysObject = jsonObject.getJSONObject("keys");
            //hero number keys JSONObject
            final Iterator<String> keys = keysObject.keys();
            System.out.println("keys: " + keys);

            final JSONObject heroData = jsonObject.getJSONObject("data");
            while (keys.hasNext()) {
                String     next       = keys.next();
                String     heroNameId = keysObject.getString(next);
                HeroEntity heroEntity = new HeroEntity();
                //avatar download
                JSONObject heroItem = heroData.getJSONObject(heroNameId);
                heroEntity.legendName = heroItem.getString("name");
                heroEntity.legendTitle = heroItem.getString("title");
                JSONArray tags = heroItem.getJSONArray("tags");
                heroEntity.tags = MyUtils.getAppandedString(tags);
                heroEntity.nameId = heroNameId;
                heroEntity.avatarUrl = heroItem.getJSONObject("image").getString("full");

                heroEntityList.add(heroEntity);
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
        return heroEntityList;
    }

    /**
     * duowan web data
     *
     * @param html html document
     * @return
     * @deprecated use {@link #parseHeroListJson(String)}
     */
    private ArrayList<HeroEntity> parseData(String html) {
        Document              document       = Jsoup.parse(html);
        ArrayList<HeroEntity> heroEntityList = new ArrayList<>();
        Preconditions.checkNotNull(document);
        try {
            Elements els = document.select(".champion_tooltip");
            for (Element el : els) {
                HeroEntity heroEntity = new HeroEntity();
                heroEntity.id = null;
                heroEntity.avatarUrl = el.select("img").attr("src").trim();
                heroEntity.legendName = el.select(".champion_name").text().trim();
                heroEntity.legendTitle = el.select(".champion_title").text().trim();
//                heroEntity.description = el.select("p").text().trim();
                heroEntity.tags = el.select(".champion_tooltip_tags").text().replace("tags:", "").replace("Tags:", "").trim();
//                System.out.println(heroEntity + "\n");
                heroEntityList.add(heroEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return heroEntityList;
    }
}
