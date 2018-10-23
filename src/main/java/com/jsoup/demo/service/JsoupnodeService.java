package com.jsoup.demo.service;

import com.jsoup.demo.entity.Jsoupnode;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 123
 * @since 2018-10-17
 */
public interface JsoupnodeService extends IService<Jsoupnode> {
    /**
     * 查询网页节点构造
     * @return
     */
    public List<Jsoupnode> selectNode();
}
