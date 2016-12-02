package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.remote;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.BuildConfig;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.AbsDataSource;
import com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service.CommonService;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroDetailInfoEntity;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Allytips;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.AttributeInfo;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Enemytips;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.EquipmentRecommend;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Skins;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.herodetailinfo.Spells;
import com.jaysen.leagueoflegendmanual.util.MyUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/29.
 * 远程数据
 */

public class RemoteHeroDetailDataSource extends AbsDataSource {
    @Inject
    RemoteHeroDetailDataSource() {

    }

    private Subscription subscription;

    public void setHeroNameID(String heroNameID) {
        this.heroNameID = heroNameID;
    }

    private String heroNameID;

    @Override
    public void getDataSource(@NonNull final LoadDataCallback callback) {
        Preconditions.checkNotNull(heroNameID);
        Preconditions.checkNotNull(callback);
        subscription = getService(CommonService.class)
                .getHeroDetailInfo(heroNameID)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        // STOPSHIP: 2016/11/29
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (BuildConfig.DEBUG) e.printStackTrace();
                        if (!isUnsubscribed()) {
                            callback.onDataNotAvailable();
                        }
                    }

                    @Override
                    public void onNext(String s) {
                        if (!isUnsubscribed()) {
                            callback.onDataLoaded(parseHeroDetailInfo(s));
                        }
                    }
                });
    }

    private HeroDetailInfoEntity parseHeroDetailInfo(String json) {
        HeroDetailInfoEntity heroDetailInfoEntity = new HeroDetailInfoEntity();
        try {
            Preconditions.checkNotNull(json);
            JSONObject jsonObject = new JSONObject(json);
            JSONObject data       = jsonObject.getJSONObject("data");
            //avatar name title nameId tags description attributeInfo
            heroDetailInfoEntity.nameId = data.getString("id");
            heroDetailInfoEntity.avatarUrl = data.getJSONObject("image").getString("full");
            heroDetailInfoEntity.legendName = data.getString("name");
            heroDetailInfoEntity.legendTitle = data.getString("title");
            JSONArray     tags          = data.getJSONArray("tags");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < tags.length(); i++) {
                stringBuilder.append(tags.getString(i));
                if (i < tags.length() - 1) {
                    stringBuilder.append("$");
                }
            }
            heroDetailInfoEntity.tags = stringBuilder.toString();
            heroDetailInfoEntity.description = data.getString("lore");
            AttributeInfo attributeInfo = new AttributeInfo();
            JSONObject    info          = data.getJSONObject("info");
            attributeInfo.attack = info.getInt("attack");
            attributeInfo.defense = info.getInt("defense");
            attributeInfo.magic = info.getInt("magic");
            attributeInfo.difficulty = info.getInt("difficulty");
            heroDetailInfoEntity.attributeInfo = attributeInfo;
            //skins
            JSONArray skinsArray = data.getJSONArray("skins");
            heroDetailInfoEntity.skinIdNames = new ArrayList<>();
            for (int i = 0; i < skinsArray.length(); i++) {
                Skins skins = new Skins();
                skins.skinId = skinsArray.getJSONObject(i).getString("id");
                skins.name = skinsArray.getJSONObject(i).getString("name");
                heroDetailInfoEntity.skinIdNames.add(skins);
            }

            Spells passiveSpells = new Spells();
            //spells passive
            JSONObject spellsPassive = data.getJSONObject("passive");
            passiveSpells.name = spellsPassive.getString("name");
            passiveSpells.description = spellsPassive.getString("description");
            passiveSpells.imageName = spellsPassive.getJSONObject("image").getString("full");
            heroDetailInfoEntity.spellsList = new ArrayList<>();
            heroDetailInfoEntity.spellsList.add(passiveSpells);
            //spells
            JSONArray spellsArray = data.getJSONArray("spells");
            for (int i = 0; i < spellsArray.length(); i++) {
                Spells     spells       = new Spells();
                JSONObject spellsObject = spellsArray.getJSONObject(i);
                spells.name = spellsObject.getString("name");
                spells.description = spellsObject.getString("description");
                spells.imageName = spellsObject.getJSONObject("image").getString("full");
                spells.tooltip = spellsObject.getString("tooltip");
                JSONArray jsonArray = spellsObject.getJSONObject("leveltip").getJSONArray("label");
                JSONArray effects   = spellsObject.getJSONObject("leveltip").getJSONArray("effect");
                //leveltipLabel
                spells.leveltipLabel = MyUtils.getAppandedString(jsonArray);
                //leveltipEffect
                spells.leveltipEffect = MyUtils.getAppandedString(effects);

                heroDetailInfoEntity.spellsList.add(spells);
            }
            //allytips
            heroDetailInfoEntity.allytips = new ArrayList<>();
            JSONArray allytips = data.getJSONArray("allytips");
            for (int i = 0; i < allytips.length(); i++) {
                Allytips tipItem = new Allytips();
                tipItem.tip = allytips.getString(i);
                heroDetailInfoEntity.allytips.add(tipItem);
            }
            //ememyTips
            heroDetailInfoEntity.enemytips = new ArrayList<>();
            JSONArray enemytips = data.getJSONArray("enemytips");
            for (int i = 0; i < enemytips.length(); i++) {
                Enemytips tipItem = new Enemytips();
                tipItem.tip = allytips.getString(i);
                heroDetailInfoEntity.enemytips.add(tipItem);
            }

            //recommend1
            JSONArray blocks = data.getJSONArray("blocks");
            heroDetailInfoEntity.recommend1 = new ArrayList<>();
            //recommend2
            heroDetailInfoEntity.recommend2 = new ArrayList<>();
            if (blocks.length() == 2) {
                setRecommendEquipment(heroDetailInfoEntity, blocks, 0);
                setRecommendEquipment(heroDetailInfoEntity, blocks, 1);
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
        return heroDetailInfoEntity;
    }

    /**
     * @param heroDetailInfoEntity
     * @param blocks
     * @param index
     * @throws JSONException
     */
    private void setRecommendEquipment(
            HeroDetailInfoEntity heroDetailInfoEntity, JSONArray blocks,
            int index) throws JSONException {
        JSONObject jsonObject = blocks.getJSONObject(index);
        String     mode       = jsonObject.getString("mode");
        JSONArray  jsonArray  = jsonObject.getJSONArray("recommended");
        for (int i = 0; i < jsonArray.length(); i++) {
            EquipmentRecommend equipmentRecommend = new EquipmentRecommend();
            JSONObject         item               = jsonArray.getJSONObject(i);
            equipmentRecommend.type = item.getString("type");
            equipmentRecommend.mode = mode;
            JSONArray     items    = item.getJSONArray("items");
            StringBuilder itemsStr = new StringBuilder();

            for (int j = 0; j < items.length(); j++) {
                String id = items.getJSONObject(j).getString("id");
                itemsStr.append(id);
                if (j < items.length() - 1) {
                    itemsStr.append("$");
                }
            }
            equipmentRecommend.equipmentIds = itemsStr.toString();
            switch (index) {
                case 0:
                    heroDetailInfoEntity.recommend1.add(equipmentRecommend);
                    break;
                case 1:
                    heroDetailInfoEntity.recommend2.add(equipmentRecommend);
                    break;
            }
        }
    }


    @Override
    public void unSubscribe() {
        subscription.unsubscribe();
    }
}
