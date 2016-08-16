package com.charge.config.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.charge.model.Favorite;
import com.charge.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by liumw on 2016/8/11 0011.
 */
public class UserInfo {
    private Long id;
    /**创建时间*/
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**修改时间*/
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**用户名*/
    private String username;
    /**真实姓名*/
    private String realName;
    /**手机*/
    private String phone;
    /**email*/
    private String email;
    /**头像*/
    private String headUrl;
    /**充电桩列表*/
    List<Favorite> favoriteList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public List<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }
}
