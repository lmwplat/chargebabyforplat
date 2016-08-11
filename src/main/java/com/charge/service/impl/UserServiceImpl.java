package com.charge.service.impl;

import com.charge.config.vo.Json;
import com.charge.config.vo.ReturnMsg;
import com.charge.dao.UserMapper;
import com.charge.model.User;
import com.charge.service.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户处理service 实现
 * @author liumw
 * @date 2016/7/25 0025
 */
@Service("userService")
public class UserServiceImpl implements UserServiceI {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id)  throws Exception{
        User user = userMapper.selectByPrimaryKey(id);
        if (user != null && user.getIsLock() == false){
            return user;
        }else{
            return null;
        }
    }

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     */
    @Override
    public Json reg(String username, String password)  throws Exception{
        Json json = new Json();
       // User user = new User();
        User user = userMapper.selectByUsername(username);
        if (user == null){
            User u = new User();
            u.setUsername(username);
            u.setPassword(password);
            userMapper.insert(u);
            json.setResult_code(ReturnMsg.SUCCESS);
            json.setObj(u);
            json.setMsg("注册成功");
            json.setSuccess(true);
        }else{
            json.setSuccess(false);
            json.setMsg("用户名已存在");
            json.setResult_code(ReturnMsg.USERNAME_EXIST);
        }


        return  json;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password)  throws Exception{


        return userMapper.selectByUsernamePass(username, password);
    }

    /**
     * 忘记密码，修改密码
     * @param username
     * @param password
     * @return
     */
    @Override
    public Json confirm(String username, String password)  throws Exception{
        Json json = new Json();

        User user = userMapper.selectByUsername(username);
        if (user != null){
            user.setPassword(password);
            userMapper.updateByPrimaryKey(user);
            json.setResult_code(ReturnMsg.SUCCESS);
            json.setObj(user);
            json.setMsg("注册成功");
            json.setSuccess(true);
        }else{
            json.setSuccess(false);
            json.setMsg("用户名不存在");
            json.setResult_code(ReturnMsg.USERNAME_NO_EXIST);
        }


        return  json;
    }


}
