package com.jaysen.leagueoflegendmanual;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by lin on 2016/12/21.
 */

public class TestVod30 {
    public static final String BASE_URL="http://www.vod30.com";
    public static final String ajax="http://www.vod30.com/categories/4/?method=ajax&block_id=list_videos_common_videos_list&sort_by=post_date&page=3";
    @Test
    public void testVod30(){
        try {
//           Document doc= Jsoup.connect("http://www.vod30.com/categories/4/")
           Document doc= Jsoup.connect(ajax)
                   .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                   .header("Accept-Encoding","gzip, deflate, sdch")
                   .header("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4")
                   .header("Cache-Control","max-age=0")
//                   .header("Connection","keep-alive")
                   .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
                   .get();
//            Elements els=doc.select(".player-holder");
//            System.out.println(els.get(0));
            System.out.println(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDownloadJs(){
        String jsSrc="/api/player/load?vkey=849b-F9vUmLGWOatgpShiW89GtoHje0QBqmI_pSRnnTr8P_mFQ";
        String loadVod="/api/player/getplayurl?vkey=849b-F9vUmLGWOatgpShiW89GtoHje0QBqmI_pSRnnTr8P_mFQ";
        try{
        Document document=Jsoup.connect(BASE_URL+loadVod)
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
                .get();
            System.out.println(document.select("body").get(0).text());
        } catch(Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
    }
}