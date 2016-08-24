package com.charge.service.admin.impl;

import com.charge.config.vo.Datagrid;
import com.charge.dao.FeedbackMapper;
import com.charge.model.Feedback;
import com.charge.service.admin.FeedbackAdminServiceI;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liumw
 * @date 2016/8/24 0024
 */
@Service("feedbackAdminService")
public class FeedbackAdminServiceImpl implements FeedbackAdminServiceI {
    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 获取反馈意见列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Datagrid<Feedback> dataGrid(int page, int rows) {
        Datagrid<Feedback> datagrid = new Datagrid<Feedback>();

        Page<Feedback> u = PageHelper.startPage(page, rows, "id desc");
        List<Feedback> feedbacks = feedbackMapper.selectAllFeedback();
        datagrid.setRows(feedbacks);
        long total = u.getTotal();
        datagrid.setTotal(total);
        return datagrid;
    }
}
