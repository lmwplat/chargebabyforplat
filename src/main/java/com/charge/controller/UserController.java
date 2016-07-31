package com.charge.controller;

import com.alibaba.fastjson.JSON;
import com.charge.config.vo.Json;
import com.charge.config.vo.ReturnMsg;
import com.charge.model.User;
import com.charge.service.UserServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 用户Controller
 * @author liumw
 * @date 2016/7/25 0025
 */
@Controller
@RequestMapping("/userController")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserServiceI userService;

    /**
     * 查看用户信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/{id}/showUser")
    public Json showUser(@PathVariable Long id) {
        Json json = new Json();
        User u = userService.getUserById(id);
        json.setMsg("成功");
        json.setResult_code("330");
      //  request.setAttribute("user", u);
        json.setObj(u);
        return json;
    }
    /**
     * 用户注册
     *
     * @param user
     *            用户对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public Json reg(@ModelAttribute("user") User user) {
        Json json = new Json();
        try {
            json = userService.reg(user.getUsername(), user.getPassword());
            logger.info(JSON.toJSONString(json));
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setResult_code(ReturnMsg.SYS_FAIL);
        }
        return json;
    }


    /**
     * 用户登录
     *
     * @param user
     *            用户对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Json login(@ModelAttribute("user") User user) {
        Json json = new Json();
        try {
            User u =  userService.login(user.getUsername(), user.getPassword());
            json.setSuccess(true);
            json.setMsg("登录成功！");
            json.setResult_code(ReturnMsg.SUCCESS);
            json.setObj(u);
            logger.info(JSON.toJSONString(json));

        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("登录失败" + e.getMessage());
            json.setResult_code(ReturnMsg.SYS_FAIL);
            json.setSuccess(false);
        }
        return json;
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/logout")
    public Json logout(HttpSession session) {
        Json json = new Json();
        if (session != null) {
            session.invalidate();
        }
        json.setSuccess(true);
        json.setMsg("注销成功！");
        return json;
    }

    /**
     * 忘记密码-修改密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public Json confirm(@ModelAttribute("user") User user){
        Json json = new Json();
        try {
            json = userService.confirm(user.getUsername(), user.getPassword());
            logger.info(JSON.toJSONString(json));
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setResult_code(ReturnMsg.SYS_FAIL);
        }
        return json;
    }

}
