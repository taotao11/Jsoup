package com.jsoup.demo.uitls;

import java.util.UUID;

public class StringUtils {

    public static String getUuid(){
       return UUID.randomUUID().toString()
                .replaceAll("-","");
    }
}
