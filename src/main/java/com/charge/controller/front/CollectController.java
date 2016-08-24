package com.charge.controller.front;

import com.alibaba.fastjson.JSON;
import com.charge.config.vo.Json;
import com.charge.config.vo.ReturnMsg;
import com.charge.model.Collect;
import com.charge.service.front.CollectServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 电桩采集
 * @author liumw
 * @date 2016/8/12 0012
 */
@Controller
@RequestMapping("/collect")
public class CollectController {
    private static final Logger logger = Logger.getLogger(CollectController.class);

    @Autowired
    private CollectServiceI collectService;

    /**
     * 添加电桩采集信息
     * *@param username 用户名
     * *@param info  充电桩信息
     */
    @ResponseBody
    @RequestMapping(value = "/addCollect", method = RequestMethod.POST)
    public Json addCollect(@ModelAttribute("collect") Collect collect){
        Json json = new Json();
        try {
            json = collectService.addCollect(collect.getUsername(), collect.getInfo());
            logger.info(JSON.toJSONString(json));
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("系统错误");
            json.setResult_code(ReturnMsg.SYS_FAIL);
            logger.error("添加电桩采集信息" + e.getMessage());
        }
        return json;

    }
}
