package com.charge.service.admin;

import com.charge.config.vo.Datagrid;
import com.charge.model.Admin;
import com.charge.model.User;

/**
 * @author liumw
 * @date 2016/8/24 0024
 */
public interface UserAdminServiceI {
    /**获取用户列表*/
    Datagrid<User> dataGrid(int page, int rows) throws Exception;
}
