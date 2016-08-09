package com.charge.controller.front;

import com.alibaba.fastjson.JSON;
import com.charge.config.vo.Json;
import com.charge.config.vo.ReturnMsg;
import com.charge.controller.BaseController;
import com.charge.model.ApkVersion;
import com.charge.model.User;
import com.charge.service.ApkVersionServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * apk版本控制管理
 * @author liumw
 * @date 2016/8/3 0003
 */
@Controller
@RequestMapping("/apkVersion")
public class ApkVersionController extends BaseController{
    private static final Logger logger = Logger.getLogger(ApkVersionController.class);
    @Autowired
    private ApkVersionServiceI apkVersionService;

    /**
     * 获取apk版本信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getLastApkVersion", method = RequestMethod.GET)
    public Json getLastApkVersion(){
        Json json = new Json();
        try {
            json =  apkVersionService.getLastApkVersion();
            logger.info(JSON.toJSONString(json));
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("获取apk版本信息失败" + e.getMessage());
            json.setResult_code(ReturnMsg.SYS_FAIL);
            json.setSuccess(false);
        }

        return json;
    }

    /**
     * 检测apk版本
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkApkVersion", method = RequestMethod.POST)
    public Json checkApkVersion(@ModelAttribute("apk") ApkVersion apk){

        int versionNo = apk.getVersionNo();
        Json json = new Json();
        try {
            json =  apkVersionService.checkApkVersion(versionNo);
            logger.info(JSON.toJSONString(json));
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("检测apk版本失败" + e.getMessage());
            json.setResult_code(ReturnMsg.SYS_FAIL);
            json.setSuccess(false);
            logger.error("检测apk版本失败" + e.getMessage());
        }

        return json;
    }

}
