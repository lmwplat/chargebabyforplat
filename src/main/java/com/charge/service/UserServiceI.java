package com.charge.service;

import com.charge.config.vo.Json;
import com.charge.model.User;

/**
 * 用户service接口
 * @author liumw
 * @date 2016/7/25 0025
 */
public interface UserServiceI {
    User getUserById(Long id);

    /**用户注册*/
    Json reg(String username, String password);

    /**用户登录*/
    User login(String username, String password);

    /**忘记密码，修改密码*/
    Json confirm(String username, String password);
}
