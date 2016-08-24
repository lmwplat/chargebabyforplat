package com.charge.controller.admin;

import com.alibaba.fastjson.JSON;
import com.charge.config.vo.Datagrid;
import com.charge.config.vo.Json;
import com.charge.config.vo.ReturnMsg;
import com.charge.controller.BaseController;
import com.charge.model.ApkVersion;
import com.charge.service.front.ApkVersionServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * apk版本控制后台管理员管理
 * @author liumw
 * @date 2016/8/3 0003
 */
@Controller
@RequestMapping("/admin/apkVersionAdmin")
public class ApkVersionAdminController extends BaseController {

    private static final Logger logger = Logger
            .getLogger(ApkVersionAdminController.class);

    @Autowired
    private ApkVersionServiceI apkVersionService;

    /**
     * 获取APK数据表格
     * @return
     */
    @ResponseBody
    @RequestMapping("/dataGrid")
    public Datagrid<ApkVersion> getUserGrid() throws Exception {
        return apkVersionService.dataGrid(page, rows);
    }

    /**
    * 到达后台管理员“用户系统管理-用户管理-添加用户表单”页面
    *
   */
    @RequestMapping(value="/apkAddPage", method = RequestMethod.GET)
    public String toApkAddPage() throws Exception{
        return "apk/apkAdd";
    }

    /**
     * 新建一个apk
     */
    @RequestMapping("/addApk")
    @ResponseBody
    public Json saveUser(@ModelAttribute("apkVersion") ApkVersion apkVersion) throws Exception{
        Json json = new Json();

        try {
            json =  apkVersionService.addApk(apkVersion);

            logger.info(JSON.toJSONString(json));

        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("新建一个apk失败" + e.getMessage());
            json.setResult_code(ReturnMsg.SYS_FAIL);
            json.setSuccess(false);
        }
        return json;
    }

}
