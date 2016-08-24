package com.charge.service.admin;

import com.charge.config.vo.Datagrid;
import com.charge.model.Collect;

/**
 * @author liumw
 * @date 2016/8/24 0024
 */
public interface CollectAdminServiceI {
    /**获取电桩采集列表*/
    Datagrid<Collect> dataGrid(int page, int rows);
}
