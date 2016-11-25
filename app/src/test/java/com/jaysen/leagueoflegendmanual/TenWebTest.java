package com.jaysen.leagueoflegendmanual;

import com.google.common.io.Files;
import com.google.common.io.PatternFilenameFilter;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/25.
 */

public class TenWebTest {
    /**
     * all hero list
     */
    public static final String webURL              = "http://lol.qq.com/web201310/info-heros.shtml";
    /**
     * hero detail info
     */
    public static final String webHERO_Detail_URL  = "http://lol.qq.com/web201310/info-defail.shtml";// +"?id=Ahri"
    /**
     * hero avatar
     */
    public static final String HeroAVATAR_BASE_URL = "http://ossweb-img.qq.com/images/lol/img/champion/";// + hero.image.full"

    /**
     * 皮肤
     */
    public static final String bigskin   = "http://ossweb-img.qq.com/images/lol/web201310/skin/big";//+id+".jpg“
    public static final String smallskin = "http://ossweb-img.qq.com/images/lol/web201310/skin/small";//+id+".jpg“

    /**
     * hero info json
     */
    public static final String heroJsJson = "http://lol.qq.com/biz/hero/";// + heroid + ".js"; heroid为英文名eg:Ahri
    public static final String heroItem   = "http://lol.qq.com/biz/hero/item.js";

    @Test
    public void testHeroDetailWeb() {
        try {
            Connection connection = Jsoup.connect(webHERO_Detail_URL + "?id=Ahri");
            System.out.println("headers" + connection.request().headers());
            Document document = connection.get();
            System.out.println(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHeroInfo() {
        try {
            BufferedReader bufferedReader = Files
                    .newReader(new File("D:\\AndroidStudioProjects\\LeagueOfLegendManual\\app\\src\\test\\java\\com\\jaysen\\leagueoflegendmanual\\" +
                            "championheroJs.json"), Charset.defaultCharset());
            String        tmp;
            StringBuilder stringBuffer = new StringBuilder();
            while ((tmp = bufferedReader.readLine()) != null) {
                stringBuffer.append(tmp);
            }
            JSONObject       jsonObject = new JSONObject(stringBuffer.toString());
            final JSONObject keysObject = jsonObject.getJSONObject("keys");
            //hero number keys JSONObject
            final Iterator<String> keys = keysObject.keys();
            System.out.println("keys: " + keys);

            final JSONObject heroData = jsonObject.getJSONObject("data");

            final OkHttpClient okHttpClient = new OkHttpClient();

            ThreadPoolExecutor   threadPoolExecutor = new ThreadPoolExecutor(4, 8, 3L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
            final CountDownLatch countDownLatch     = new CountDownLatch(keysObject.length());
            while (keys.hasNext()) {
//                threadPoolExecutor.execute(new Runnable() {
//                    @Override
//                    public void run() {
                try {
                    //heroInfo download
//                            synchronized (keys) {
                    String next = keys.next();

                    System.out.println(Thread.currentThread().getName() + " thread id:" + Thread.currentThread().getId() + " next:" + next);
                    String   heroNameId         = keysObject.getString(next);
                    Request  request            = new Request.Builder().url(heroJsJson + heroNameId + ".js").build();
                    Response response           = okHttpClient.newCall(request).execute();
                    String   heroInfoStringBody = response.body().string();
                    byte[]   bytes              = heroInfoStringBody.getBytes();
                    File     heroInfoJsFile     = new File("D:/hero/" + heroNameId + ".js");
                    Files.write(bytes, heroInfoJsFile);

                    //avatar download
                    JSONObject heroItem       = heroData.getJSONObject(heroNameId);
                    String     avatarName     = heroItem.getJSONObject("image").getString("full");
                    Request    avatarRequest  = new Request.Builder().url(HeroAVATAR_BASE_URL + avatarName).build();
                    Response   avatarResponse = okHttpClient.newCall(avatarRequest).execute();
                    byte[]     imgBytes       = avatarResponse.body().bytes();
                    Files.write(imgBytes, new File("D:/hero/" + avatarName));
//                            }
                } catch (JSONException | IOException e1) {
                    e1.printStackTrace();
                }

                countDownLatch.countDown();

//                    }
//                });

            }
            //waite while loop complete
//            countDownLatch.await();
//            threadPoolExecutor.shutdown();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJsFile2JsonFile() {
        File dir = new File("D:\\hero");
        if (dir.isDirectory()) {
            File[] jsFiles = dir.listFiles();
            System.out.println("jsFiles length:"+jsFiles.length);
            if (jsFiles.length>0)
            return;
            for (int i = 0; i < jsFiles.length; i++) {
                try {
                    BufferedReader bufferedReader = Files.newReader(jsFiles[i], Charset.defaultCharset());
                    String         tmp;
                    StringBuilder  stringBuffer   = new StringBuilder();
                    while ((tmp = bufferedReader.readLine()) != null) {
                        stringBuffer.append(tmp);
                    }
                    String content    = stringBuffer.toString();
                    int    equalIndex = content.lastIndexOf("=");
                    content = content.substring(equalIndex + 1);
                    Files.write(content, new File("D:/hero/" + jsFiles[i].getName().replace(".js", "") + ".json"), Charset.defaultCharset());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
