package com.jsoup.demo.controller;


import com.jsoup.demo.entity.Jsoup;
import com.jsoup.demo.entity.Jsoupnode;
import com.jsoup.demo.service.JsoupService;
import com.jsoup.demo.service.JsoupnodeService;
import com.jsoup.demo.uitls.Result;
import com.jsoup.demo.uitls.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 123
 * @since 2018-10-17
 */
@RestController
@RequestMapping("/jsoup")
public class JsoupController {

    @Autowired
    JsoupService jsoupService;

    @Autowired
    JsoupnodeService jsoupnodeService;

    /**
     * 新增
     * @param jsoup
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Jsoup jsoup){
        String jId = StringUtils.getUuid();
        jsoup.setId(jId);
        boolean insert = jsoupService.insert(jsoup);
        if (insert){
            Jsoupnode jsoupnode = jsoupnodeService.selectById(jsoup.getNode());
            if (jsoupnode != null) {
                jsoupnode.setjId(jId);
                boolean nodeUpd = jsoupnodeService.updateAllColumnById(jsoupnode);
                if (nodeUpd) {
                    return Result.success(null, "success");
                }
            }
        }
        return Result.error("系统错误");
    }

    @GetMapping("/selectOneById")
    public Result selectOneById(@RequestParam("id") String id){
        Jsoup jsoup = jsoupService.selectById(id);
        return Result.success(jsoup,"success");
    }
}

