package com.charge.config.vo;

import com.charge.model.Admin;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * @author liumw
 * @date 2016/8/2 0002
 */
public class SessionSysInfo {
    /**
     * sessionID
     */
    private String id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 登录姓名
     */
    private String username;

    /**
     * 客户端IP
     */
    private String ip;
    /**
     * 登录时间
     */
    private Date loginTime = new Date();
    /**
     * 管理员用户
     */
    private Admin admin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
