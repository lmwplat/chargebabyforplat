package com.charge.service.front.impl;

import com.charge.config.utils.BeanUtils;
import com.charge.config.vo.Json;
import com.charge.config.vo.ReturnMsg;
import com.charge.config.vo.UserInfo;
import com.charge.dao.UserMapper;
import com.charge.model.Favorite;
import com.charge.model.User;
import com.charge.service.front.FavoriteServiceI;
import com.charge.service.front.UserServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户处理service 实现
 * @author liumw
 * @date 2016/7/25 0025
 */
@Service("userService")
public class UserServiceImpl implements UserServiceI {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FavoriteServiceI favoriteService;

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
            UserInfo userInfo = new UserInfo();
            User u = new User();
            u.setUsername(username);
            u.setPassword(password);
            userMapper.insert(u);

            BeanUtils.copyNotNullProperties(u, userInfo);
            json.setResult_code(ReturnMsg.SUCCESS);
            json.setObj(userInfo);
            json.setMsg("注册成功");
            json.setSuccess(true);
            logger.info(username + "进行用户注册");
        }else{
            json.setSuccess(false);
            json.setMsg("用户名已存在");
            json.setResult_code(ReturnMsg.USERNAME_EXIST);
            logger.error(username + "注册失败----用户名已存在");
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
    public Json login(String username, String password)  throws Exception{
        Json json = new Json();
        User u = userMapper.selectByUsernamePass(username, password);
        if (u == null){
            json.setSuccess(false);
            json.setMsg("用户名密码错误");
            json.setResult_code(ReturnMsg.USERNAME_PASS_ERROR);
            logger.error(username + "登录失败 ----用户名密码错误");
            return json;
        }
        UserInfo userInfo = new UserInfo();
        List<Favorite> favoriteList = favoriteService.findFavorite(u.getId());
        BeanUtils.copyNotNullProperties(u, userInfo);
        userInfo.setFavoriteList(favoriteList);

        json.setSuccess(true);
        json.setMsg("登录成功！");
        json.setResult_code(ReturnMsg.SUCCESS);
        json.setObj(userInfo);
        logger.info(username + "登陆成功");
        return json;
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
            json.setMsg("修改密码成功");
            json.setSuccess(true);
            logger.info(username + "修改密码成功");
        }else{
            json.setSuccess(false);
            json.setMsg("用户名不存在");
            json.setResult_code(ReturnMsg.USERNAME_NO_EXIST);
            logger.error(username + "修改密码失败" + "---用户名不存在");
        }
        return  json;
    }
}
