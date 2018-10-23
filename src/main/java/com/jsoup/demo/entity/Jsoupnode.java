package com.jsoup.demo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author 123
 * @since 2018-10-17
 */
public class Jsoupnode extends Model<Jsoupnode> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 网页规则
     */
    @TableField("css_query")
    private String cssQuery;
    /**
     * 父ID
     */
    private String parentId;
    /**
     * 外键id
     */
    private String jId;
    @TableField(exist = false)
    private Jsoup jsoup;
    @TableField(exist = false)
    private List<Jsoupnode> children;

    public List<Jsoupnode> getChildren() {
        return children;
    }

    public void setChildren(List<Jsoupnode> children) {
        this.children = children;
    }

    public Jsoup getJsoup() {
        return jsoup;
    }

    public void setJsoup(Jsoup jsoup) {
        this.jsoup = jsoup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCssQuery() {
        return cssQuery;
    }

    public void setCssQuery(String cssQuery) {
        this.cssQuery = cssQuery;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getjId() {
        return jId;
    }

    public void setjId(String jId) {
        this.jId = jId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Jsoupnode{" +
        "id=" + id +
        ", name=" + name +
        ", cssQuery=" + cssQuery +
        ", parentId=" + parentId +
        ", jId=" + jId +
        "}";
    }
}
