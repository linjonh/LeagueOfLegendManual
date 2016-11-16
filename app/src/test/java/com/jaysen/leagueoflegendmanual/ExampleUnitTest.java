package com.jaysen.leagueoflegendmanual;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.jaysen.leagueoflegendmanual.data.source.DaoMaster;
import com.jaysen.leagueoflegendmanual.data.source.DaoSession;
import com.jaysen.leagueoflegendmanual.data.source.HeroEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void GetLegendInfo() {
        try {
            Document              document       = Jsoup.connect("http://lol.duowan.com/hero/").get();
            Elements              els            = document.select(".champion_tooltip");
            ArrayList<HeroEntity> heroEntityList = new ArrayList<>();
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
            Gson   gson = new Gson();
            String json = gson.toJson(heroEntityList);
            Files.write(json, new File("D:/hero.json"), Charset.defaultCharset());
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}