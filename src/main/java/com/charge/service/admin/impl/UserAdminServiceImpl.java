package com.charge.service.admin.impl;

import com.charge.config.vo.Datagrid;
import com.charge.dao.UserMapper;
import com.charge.model.Admin;
import com.charge.model.AdminExample;
import com.charge.model.User;
import com.charge.model.UserExample;
import com.charge.service.admin.UserAdminServiceI;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liumw
 * @date 2016/8/24 0024
 */
@Service("userAdminService")
public class UserAdminServiceImpl implements UserAdminServiceI {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Datagrid<User> dataGrid(int page, int rows) throws Exception {
        Datagrid<User> datagrid = new Datagrid<User>();

        Page<User> u = PageHelper.startPage(page, rows, "id desc");
        List<User> users = userMapper.selectAllUser();
        datagrid.setRows(users);
        long total = u.getTotal();
        datagrid.setTotal(total);
        return datagrid;
    }
}
