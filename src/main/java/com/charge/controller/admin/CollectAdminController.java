package com.charge.controller.admin;

import com.charge.config.vo.Datagrid;
import com.charge.controller.BaseController;
import com.charge.model.Collect;
import com.charge.model.Feedback;
import com.charge.service.admin.CollectAdminServiceI;
import com.charge.service.admin.FeedbackAdminServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liumw
 * @date 2016/8/24 0024
 */
@Controller
@RequestMapping("/admin/collectAdmin")
public class CollectAdminController extends BaseController {

    private static final Logger logger = Logger
            .getLogger(CollectAdminController.class);

    @Autowired
    private CollectAdminServiceI collectAdminService;
    /**
     * 获取电桩收集数据表格
     *
     * @return
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public Datagrid<Collect> getCollectGrid() throws Exception{
        return collectAdminService.dataGrid(page, rows);
    }
}
