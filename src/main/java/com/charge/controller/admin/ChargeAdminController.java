package com.charge.controller.admin;

import com.charge.config.vo.Datagrid;
import com.charge.controller.BaseController;
import com.charge.model.Charge;
import com.charge.model.Collect;
import com.charge.service.admin.ChargeAdminServiceI;
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
@RequestMapping("/admin/chargeAdmin")
public class ChargeAdminController extends BaseController{
    private static final Logger logger = Logger
            .getLogger(ChargeAdminController.class);

    @Autowired
    private ChargeAdminServiceI chargeAdminService;

    /**
     * 获取电桩数据表格
     *
     * @return
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public Datagrid<Charge> getChargeGrid() throws Exception{
        return chargeAdminService.dataGrid(page, rows);
    }

}
