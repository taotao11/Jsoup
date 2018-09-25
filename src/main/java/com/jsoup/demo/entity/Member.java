package com.jsoup.demo.entity;

import java.util.Date;
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
public class Member extends Model<Member> {

    private static final long serialVersionUID = 1L;

    private String id;
    private String username;
    private String password;
    private String email;
    private Date createtime;
    private String qqopenid;
    private String wxopenid;
    private Integer vipflag;
    private Integer deleteflag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getQqopenid() {
        return qqopenid;
    }

    public void setQqopenid(String qqopenid) {
        this.qqopenid = qqopenid;
    }

    public String getWxopenid() {
        return wxopenid;
    }

    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid;
    }

    public Integer getVipflag() {
        return vipflag;
    }

    public void setVipflag(Integer vipflag) {
        this.vipflag = vipflag;
    }

    public Integer getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Member{" +
        ", id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", email=" + email +
        ", createtime=" + createtime +
        ", qqopenid=" + qqopenid +
        ", wxopenid=" + wxopenid +
        ", vipflag=" + vipflag +
        ", deleteflag=" + deleteflag +
        "}";
    }
}
