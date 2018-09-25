package com.jsoup.demo.jsoup;

import com.jsoup.demo.entity.Boot;
import com.jsoup.demo.service.BootService;
import com.jsoup.demo.uitls.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

/**
 * 笔趣阁 爬虫类
 */
public class BqgJsoup extends TxtJsoup {
    // list最大值
    public static final Integer MAX_BOOT = 30;
    public BootService bootService;
    public List<Boot> list = new LinkedList<Boot>();
    public boolean save(List<Boot> list){
        boolean flag = bootService.insertBatch(list);
        return flag;
    }
    @Override
    public void doJsoup() throws Exception {
        //得到节点数组
        Elements aEle = getElements("https://www.bqg5200.com/","div.nav > ul > li > a[href]");
        aEle.forEach(ele -> {
            try {
                String name = ele.select("h2").text();
                if("首页".equals(name) || "玄幻小说".equals(name) ||
                    "武侠".equals(name) || "都市".equals(name)){
                    return;
                }
                chidPage(ele.attr("href"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 获得列表页
     * @param href
     * @throws Exception
     */
    public void chidPage(String href) throws Exception {
        Document document  = null;
        try {
           document = getDocument(href);
        }catch ( IOException e ){
            document = getDocument(href);
        }

        Elements eleUrls = document.select("div.update_list > ul > li > span.r_spanone > a[href]");
        if (!isEleNull(eleUrls)){
            return;
        }
        eleUrls.forEach(element -> {
            try {
                String contentUrl = element.attr("href");
                getContens(contentUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Elements nextPage =  document.select("div.pages > div.pagelink > a.next[href]");
        if(!isEleNull(nextPage)){
            return;
        }
        chidPage(nextPage.get(0).attr("href"));
    }

    /**
     * 获得内容页
     * @param url
     */
    public void getContens(String url) throws  Exception{
        Boot boot = new Boot();
        Document document  = null;
        try {
            document = getDocument(url);
        }catch ( IOException e ){
            document = getDocument(url);
        }
        Elements elements = document.select("div#bookinfo");
        //书名
        String name = getDataOne(elements,"div.booktitle > h1");
        // 作者
        String auth = getDataOne(elements,"div.booktitle > div#author");
        // 内容
        String content = getDataOne(elements,"div#bookintro");
        // 阅读地址
        String readUrl = getDataUrlOne(elements,"div#bookimg > div#reader > a[href]","href");
        // 图片地址
        String imgUrl = getDataUrlOne(elements,"div#bookimg > img","src");
        // 下载地址
//        String dwUrl = getDataUrlOne(elements,"");
        // 状态 0 连载 1 完结
        Integer status = "连载中".equals(getDataOne(elements,"div#count > span.pd_r",5)) ? 0 : 1;
        // 最新章节
        String newWorks = getDataOne(elements,"div.bookright > div.new > span.new_l > a");
        // 点击数
        Long click = Long.parseLong(getDataOne(elements,"div#count > span#Hits"));
        // 类型
        String type = getDataOne(elements,"div#count > span.pd_r",0);
        boot.setId(StringUtils.getUuid());
        boot.setAuth(auth);
        boot.setClick(click);
        boot.setContent(content);
        boot.setCreateTime(new Date());
        boot.setImgUrl(imgUrl);
        boot.setName(name);
        boot.setNewWorks(newWorks);
        boot.setReadUrl(readUrl);
        boot.setStatus(status);
        boot.setType(type);
        System.out.println(boot);
        list.add(boot);
        boolean flag = false;
        if (list.size() >= MAX_BOOT){
            flag = save(list);
            if (flag){
                System.out.println("保存成功!!!!!");
                list.clear();
            }
        }
    }

    /**
     * 章节爬虫
     * @param url
     */
    public Map<String,List<String>> section(String url) throws Exception {
        Map<String,List<String>> map = new HashMap<String, List<String>>();
        Elements aEle = getElements(url,"div#readerlist > ul > li");
        List<String> sections = aEle.eachText();
        List<String> urls = aEle.select("a").eachAttr("href");
        map.put("sections",sections);
        map.put("urls",urls);
        return map;
    }

    /**
     * 章节内容
     * @param url
     * @return
     * @throws Exception
     */
    public Map<String,String>content(String url) throws Exception {
        Map<String,String> map = new HashMap<String, String>();

        Elements aEle = getElements(url,"div#container");
        if (null == aEle || aEle.size() == 0 ){
            return null;
        }
        map.put("section",getDataOne(aEle,"div.title > h1"));
        map.put("content",getDataOne(aEle,"div#center > div#content"));
        map.put("next",getElements(aEle,"div.jump > div.jump1 > a").eachAttr("href").get(4));
        map.put("pre",getDataUrlOne(aEle,"div.jump > div.jump1 > a","href"));
        return map;
    }
    /**
     * 判断节点是否为空
     * @return
     */
    public boolean isEleNull(Elements elements){
        if (elements.size() == 0 || elements == null){
            return false;
        }
        return true;
    }

}
