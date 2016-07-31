package com.charge.controller;

import com.alibaba.fastjson.JSON;
import com.charge.config.vo.Json;
import com.charge.config.vo.ReturnMsg;
import com.charge.model.Feedback;
import com.charge.model.User;
import com.charge.service.FeedbackServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 意见反馈Controller
 * @author liumw
 * @date 2016/7/31 0031
 */
@Controller
@RequestMapping("/feedbackController")
public class FeedbackController {
    private static final Logger logger = Logger.getLogger(FeedbackController.class);

    @Autowired
    private FeedbackServiceI feedbackService;


    /**
     * 意见反馈提交
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/feedbackConfirm", method = RequestMethod.POST)
    public Json feedbackConfirm(@ModelAttribute("feedback") Feedback feedback){
        Json json = new Json();
        try {
            json = feedbackService.feedbackConfirm(feedback.getUsername(), feedback.getInfo());
            logger.info(JSON.toJSONString(json));
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg(e.getMessage());
            json.setResult_code(ReturnMsg.SYS_FAIL);
        }
        return json;
    }

}
