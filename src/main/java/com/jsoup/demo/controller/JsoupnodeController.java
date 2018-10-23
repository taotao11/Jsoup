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

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 123
 * @since 2018-10-17
 */
@RestController
@RequestMapping("/jsoupnode")
public class JsoupnodeController {

    @Autowired
    JsoupService jsoupService;

    @Autowired
    JsoupnodeService jsoupnodeService;

    /**
     * 批量插入
     * @param jsoupnodes
     * @return
     */
    @PostMapping("/bacthIns")
    public Result batchInsert(@RequestBody List<Jsoupnode> jsoupnodes){

        jsoupnodes.forEach(node->{
            Jsoup jsoup = node.getJsoup();
            if (jsoup != null){
                String jId = StringUtils.getUuid();
                jsoup.setId(jId);
                boolean isIns = jsoupService.insert(jsoup);
                if (isIns){
                    node.setjId(jId);
                }
            }
            jsoupnodeService.insert(node);

        });
        return Result.success(null,"success");
    }

    /**
     * 新增
     * @param jsoupnode
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Jsoupnode jsoupnode){
        Result result = null;
        jsoupnode.setId(StringUtils.getUuid());
        boolean insert = jsoupnodeService.insert(jsoupnode);
        if (!insert){
            result = Result.error("lose");
        }else {
            result = Result.success(null,"success");
        }
        return result;
    }
    @PostMapping("/selectNode")
    public Result selectNode(){
        List<Jsoupnode> jsoupnodes = jsoupnodeService.selectNode();
        return Result.success(jsoupnodes,"success");
    }
}

