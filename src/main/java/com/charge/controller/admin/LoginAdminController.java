package com.charge.controller.admin;

import com.alibaba.fastjson.JSON;
import com.charge.config.utils.ConfigUtils;
import com.charge.config.utils.MD5Utils;
import com.charge.config.vo.Json;
import com.charge.config.vo.ReturnMsg;
import com.charge.config.vo.SessionSysInfo;
import com.charge.controller.BaseController;
import com.charge.model.Admin;
import com.charge.service.AdminServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liumw
 * @date 2016/8/2 0002
 */
@Controller
@RequestMapping("/admin")
public class LoginAdminController extends BaseController {

    /**
     * 日志对象
     */
    protected Logger logger = Logger.getLogger(LoginAdminController.class);

    @Autowired
    private AdminServiceI adminService;

    @RequestMapping(value = "/login" )
    public String showLoginForm() {
        return "login";
    }
    /**
     * 主页面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/main")
    public String main() throws Exception {
        return "main";
    }


    /**
     * 主页面--下面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/north")
    public String north() throws Exception {
        return "layout/north";
    }


    /**
     * 主页面--下面
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/south")
    public String south() throws Exception {
        return "layout/south";
    }
    /*
	 * 到达后台管理员“用户系统管理-用户管理”页面
	 * 菜单权限
	*/
    @RequestMapping("/user")
    public String toUserAdmin()	{
        return "user/user" ;
    }

    /**
     * 管理员登录
     *
     * @param admin
     *            管理员对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/adminLogin")
    public Json login(Admin admin) throws Exception{
        Json json = new Json();
        try {
            String password = MD5Utils.md5(admin.getPassword());
            Admin user = adminService.loginAdmin(admin.getUsername(), password);
            if (user != null){
                SessionSysInfo sessionInfo = new SessionSysInfo();

                sessionInfo.setUsername(user.getUsername());
                sessionInfo.setRealName(user.getRealName());
                sessionInfo.setUserId(user.getId());
                sessionInfo.setId(getSession().getId());
                sessionInfo.setAdmin(user);
                getSession().setAttribute(ConfigUtils.getSessionInfoName(), sessionInfo);

                json.setSuccess(true);
                json.setMsg("登录成功！");
                json.setResult_code(ReturnMsg.SUCCESS);
                json.setObj(user);
            }else{
                json.setSuccess(false);
                json.setMsg("用户名密码错误！");
                json.setResult_code(ReturnMsg.USERNAME_PASS_ERROR);
            }
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
     * @return
     */
    @ResponseBody
    @RequestMapping("/logout")
    public Json logout() throws Exception{
        if (getSession() != null) {
            getSession().invalidate();
        }
        Json j = new Json();
        j.setSuccess(true);
        return j;
    }

    /**
     * 修改自己的密码
     */
    /*@ResponseBody
    @RequestMapping("/updateCurrentPwd")
    public Json updateCurrentPwd(Administrator user) throws Exception{
        SessionInfo sessionInfo = (SessionInfo) SpringMVCHolder.getSession().getAttribute(ConfigUtils.getSessionInfoName());
        Json json = new Json();
        Administrator administrator = administratorService.getById(sessionInfo.getAdministrator().getId());
        administrator.setPwd(Encrypt.e(user.getPwd()));
        administrator.setUpdateDatetime(new Date());
        administratorService.update(administrator);
        json.setSuccess(true);
        return json;
    }*/

    /**
     * 跳转到用户管理页面
     *
     * @return
     */
    @RequestMapping("/userAdmin")
    public String userAdmin() {
        return "user/user";
    }

    /**
     * 跳转到用户管理页面
     *
     * @return
     */
    @RequestMapping("/apkVersionAdmin")
    public String apkVersionAdmin() {
        return "apk/apkVersion";
    }
}
