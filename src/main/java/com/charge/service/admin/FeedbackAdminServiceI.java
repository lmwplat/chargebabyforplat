package com.charge.service.admin;

import com.charge.config.vo.Datagrid;
import com.charge.model.Feedback;
import com.charge.model.User;

/**
 * @author liumw
 * @date 2016/8/24 0024
 */
public interface FeedbackAdminServiceI {
    /**获取反馈意见列表*/
    Datagrid<Feedback> dataGrid(int page, int rows);
}
