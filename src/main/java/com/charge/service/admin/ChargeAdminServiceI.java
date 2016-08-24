package com.charge.service.admin;

import com.charge.config.vo.Datagrid;
import com.charge.model.Charge;

/**
 * @author liumw
 * @date 2016/8/24 0024
 */
public interface ChargeAdminServiceI {
    /**获取电桩列表*/
    Datagrid<Charge> dataGrid(int page, int rows);
}
