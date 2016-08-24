package com.charge.service.admin;

import com.charge.config.vo.Datagrid;
import com.charge.config.vo.Json;
import com.charge.model.Admin;

/**
 * 管理员后台--接口
 * @author liumw
 * @date 2016/8/2 0002
 */
public interface AdminServiceI {

    /**根据主键id，获取管理员信息*/
    Admin getAdminById(Long id) throws Exception;

    /**管理员登录*/
    Admin loginAdmin(String username, String password) throws Exception;

    /**获取管理员信息的dataGrid*/
    Datagrid<Admin> dataGrid(int page, int rows) throws Exception;
}
