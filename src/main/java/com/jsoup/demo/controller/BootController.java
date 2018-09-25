package com.jsoup.demo.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jsoup.demo.entity.Boot;
import com.jsoup.demo.jsoup.BqgJsoup;
import com.jsoup.demo.service.BootService;
import com.jsoup.demo.thread.JsoupThread;
import com.jsoup.demo.uitls.Param;
import com.jsoup.demo.uitls.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2018-08-29
 */
@RestController
@RequestMapping("/book")
public class BootController {

    @Autowired
    public BootService bootService;

    /**
     * 测试
     * @return
     */
    @RequestMapping("/hello")
    public String hello (){
        return "hello word";
    }

    /**
     * 数据保存
     * @return
     */
    @RequestMapping("/save")
    public String save() {
        BqgJsoup bqgJsoup = new BqgJsoup();
        bqgJsoup.bootService = bootService;
        try {
            bqgJsoup.doJsoup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 通过id 查询
     * @param id
     * @return
     */
    @RequestMapping("selectById")
    public Result select(String id){
        Boot boot = bootService.selectById(id);
        return Result.success(boot,"success");
    }

    /**
     * 查询所有 带分页
     * @param param
     * @return
     */
    @RequestMapping("/selectByAll")
    public Result selectByPage(Param param){
        Page<Boot> page = bootService.selectPage(new Page<Boot>(param.getCurrent(),param.getSize()),
                getWrapper(param));
        return Result.success(page,"success");
    }

    @RequestMapping("/selectByPageAll")
    public Result selectByPageAll(@RequestBody Param param){
        Page<Boot> page = bootService.selectPage(new Page<Boot>(param.getCurrent(),param.getSize()),
                new EntityWrapper<Boot>());
        return Result.success(page,"success");
    }
    /**
     * 获得查询条件
     * // 相等 ： ==    、  eq
     *
     * // 不相等 ：！=  、ne
     *
     * // 小于  ： <   、 lt
     *
     * // 大于  ： >   、 gt
     *
     * // 小于等于 ： < =    、 le
     *
     * //  大于等于 ： > =   、 ge
     * @param param
     * @return
     */
    public EntityWrapper<Boot> getWrapper(Param param){
        EntityWrapper<Boot> wrapper = new EntityWrapper<Boot>();
        wrapper.like("name",param.getName())
                .like("auth",param.getAuth());
        if (param.getClick() != null){
            wrapper.ge("click",param.getClick());
        }
        if (param.getBeginTime() != null){
            wrapper.between("create_time",param.getBeginTime(),param.getEndTime());
        }
        return wrapper;
    }
    /**
     * 添加
     * @param boot
     * @return
     */
    @RequestMapping("/insert")
    public Result insert(@RequestBody Boot boot){
        boolean insert = bootService.insert(boot);
        if (insert){
            return Result.success(null,"success");
        }else {
            return Result.error("查询失败!!");
        }
    }

    /**
     * 修改
     * @param boot
     * @return
     */
    @RequestMapping("/update")
    public Result update (@RequestBody Boot boot){
       boolean update = bootService.updateById(boot);
       if (update){
           return Result.success(null,"success");
       }else {
           return Result.error("修改失败!!");
       }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(List<String> ids){
        boolean delete = bootService.deleteBatchIds(ids);
        if (delete){
            return Result.success(null,"success");
        }else {
            return Result.error("删除失败!!");
        }
    }
    /**
     * 执行爬虫
     * @return
     */
    @RequestMapping("/thread")
    public String thread(){
        String[] arr = new String[]{
                "https://www.bqg5200.com/sort/4_1/",
                "https://www.bqg5200.com/sort/5_1/",
                "https://www.bqg5200.com/sort/6_1/",
                "https://www.bqg5200.com/sort/7_1/"
        };
        int i  = 1;
        for (String url : arr){
            new JsoupThread(bootService,url,"线程" + i).start();
        }
        return "success";
    }

    /**
     * 得到章节
     * @param url
     * @return
     * @throws Exception
     */
    @RequestMapping("/section")
    public Result getSection(@RequestBody String url) throws Exception {
        Map<String,List<String>> map = new BqgJsoup().section(url.replaceAll("\"",""));
        return Result.success(map,"success");
    }

    /**
     * 得到内容
     * @param url
     * @return
     * @throws Exception
     */
    @RequestMapping("/content")
    public Result getContent( @RequestBody String url) throws Exception{
        Map<String,String> map = new BqgJsoup().content("https://www.bqg5200.com/" +
                url.replaceAll("\"",""));
        if (map == null){
            return Result.error("lose");
        }
        return Result.success(map,"success");
    }
}

