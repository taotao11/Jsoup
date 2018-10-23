package com.jsoup.demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jsoup.demo.entity.Jsoupnode;
import com.jsoup.demo.mapper.JsoupnodeMapper;
import com.jsoup.demo.service.JsoupnodeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 123
 * @since 2018-10-17
 */
@Service
public class JsoupnodeServiceImpl extends ServiceImpl<JsoupnodeMapper, Jsoupnode> implements JsoupnodeService {

    @Autowired
    JsoupnodeMapper jsoupnodeMapper;
    @Override
    public List<Jsoupnode> selectNode() {
        //得到所有网页结构
        List<Jsoupnode> jNodes = jsoupnodeMapper.selectList(new EntityWrapper<Jsoupnode>());
        return ergodic(jNodes,"0");
    }

    /**
     * 遍历网页节点
     * @return
     */
    public List<Jsoupnode> ergodic(List<Jsoupnode> jNodes,String pid){
        List<Jsoupnode> retNodes = new ArrayList<Jsoupnode>();
        jNodes.forEach(node ->{
            if (node.getParentId() != null && node.getParentId().equals(pid)){
                //递归得到子节点
                node.setChildren(ergodic(jNodes,node.getId()));
                retNodes.add(node);
            }

        });
        return retNodes;
    }
}
