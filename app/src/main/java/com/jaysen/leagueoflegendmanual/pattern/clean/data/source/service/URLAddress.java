package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/17.
 */

public class URLAddress {
    public static final String Base_URL                = "http://www.playappstation.com/";
    public static final String LOL_HERO_LIST_URL       = Base_URL + "hero.json";
    public static final String LOL_HERO_DETAIL_URL     = Base_URL + "heros/";
    public static final String LOL_EQUIPMENT_URL       = Base_URL + "item.json";
    public static final String LOL_summoner_URL        = Base_URL + "item.json";
    //images
    public static final String EQUIPMENT_ImageDl_URL   = Base_URL + "img/item/";//+ id.png
    public static final String SKIN_ImageDl_URL        = Base_URL + "img/skin/";//+ bigxxxx.jpg or smallxxx.jpg
    public static final String SUMMONER_ImageDl_URL    = Base_URL + "img/summoner/";//+ summonerId.png or descKey.jpg
    public static final String HERO_AVATAR_ImageDl_URL = Base_URL + "img/";//+ nameId.png

}
