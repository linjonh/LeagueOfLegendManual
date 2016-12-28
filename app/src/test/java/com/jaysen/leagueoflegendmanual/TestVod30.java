package com.jaysen.leagueoflegendmanual;

import com.google.common.io.Files;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lin on 2016/12/21.
 */

public class TestVod30 {
    public static final String BASE_URL = "http://www.vod30.com";
    public static final String ajax     = "http://www.vod30.com/categories/4/?method=json&block_id=list_videos_common_videos_list&sort_by=post_date&page=3";

    @Test
    public void testVod30() {
        try {
//           Document doc= Jsoup.connect("http://www.vod30.com/categories/4/")
            Document doc = Jsoup.connect(ajax)
                                .header("Accept",
                                        "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                                .header("Accept-Encoding", "gzip, deflate, sdch")
                                .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4")
                                .header("Cache-Control", "max-age=0")
//                   .header("Connection","keep-alive")
                                .header("User-Agent",
                                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
                                .get();
//            Elements els=doc.select(".player-holder");
//            System.out.println(els.get(0));
            System.out.println(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDownloadJs() {
//        String jsSrc="/api/player/load?vkey=849b-F9vUmLGWOatgpShiW89GtoHje0QBqmI_pSRnnTr8P_mFQ";
        String loadVod = "/api/player/getplayurl?vkey=849b-F9vUmLGWOatgpShiW89GtoHje0QBqmI_pSRnnTr8P_mFQ";
        try {
            Document document = Jsoup.connect(BASE_URL + loadVod)
                                     .header("User-Agent",
                                             "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36")
                                     .get();
            System.out.println(document.select("body").get(0).text());
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
    }

    int mapCount = 1;


    @Test
    public void testVodlinks() throws IOException, JSONException {
//        File vodlinkFile = new File(
//                "D:\\AndroidStudioProjects\\LeagueOfLegendManual\\app\\src\\test\\java\\com\\jaysen\\leagueoflegendmanual\\vod_links.txt");
        File vodlinkFile = new File(
                "D:\\AndroidStudioProjects\\LeagueOfLegendManual\\app\\src\\test\\java\\com\\jaysen\\leagueoflegendmanual\\needDlAgainFilesNameList.txt");
        BufferedReader bufferedReader = Files.newReader(vodlinkFile, Charset.defaultCharset());

        String            tmp;
        ArrayList<String> links = new ArrayList<>();
        while ((tmp = bufferedReader.readLine()) != null) {
//            System.out.println("length:" + tmp.length());
            if (tmp.length() > 0) {
                links.add(tmp);
            }
        }

        String vodFile = "D:\\AndroidStudioProjects\\LeagueOfLegendManual\\app\\src\\test\\java\\com\\jaysen\\leagueoflegendmanual\\LOL_vod.json";
        String json    = TenWebTest.getReadedFileString(vodFile);

        JSONObject       jsonObject = new JSONObject(json);
        JSONObject       data       = jsonObject.getJSONObject("data");
        Iterator<String> keys       = data.keys();
        while (keys.hasNext()) {
            System.out.println("\nindex: " + mapCount);
            String key = keys.next();
//            System.out.println("key name:" + key);
            JSONObject item    = data.getJSONObject(key);
            String     vodlink = item.getString("vodlink");
//            System.out.println("vodlink:" + vodlink);
            compareVid(links, item, vodlink);
            mapCount++;
        }

//        File file = new File(
//                "D:\\AndroidStudioProjects\\LeagueOfLegendManual\\app\\src\\test\\java\\com\\jaysen\\leagueoflegendmanual\\VodRealLink.json");
//        Files.write(data.toString().getBytes(), file);


    }

    /**
     * @param links   need download source links to be mapped
     * @param item    hero item info
     * @param vodlink hero video link （截取vid字符串）
     * @throws JSONException
     */
    private void compareVid(ArrayList<String> links, JSONObject item,
            String vodlink) throws JSONException, IOException {
        String tmpLink = vodlink + "\n";
        int    start   = vodlink.indexOf("vid=");
        if (start == -1) {
            start = vodlink.indexOf("VideoIDS=");
        }
        if (start > 0) {
            vodlink = vodlink.substring(start).replace("vid=", "").replace("VideoIDS=", "");
            int endIndex = vodlink.indexOf("&");
            if (endIndex != -1) {
                vodlink = vodlink.substring(0, endIndex);
            }
            System.out.println(String.format("%-3d vodlink key:" + vodlink, mapCount));
        }
        boolean hasLink = false;
        for (String line : links) {
            if (line.contains(vodlink)) {
                item.put("vodlink", line);
                System.out.println(String.format("%-3d item:" + item.toString(), mapCount));
                hasLink = true;
                break;
            }
        }
        if (hasLink) {
//            item.put("directLink", false);
            System.out.println(tmpLink);
            Files.append(tmpLink, new File(
                                 "D:\\AndroidStudioProjects\\LeagueOfLegendManual\\app\\src\\test\\java\\com\\jaysen\\leagueoflegendmanual\\needDlVedioLink.txt"),
                         Charset.defaultCharset());
        }
    }

    /**
     * 列出已下载文件名保存到linksFiles.txt
     *
     * @throws IOException
     */
    @Test
    public void listFilesNames() throws IOException {
        File          file          = new File("G:\\gamecenter\\vod");
        File[]        files         = file.listFiles();
        StringBuilder stringBuilder = new StringBuilder();
        for (File f :
                files) {
            String name = f.getName();
            stringBuilder.append(name).append("\n");
        }
        Files.write(stringBuilder.toString().getBytes(), new File(
                "D:\\ASProject\\LeagueOfLegendManual\\app\\src\\test\\java\\com\\jaysen\\leagueoflegendmanual\\linksFiles.txt"));
    }

    /**
     * linksFIles已下载文件名字符串与_vod2.json 的hero item vodlink的vid 做比较，未匹配到的需要再下载，记录该vodlink到文件needDlAgainFilesNameList.txt
     *
     * @throws IOException
     * @throws JSONException
     */
    @Test
    public void testRecoverDeleteFile() throws IOException, JSONException {
        File fileAppend = new File(
                "D:\\AndroidStudioProjects\\LeagueOfLegendManual\\app\\src\\test\\java\\com\\jaysen\\leagueoflegendmanual\\needDlAgainFilesNameList.txt");
        File linksFiles = new File(
                "D:\\AndroidStudioProjects\\LeagueOfLegendManual\\app\\src\\test\\java\\com\\jaysen\\leagueoflegendmanual\\linksFiles.txt");
        String jsonString = TenWebTest.getReadedFileString(
                "D:\\AndroidStudioProjects\\LeagueOfLegendManual\\app\\src\\test\\java\\com\\jaysen\\leagueoflegendmanual\\_vod2.json");
        JSONObject       jsonObject = new JSONObject(jsonString);
        Iterator<String> keys       = jsonObject.keys();
        List<String>     stringList = Files.readLines(linksFiles, Charset.defaultCharset());

        while (keys.hasNext()) {
            String     key     = keys.next();
            JSONObject item    = jsonObject.getJSONObject(key);
            String     vodlink = item.getString("vodlink");
            String     tmpLink = vodlink;
            int        end     = vodlink.indexOf("?");
            if (end != -1) {
                vodlink = vodlink.substring(0, end);
            }
            vodlink = vodlink.substring(vodlink.lastIndexOf("/")).replace("/", "");
            System.out.println(vodlink);
            boolean matched = false;
            for (String fileName : stringList) {
                if (vodlink.contains(fileName)) {
                    matched = true;
                    break;
                }
            }
            if (!matched) {
//                String name = item.getString("name") + ","+vodlink+" = " + tmpLink+"\n";
                String name = vodlink + "\n";
                System.out.println("append: " + name);
                Files.append(name, fileAppend, Charset.defaultCharset());
            }
        }


    }

    /**
     * 创建我自己的视频库JSON
     * @throws IOException
     * @throws JSONException
     */
    @Test
    public void createVodNamePathJson() throws IOException, JSONException {
        File vodFile = new File(
                "D:\\AndroidStudioProjects\\LeagueOfLegendManual\\app\\src\\test\\java\\com\\jaysen\\leagueoflegendmanual\\_vod2.json");
        String           json       = TenWebTest.getStringBuilder(vodFile).toString();
        JSONObject       jsonObject = new JSONObject(json);
        Iterator<String> keys       = jsonObject.keys();
        while (keys.hasNext()) {
            String     key  = keys.next();
            JSONObject item = jsonObject.getJSONObject(key);
            if (item.has("directLink")) {
                continue;
            }
            String vodlink = item.getString("vodlink");
            vodlink = parseVid(vodlink);
            item.put("vodlink", vodlink);
        }
        Files.write(jsonObject.toString().getBytes(),new File("D:\\AndroidStudioProjects\\LeagueOfLegendManual\\app\\src\\test\\java\\com\\jaysen\\leagueoflegendmanual\\_my_vod.json"));
    }

    private String parseVid(String vodlink) {
        int start = vodlink.indexOf("?");
        if (start != -1) {
            vodlink = vodlink.substring(0, start);
            int end = vodlink.lastIndexOf("/");
            if (end != -1) {
                vodlink = vodlink.substring(end + 1);
                System.out.println("vodlink = [" + vodlink + "]");
            }
        }
        return vodlink;
    }
}
