package com.jsoup.demo.entity;

public class JsoupNode extends Jsoup {
   private Jsoup[] chidens;

    public JsoupNode() {
    }

    public Jsoup[] getChidens() {
        return chidens;
    }

    public void setChidens(Jsoup[] chidens) {
        this.chidens = chidens;
    }
}
