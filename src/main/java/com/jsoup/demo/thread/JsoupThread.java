package com.jsoup.demo.thread;

import com.jsoup.demo.jsoup.BqgJsoup;
import com.jsoup.demo.service.BootService;

/**
 * 爬虫线程
 */
public class JsoupThread implements Runnable {

    public BootService bootService;
    private Thread t;
    public String url;
    public String name;

    public JsoupThread(BootService bootService, String url, String name) {
        this.bootService = bootService;
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            BqgJsoup bqgJsoup = new BqgJsoup();
            bqgJsoup.bootService = bootService;
            bqgJsoup.chidPage(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void start(){
        if (t == null){
            t = new Thread(this,name);
            t.start();
        }
    }
}
