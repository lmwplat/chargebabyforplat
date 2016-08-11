package com.charge.service;

import com.charge.config.vo.Json;

/**
 * 意见反馈--接口
 * @author liumw
 * @date 2016/7/31 0031
 */
public interface FeedbackServiceI {
    /**提交意见反馈*/
    Json feedbackConfirm(String username, String info) throws Exception;
}
