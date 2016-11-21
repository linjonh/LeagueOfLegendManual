package com.jaysen.leagueoflegendmanual.data.source.remote;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.BuildConfig;
import com.jaysen.leagueoflegendmanual.data.source.AbsDataSource;
import com.jaysen.leagueoflegendmanual.data.source.service.HeroService;
import com.jaysen.leagueoflegendmanual.domain.model.HeroEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;

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
                    public void onNext(String html) {
                        if (!isUnsubscribed()) {
                            callback.onDataLoaded(parseData(html));
                        }
                    }
                });
    }

    @Override
    public void unSubscribe() {
        subscription.unsubscribe();
    }

    private ArrayList<HeroEntity> parseData(String html) {
        Document              document       = Jsoup.parse(html);
        ArrayList<HeroEntity> heroEntityList = new ArrayList<>();
        Preconditions.checkNotNull(document);
        try {
            Elements els = document.select(".champion_tooltip");
            for (Element el : els) {
                HeroEntity heroEntity = new HeroEntity();
                heroEntity.avatarUrl = el.select("img").attr("src");
                heroEntity.legendName = el.select(".champion_name").text();
                heroEntity.legendTitle = el.select(".champion_title").text();
                heroEntity.description = el.select("p").text();
                heroEntity.tags = el.select(".champion_tooltip_tags").text().replace("tags:", "").trim();
//                System.out.println(heroEntity + "\n");
                heroEntityList.add(heroEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return heroEntityList;
    }
}
