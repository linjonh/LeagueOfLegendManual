package com.jaysen.leagueoflegendmanual.pattern.clean.data.source.service;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/17.
 */

public class URLAddress {
    public static final String Base_URL                       = "http://www.playappstation.com/gamecenter/";
    public static final String LOL_HERO_LIST_URL              = Base_URL + "hero.json";
    public static final String LOL_HERO_DETAIL_URL            = Base_URL + "heros/";
    public static final String LOL_EQUIPMENT_URL              = Base_URL + "item.json";
    public static final String LOL_summoner_URL               = Base_URL + "item.json";
    //images
    public static final String EQUIPMENT_ImageDl_URL          = Base_URL + "img/item/%1$s.png";//+ id.png
    public static final String SKIN_BIG_ImageDl_URL           = Base_URL + "img/skin/big%1$s.jpg";//+ bigxxxx.jpg or smallxxx.jpg
    public static final String SKIN_SMALL_ImageDl_URL         = Base_URL + "img/skin/small%1$s.jpg";//+ bigxxxx.jpg or smallxxx.jpg
    public static final String SUMMONER_ID_ImageDl_URL        = Base_URL + "img/summoner/%1s";//+ summonerId.png or descKey.jpg
    public static final String SUMMONER_DESC_ImageDl_URL      = Base_URL + "img/summoner/%1s.jpg";//+ summonerId.png or descKey.jpg
    public static final String HERO_AVATAR_ImageDl_URL        = Base_URL + "img/%1$s";//+ nameId.png
    public static final String HERO_SKILL_PASSIVE_ImageDl_URL = Base_URL + "img/passive/%1$s";//+ nameId.png
    public static final String HERO_SKILL_ImageDl_URL         = Base_URL + "img/skill/%1$s";//+ nameId.png

}
