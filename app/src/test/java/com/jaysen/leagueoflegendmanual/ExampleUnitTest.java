package com.jaysen.leagueoflegendmanual;


import android.util.Base64;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroEntity;
import com.jaysen.leagueoflegendmanual.util.HttpLoggingInterceptor;
import com.jaysen.leagueoflegendmanual.util.RC4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

    @Test
    public void testTencentVOD() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        System.out.println("message = [" + message + "]");
                    }
                }))
                .build();
//        String bodystring = "newplatform=11&vid=j0340rabsfg&charge=0&dtype=3&platform=11&fhdswitch=1&pid=8A9089A1288369B8197586698F2155A6&ehost=https%3A%2F%2Fv%2Eqq%2Ecom%2Fx%2Fcover%2Ffro3b7qcdrhorti%2Ehtml&fp2p=1&ran=0%2E08482184773311019&guid=5F10DEC41D33B62AE30CA2F0951EC349&utype=0&appver=3%2E2%2E25%2E387&ip=&speed=57179&encryptVer=5%2E4&defn=shd&defaultfmt=shd&vids=j0340rabsfg&linkver=2&otype=xml&defnpayver=1&cKey=YouBc6aCAzqUz1DJ%5F%5FpR8bcAOPMjEBzBa02aDfgFuw%2DiXS6%5FpQfiT0mN%2DBz3nZkfkiwAiaA3ZKk70S6fOz7KFD%5FMDwLbHO39L6LYhrTzDhcyqgZviwWET2x%2D7KChaDqAAXtwmQVdRTBUYxAItMAz5Z7RCQwCtvsIMyS0RXsOAR7SfNXKi6lVSubBuzYCHwCNSXuG9LkxvYoXSE5yKlpf4Ehk4FwH6ALTGRvkcZbeSuZDErcZGrX8%5FWIxxB%5FtkgJg%2DSb1MC12gBE%5Fvv8kSl2MQXANiz%5FmR%5FeLma%5Fa4zCEC8efEYLeZpv2jzAUIWs";
        String bodystring = "vid=j0340rabsfg&otype=json&guid=5F10DEC41D33B62AE30CA2F0951EC349";
//        try {
//            bodystring = URLEncoder.encode(bodystring, "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), bodystring);

        Request request = new Request.Builder().url("http://vv.video.qq.com/geturl").post(requestBody)
//                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.82 Safari/537.36")
//                .header("Connection", "keep-alive")
//                .header("Content-Type", "application/x-www-form-urlencoded")
//                .header("Accept", "*/*")
//                .header("Accept-Encoding", " gzip, deflate, br")
//                .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4")
//                .header("Cookier", "Cookie: _gscu_661903259=70115793482trn50; eas_sid=a1W4d740u1s9c896u3a5f8L3z3; sd_userid=99471471601240444; sd_cookie_crttime=1471601240444; ptui_loginuin=2881388487; pac_uid=1_2881388487; tvfe_boss_uuid=986bae4103464a6b; mobileUV=1_1586c393aba_2ae3b; same_pc=%5B%7B%22uin%22%3A%22783036250%22%2C%22qnid%22%3A11299%2C%22status%22%3A1%7D%2C%7B%22uin%22%3A%22783036250%22%2C%22qnid%22%3A11300%2C%22status%22%3A1%7D%5D; RK=XNuO+CMnmT; pt2gguin=o2881388487; ptcz=a2528021f446d03b7bfdc36e2c9c7125d19bc9251670edc9b0ac5d96826388d5; dm_login_weixin_scan=; pgv_pvi=3850589184; guid=180z173z18z640123456789qwertyuio; o_cookie=2881388487; pgv_info=ssid=s9451872506; pgv_pvid=684400184")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            System.out.println(response.body().string().replace("QZOutputJson=","").replace(";",""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void base64(){
        String base64str="52hVhBIiCRoAc1oagqBjJA==";
        System.out.println(new String(Base64.decode(base64str,0)));
    }

    @Test
    public void cryptoRC4(){
        String mPrivatekey="jaysen";
        String txt="jaysen.lin";
        String es= RC4.encry_RC4_string(txt,mPrivatekey);
        System.out.println("encode:"+es);
        String ds=RC4.decry_RC4(es,mPrivatekey);
        System.out.println("decode:"+ds);

    }

    @Test
    public void parseHex(){
        byte[] string=new byte[4];
        string[0]= Byte.parseByte("-28");
        string[1]= Byte.parseByte("-67");
        string[2]= Byte.parseByte("-96");
//        string[3]= Byte.parseByte("-27");
        String txt=new String(string);
        System.out.println(txt);
    }

    @Test public void testByte(){
        String a= "你好";
        System.out.println("a.length() = [" + a.length() + "]");
        System.out.println("char = [" + a.charAt(0) + "]");
        System.out.println("int = [" +  (int)a.charAt(0)+ "]");
        System.out.println("Hex = [" + Integer.toHexString((int)a.charAt(0)) + "]");
        char b='a';
        byte[] bytes=String.valueOf(b).getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println("i:"+i+" = [" + bytes[i] + "]");
        }
        System.out.println("byteString = [" + new String(bytes) + "]");
    }
}