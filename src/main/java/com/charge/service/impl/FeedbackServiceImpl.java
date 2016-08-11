package com.charge.service.impl;

import com.charge.config.vo.Json;
import com.charge.config.vo.ReturnMsg;
import com.charge.dao.FeedbackMapper;
import com.charge.model.Feedback;
import com.charge.service.FeedbackServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 意见反馈-接口实现
 * @author liumw
 * @date 2016/7/31 0031
 */
@Service("feedbackServiceI")
public class FeedbackServiceImpl implements FeedbackServiceI {
    private static final Logger logger = Logger.getLogger(FeedbackServiceImpl.class);
    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 提交意见反馈
     * @param username
     * @param info
     * @return
     */
    @Override
    public Json feedbackConfirm(String username, String info)  throws Exception{
        Json json = new Json();
        Feedback feedback = new Feedback();
        if (username != null){
            feedback.setUsername(username);
        }
        feedback.setInfo(info);
        feedbackMapper.insert(feedback);

        json.setSuccess(true);
        json.setResult_code(ReturnMsg.SUCCESS);
        json.setMsg("提交成功");

        return json;
    }
}
