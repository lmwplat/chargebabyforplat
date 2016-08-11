package com.charge.service;

import com.charge.config.vo.Json;
import com.charge.model.User;

/**
 * 用户service接口
 * @author liumw
 * @date 2016/7/25 0025
 */
public interface UserServiceI {
    User getUserById(Long id) throws Exception;


    /**用户注册*/
    Json reg(String username, String password) throws Exception;

    /**用户登录*/
    Json login(String username, String password) throws Exception;

    /**忘记密码，修改密码*/
    Json confirm(String username, String password) throws Exception;
}
