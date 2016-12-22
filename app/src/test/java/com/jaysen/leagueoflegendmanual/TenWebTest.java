package com.jaysen.leagueoflegendmanual;

import android.support.annotation.NonNull;
import android.util.Base64;

import com.google.common.io.Files;

import org.json.JSONArray;
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

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by jaysen.lin@foxmail.com on 2016/11/25.
 */

public class TenWebTest {
    /**
     * all hero list
     */
    public static final String web_URL             = "http://lol.qq.com/web201310/info-heros.shtml";
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
    public static final String bigskin_URL   = "http://ossweb-img.qq.com/images/lol/web201310/skin/big";//+id+".jpg“
    public static final String smallskin_URL = "http://ossweb-img.qq.com/images/lol/web201310/skin/small";//+id+".jpg“

    /**
     * hero info json
     */
    public static final String heroJsJson_URL = "http://lol.qq.com/biz/hero/";// + heroid + ".js"; heroid为英文名eg:Ahri
    public static final String heroItem_URL   = "http://lol.qq.com/biz/hero/item.js";

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
    public void testCreateParentDirs() {
        File heroInfoJsFile = new File("D:/test/temp/mytest/tessda/");
        try {
            Files.createParentDirs(heroInfoJsFile);
            Files.createParentDirs(heroInfoJsFile);
            Files.createParentDirs(heroInfoJsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * download hero info js 文件保存到D盘的hero文件夹
     */
    @Test
    public void testHeroInfo() throws IOException {
        String content = getHttpContent("http://www.playappstation.com/gamecenter/hero.json");

        try {
//            StringBuilder stringBuffer = getStringBuilder(new File("D:\\ASProject\\LeagueOfLegendManual\\app\\src\\test\\java\\com\\jaysen\\leagueoflegendmanual\\" +
//                    "championheroJs.json"));
//            JSONObject       jsonObject = new JSONObject(stringBuffer.toString());
            JSONObject       jsonObject = new JSONObject(content.trim());
            final JSONObject keysObject = jsonObject.getJSONObject("keys");
            //hero number keys JSONObject
            final Iterator<String> keys = keysObject.keys();
            System.out.println("keys: " + keys);

            final JSONObject heroData = jsonObject.getJSONObject("data");

            final OkHttpClient okHttpClient = new OkHttpClient();

//            ThreadPoolExecutor   threadPoolExecutor = new ThreadPoolExecutor(4, 8, 3L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
//            final CountDownLatch countDownLatch     = new CountDownLatch(keysObject.length());
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
                    Request  request            = new Request.Builder().url(heroJsJson_URL + heroNameId + ".js").build();
                    Response response           = okHttpClient.newCall(request).execute();
                    String   heroInfoStringBody = response.body().string();
                    byte[]   bytes              = heroInfoStringBody.getBytes();
                    String   pathname           = "D:/hero/" + heroNameId + ".js";
                    File     heroInfoJsFile     = new File(pathname);
                    Files.createParentDirs(heroInfoJsFile);
                    Files.write(bytes, heroInfoJsFile);

                    //avatar download
                    JSONObject heroItem       = heroData.getJSONObject(heroNameId);
                    String     avatarName     = heroItem.getJSONObject("image").getString("full");
                    Request    avatarRequest  = new Request.Builder().url(HeroAVATAR_BASE_URL + avatarName).build();
                    Response   avatarResponse = okHttpClient.newCall(avatarRequest).execute();
                    byte[]     imgBytes       = avatarResponse.body().bytes();
                    File       to             = new File("D:/hero/" + avatarName);
                    Files.createParentDirs(to);
                    Files.write(imgBytes, to);
//                            }
                } catch (JSONException | IOException e1) {
                    e1.printStackTrace();
                }

//                countDownLatch.countDown();

//                    }
//                });

            }
            //waite while loop complete
//            countDownLatch.await();
//            threadPoolExecutor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把js里的heroInfo改为JSON格式文件，去掉一些js的语法文字
     */
    @Test
    public void testJsFile2JsonFile() {
        File dir = new File("D:\\hero");
        if (dir.isDirectory()) {
            File[] jsFiles = dir.listFiles();
            System.out.println("jsFiles length:" + jsFiles.length);
            for (int i = 0; i < jsFiles.length; i++) {
                String name = jsFiles[i].getName();
                System.out.println("name: " + name);
                if (!name.contains(".js")) {
                    System.out.println("skipped: " + name);
                    continue;
                }
                name = name.replace(".js", "");
                System.out.println("i:" + i + " name: " + name);
                try {
                    StringBuilder stringBuffer = getStringBuilder(jsFiles[i]);
                    String        content      = stringBuffer.toString();
                    String        str          = name + "=";//Ahri=
                    int           equalIndex   = content.lastIndexOf(str);
                    content = content.substring(equalIndex + str.length(), content.length() - 1);//delete the “;” end of file
                    Files.write(content, new File("D:/hero/" + jsFiles[i].getName().replace(".js", "") + ".json"), Charset.defaultCharset());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @NonNull
    private static StringBuilder getStringBuilder(String fileName) throws IOException {
        return getStringBuilder(new File(fileName));
    }

    @NonNull
    private static StringBuilder getStringBuilder(File jsFile) throws IOException {
        BufferedReader bufferedReader = Files.newReader(jsFile, Charset.defaultCharset());
        String         tmp;
        StringBuilder  stringBuffer   = new StringBuilder();
        while ((tmp = bufferedReader.readLine()) != null) {
            stringBuffer.append(tmp);
        }
        return stringBuffer;
    }

    public static String getReadedFileString(String fileName) throws IOException {
        return getStringBuilder(fileName).toString();
    }

    /**
     * get real VOD url
     */
    public static String vodUrl = "http://vv.video.qq.com/geturl?vid=%s&otype=json&platform=11";//&ran=0%2E9652906153351068";

    @Test
    public void testDownloadVodUrl() {
        String vodSrcFiledir = "D:\\ASProject\\LeagueOfLegendManual\\app\\src\\test\\java\\com\\jaysen\\leagueoflegendmanual\\LOL_vod.json";
        try {
            String           jsonStr    = getReadedFileString(vodSrcFiledir);
            JSONObject       jsonObject = new JSONObject(jsonStr);
            JSONObject       dataObject = jsonObject.getJSONObject("data");
            Iterator<String> keys       = dataObject.keys();
            while (keys.hasNext()) {
                JSONObject heroItem = dataObject.getJSONObject(keys.next());
                String     vodlink  = heroItem.getString("vodlink");

//                String encodeUrl = URLEncoder.encode(vodlink, "utf-8");
//                System.out.println("encodeUrl: " + encodeUrl);

                System.out.println("vodlink: " + vodlink);
                String vid;
//                if (vid == null) {
//                    continue;
//                }
                if (vodlink.contains("qq.com")) {
                    vid = vodlink.substring(vodlink.indexOf("=") + 1);
                } else {
                    continue;
                }
                String responseStr = getResponse(vid).body().string();
                System.out.println("responseStr: " + responseStr);

//                String url = String.format(Locale.getDefault(), vodUrl, vid);
//                String doc = getHttpContent(url);
//                System.out.println("url: " + url);
//                System.out.println(doc.toString());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getHttpContent(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        return okHttpClient.newCall(new Request.Builder().url(url)
//                .header("Cookie", "eas_sid=e1s436W077X9x4n941t31279w7&tvfe_boss_uuid=8b5ed5ce863dd50d&ptui_loginuin=783036250&_gscu_661903259=768841171y6rio12&pac_uid=1_783036250&pt2gguin=o0783036250&RK=TNmnInsmH+&ptcz=245a8392689b240b1f168e4fa49c2f3e46b74fbc2d285b3c3ae2927c2f51e1bd&guid=10z157z52z310123456789qwertyuiop&o_cookie=783036250&pgv_pvid=9588289676&")
                .build()).execute().body().string();
    }

    @Test
    public void testBase64() throws IOException {
        String filString = getReadedFileString("C:\\Users\\lin\\Documents\\youKY_mplayAPI.json");
        System.out.println(Base64.decode(filString, 0));
    }

    @Test
    public void dlExe() {
//        String url="http://fiddler2.com/r/?SYNTAXVIEWINSTALL";
        String       url          = "http://v.youku.com/player/getRealM3U8/vid/XNDU1NjE4Nzg0/type/mp4/v.m3u8";
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            byte[] data = okHttpClient.newCall(new Request.Builder().url(url).build()).execute().body().bytes();
            File   to   = new File("D:/FFFFFFFFF/m3u.m3u");
            Files.createParentDirs(to);
            Files.write(data, to);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //post
    public static final String getvInfo_URL = "http://vv.video.qq.com/getvinfo";
    public static final String getvkey_URL  = "http://vv.video.qq.com/getvkey";

    @Test
    public void testGetVkeyAndInfo() throws IOException {
//        Response response = getResponse();
//        System.out.println("xml: " + response.body().string());
    }

    private Response getResponse(String vid) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        String       clientKey    = "cKey=LUtu8mrMwljXMHHVT5KouiXQjMKUWfOmavz1BzGD8m6%5FU6C1OPeUrr59g5hilRB3j4iDJHZ2R0Z0k3HCkBF7cIVwNfSa6GzALu3We9VZuYAyYcHRu2iyT1OaJ0QGX2ym%5FpTkHdM8Pr3knoy41jhEqaSgvqa2oeuoD9HuKFd82ZizFptdxiWjEzXh8zCcekC99dfyVbamAAszfyU6bWyfzfEvgDduFYYRugLYClWKPhxTi13Jq9FKEhBNUGEhVJ9Lp4jYSyHCeAhS5SQ7";
        String bodystring = "&otype=xml" + clientKey +
                "&format=2" +
                "&encryptVer=5%2E4" +
                "&appver=3%2E2%2E19%2E358" +
                "&ehost=http%3A%2F%2Fstatic%2Evideo%2Eqq%2Ecom%2Fv1%2Fres%2Fqqplayerout%2Eswf%3Fvid%3D8ga0GW0IhBZ" +
                "&vid=8ga0GW0IhBZ&platform=70202" +
                "&ran=0%2E5003935750573874" +
                "&vt=200&linkver=2" +
                "&filename=8ga0GW0IhBZ%2Emp4" +
                "&charge=0" +
                "&guid=AD27B3A2CE692C4210C9BC3DAD6FD7833C2E2FFD";
        String nolineString =
                "fhdswitch=0&speed=3328&encryptVer=5%2E4&utype=%2D1&ip=&newplatform=70202&fp2p=1&refer=&defnpayver=1&appver=3%2E2%2E19%2E358" +
                        "&vids=" + vid +
                        "&ran=0%2E35083639482036233" +
                        "&vid=" + vid +
                        "&otype=xml&platform=70202&linkver=2&charge=0&ehost=&dtype=3&cKey=pfc9C%5FJwkaFM3tUQkZxKTpC3AG6Q2ihn2BY9b5VufqaeHfp0sR7JMe%2DHmzCN%5FXaXlsZwXe9FSzOEzYdqXXyK37o8cVpupzyMEl6400voFzLrADTFWihNx%2DHy966FsYSwWyOfpoUxtOu5pNkCGMDsUZtW3LNKdtPPexzPCtpT6nX%5Fye%2DfI98j5knYiANtpc19r6j9cLxPgwch2Haf8OgKPpzD8E0irFN3KjB8qMX3I9hD8btF7bIHFZfjBuZEIpyMH2URW0xtNR8nQqPr&pid=C05B5B2ACC7D018071BA5AB104E8607051F8B883&guid=A587100EB4470E020801AF76185ACF2928CF3B82";
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), bodystring);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), nolineString);
        return okHttpClient.newCall(new Request.Builder().url(getvInfo_URL)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .post(requestBody)
                .build()).execute();
    }


    public static final String itemImageDl_URL = "http://ossweb-img.qq.com/images/lol/img/item/";//+item img name

    @Test
    public void downloadEquipmentImgs() throws IOException, JSONException {
        OkHttpClient okHttpClient = new OkHttpClient();

        String           json       = getReadedFileString("D:\\hero\\game_data\\item.json");
        JSONObject       jsonObject = new JSONObject(json);
        JSONObject       data       = jsonObject.getJSONObject("data");
        Iterator<String> keys       = data.keys();
        while (keys.hasNext()) {
            JSONObject equip    = data.getJSONObject(keys.next());
            String     imgName  = equip.getJSONObject("image").getString("full");
            Request    request  = new Request.Builder().url(itemImageDl_URL + imgName).build();
            byte[]     imgBytes = okHttpClient.newCall(request).execute().body().bytes();
            File       to       = new File("D:\\hero\\game_data\\img\\item\\" + imgName);
            Files.createParentDirs(to);
            Files.write(imgBytes, to);
        }
    }

    @Test
    public void downloadHeroSikinImgs() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)
                .connectTimeout(3, TimeUnit.SECONDS)
                .build();
        File   fileDir = new File("D:\\hero\\game_data\\heros");
        File[] files   = fileDir.listFiles();
        String json;
        int    j       = 0;


        for (File file : files) {

            if (file.isDirectory()) {
                continue;
            }
            System.out.println(String.format("%4d: read file:" + file.getName(), j++));
            try {
                json = getStringBuilder(file).toString();//hero file json


                JSONObject jsonObject = new JSONObject(json);
                JSONObject data       = jsonObject.getJSONObject("data");
                JSONArray  skins      = data.getJSONArray("skins");
                for (int i = 0; i < skins.length(); i++) {
                    final int    k      = i;
                    final String skinId = skins.getJSONObject(i).getString("id");
                    final String name   = skins.getJSONObject(i).getString("name");


                    final File tobig = new File("D:\\hero\\game_data\\img\\skin\\big" + skinId + ".jpg");
                    if (!tobig.exists()) {
                        Request requestBigSkin = new Request.Builder().url(bigskin_URL + skinId + ".jpg").build();
                        Observable.just(okHttpClient.newCall(requestBigSkin).execute())
                                .retry(3)
                                .subscribe(new Action1<Response>() {
                                    @Override
                                    public void call(Response response) {
                                        try {
                                            byte[] bigImgBytes = response.body().bytes();
                                            Files.createParentDirs(tobig);
                                            Files.write(bigImgBytes, tobig);
                                            System.out.println(String.format("%4d:save big skin id:" + skinId + " name: " + name + ".jpg", k));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });


                    }
                    final File tosmall = new File("D:\\hero\\game_data\\img\\skin\\small" + skinId + ".jpg");
                    if (!tosmall.exists()) {
                        Request requestSmallSkin = new Request.Builder().url(smallskin_URL + skinId + ".jpg").build();
                        Observable.just(okHttpClient.newCall(requestSmallSkin).execute())
                                .retry(3)
                                .subscribe(new Action1<Response>() {
                                    @Override
                                    public void call(Response response) {
                                        try {
                                            byte[] smallImgBytes = response.body().bytes();
                                            Files.write(smallImgBytes, tosmall);
                                            System.out.println(String.format("%4d:save samll skin id:" + skinId + " name: " + name + ".jpg", k));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }

                                });
                    }
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void testGithubPageSource() throws IOException {
        String content = getHttpContent("http://www.playappstation.com/gamecenter/hero.json");
        System.out.println("content: " + content);
    }

    //召唤师技能图片
    public static final String summoner_URL      = "http://ossweb-img.qq.com/images/lol/img/spell/";//+image.full
    //召唤师技能介绍图片
    public static final String summoner_desc_URL = "http://ossweb-img.qq.com/images/lol/web201310/summoner/";//'+d.key+'.jpg

    private byte[] downloadImage(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        return okHttpClient.newCall(new Request.Builder().url(url).build()).execute().body().bytes();
    }

    @Test
    public void testSummoner() throws IOException, JSONException {
        String content = getHttpContent("http://www.playappstation.com/gamecenter/summoner.json");

        JSONObject       jsonObject = new JSONObject(content);
        JSONObject       data       = jsonObject.getJSONObject("data");
        Iterator<String> keys       = data.keys();
        while (keys.hasNext()) {
            JSONObject skillItem    = data.getJSONObject(keys.next());
            String     descImageId  = skillItem.getString("key");
            String     skillImgName = skillItem.getJSONObject("image").getString("full");

            File to       = new File("D:\\hero\\game_data\\img\\summoner\\" + skillImgName);
            File descFile = new File("D:\\hero\\game_data\\img\\summoner\\" + descImageId + ".jpg");
            Files.createParentDirs(to);
            Files.write(downloadImage(summoner_URL + skillImgName), to);
            Files.write(downloadImage(summoner_desc_URL + descImageId + ".jpg"), descFile);

        }

    }

    public static final String SKILL_IMG_URL  = "http://ossweb-img.qq.com/images/lol/img/passive/";
    public static final String SKILLs_IMG_URL = "http://ossweb-img.qq.com/images/lol/img/spell/";

    @Test
    public void testSkillDl() throws IOException, JSONException {
        File   dir   = new File("D:\\hero\\game_data\\heros");
        File[] files = dir.listFiles();
        for (int j = 0; j < files.length; j++) {
            File       file             = files[j];
            String     json             = getReadedFileString(file.getCanonicalPath());
            JSONObject hero             = new JSONObject(json);
            String     passiveImageName = hero.getJSONObject("data").getJSONObject("passive").getJSONObject("image").getString("full");
            String     url              = SKILL_IMG_URL + passiveImageName;
//            System.out.println("passive url：" + url);
            byte[] passiveIMg = downloadImage(url);
            File   to         = new File("D:\\hero\\game_data/img/skill/" + passiveImageName);
            if (!to.exists()) {
                Files.createParentDirs(to);
                Files.write(passiveIMg, to);
                System.out.println(String.format("%4d save passive: " + passiveImageName, j));
            } else {
                System.out.println("exist file:" + passiveImageName);
            }
            JSONArray jsonArray = hero.getJSONObject("data").getJSONArray("spells");
            for (int i = 0; i < jsonArray.length(); i++) {
                String skillImageName = jsonArray.getJSONObject(i).getJSONObject("image").getString("full");
                String url1           = SKILLs_IMG_URL + skillImageName;
//                System.out.println("skill url:" + url1);
                byte[] skillIMG = downloadImage(url1);
                File   tof      = new File("D:\\hero\\game_data/img/skill/" + skillImageName);
                if (tof.exists()) {
                    continue;
                }
                Files.createParentDirs(tof);
                Files.write(skillIMG, tof);
                System.out.println(String.format("%4d save skill: " + skillImageName, i));
            }
        }
    }
}
