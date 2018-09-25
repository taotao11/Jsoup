package com.jsoup.demo.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 爬虫
 */
public class Main {


    public static void main(String[] args) {
//        Document document;
//        try {
//            document = Jsoup.connect("https://www.bqg5200.com/")
//                    .userAgent("Mozilla/5.0 (Windows NT 6.1; rv:22.0) Gecko/20100101 Firefox/22.0")
//                    .ignoreContentType(true).timeout(30000)
//                    .get();
//            Elements aEle = document.select("div.nav > ul > li > a[href]");
//            Elements element = document.select("div.nav > ul > li > a > h2");
//            System.out.println(element.text());
//            aEle.forEach(ele -> {
//                try {
//                    chidPage(ele.attr("href"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//        } catch (Exception e) {
//
//        }
        try {
            new BqgJsoup().doJsoup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void  chidPage(String url) throws IOException {
        Document document;
        String [] USER_AGENTS = new String[] {
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; AcooBrowser; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
                "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Acoo Browser; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 3.0.04506)",
                "Mozilla/4.0 (compatible; MSIE 7.0; AOL 9.5; AOLBuild 4337.35; Windows NT 5.1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
                "Mozilla/5.0 (Windows; U; MSIE 9.0; Windows NT 9.0; en-US)",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)",
                "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 1.0.3705; .NET CLR 1.1.4322)",
                "Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 5.2; .NET CLR 1.1.4322; .NET CLR 2.0.50727; InfoPath.2; .NET CLR 3.0.04506.30)",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN) AppleWebKit/523.15 (KHTML, like Gecko, Safari/419.3) Arora/0.3 (Change: 287 c9dfb30)",
                "Mozilla/5.0 (X11; U; Linux; en-US) AppleWebKit/527+ (KHTML, like Gecko, Safari/419.3) Arora/0.6",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.2pre) Gecko/20070215 K-Ninja/2.1.1",
                "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9) Gecko/20080705 Firefox/3.0 Kapiko/3.0",
                "Mozilla/5.0 (X11; Linux i686; U;) Gecko/20070322 Kazehakase/0.4.5",
                "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.8) Gecko Fedora/1.9.0.8-1.fc10 Kazehakase/0.5.6",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.20 (KHTML, like Gecko) Chrome/19.0.1036.7 Safari/535.20",
                "Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52"
        };
        List list = new ArrayList<Map>();
        document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; rv:22.0) Gecko/20100101 Firefox/22.0")
                .ignoreContentType(true).timeout(30000)
                .get();
        Elements root = document.select("div.recombook");
        Elements txtUrl = root.select("dl > dd > a");
        Elements name = txtUrl.select("h3");
        Elements auth = root.select("dl > dd.tit");
        Elements detail = root.select("dl > dd.name");
        name.forEach(element -> {
            Map<String,Object> txtMap = new HashMap();
            txtMap.put("name",element.text());
            list.add(txtMap);
        });
        doForeach(auth,list,"auth");
        doForeach(txtUrl,list,"textUrl");
        doForeach(detail,list,"detail");
        System.out.println(list);
    }
    public static void doForeach(Elements elements,List<Map> list,String name){
        int i = 0;
        for (Element element : elements){
            if (i >= list.size()) {
                break;
            }
            if (name.equals("textUrl")){
                list.get(i).put(name,element.attr("href"));
            }else {
                list.get(i).put(name,element.text());
            }
            i++;
        }
    }
}
