package com.jsoup.demo.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2018-08-29
 */
public class Boot extends Model<Boot> {

    private static final long serialVersionUID = 1L;

    /**
     * 书名
     */
    private String name;
    /**
     * id
     */
    private String id;
    /**
     * 内容
     */
    private String content;
    /**
     * 阅读地址
     */
    @TableField("read_url")
    private String readUrl;
    /**
     * 图片地址
     */
    @TableField("img_url")
    private String imgUrl;
    /**
     * 下载地址
     */
    @TableField("dw_url")
    private String dwUrl;
    /**
     * 作者
     */
    private String auth;
    /**
     * 状态 0 连载 1 完结
     */
    private Integer status;
    /**
     * 最新章节
     */
    @TableField("new_works")
    private String newWorks;
    /**
     * 点击数
     */
    private Long click;
    /**
     * 时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 类型
     */
    private String type;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReadUrl() {
        return readUrl;
    }

    public void setReadUrl(String readUrl) {
        this.readUrl = readUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDwUrl() {
        return dwUrl;
    }

    public void setDwUrl(String dwUrl) {
        this.dwUrl = dwUrl;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNewWorks() {
        return newWorks;
    }

    public void setNewWorks(String newWorks) {
        this.newWorks = newWorks;
    }

    public Long getClick() {
        return click;
    }

    public void setClick(Long click) {
        this.click = click;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Boot{" +
        ", name=" + name +
        ", id=" + id +
        ", content=" + content +
        ", readUrl=" + readUrl +
        ", imgUrl=" + imgUrl +
        ", dwUrl=" + dwUrl +
        ", auth=" + auth +
        ", status=" + status +
        ", newWorks=" + newWorks +
        ", click=" + click +
        ", createTime=" + createTime +
        ", type=" + type +
        "}";
    }
}
