package com.charge.service.admin.impl;

import com.charge.config.vo.Datagrid;
import com.charge.dao.AdminMapper;
import com.charge.model.Admin;
import com.charge.model.AdminExample;
import com.charge.model.User;
import com.charge.service.admin.AdminServiceI;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员后台--接口实现
 * @author liumw
 * @date 2016/8/2 0002
 */
@Service("adminService")
public class AdminServiceImpl implements AdminServiceI {

    @Autowired
    private AdminMapper adminMapper;
    /**
     * 根据主键id，获取管理员信息
     * @param id
     * @return
     */
    @Override
    public Admin getAdminById(Long id) throws Exception {
        return adminMapper.selectByPrimaryKey(id);
    }

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public Admin loginAdmin(String username, String password)  throws Exception{
        return adminMapper.selectByUsernamePass(username, password);
    }

    /**
     * 获取管理员信息的dataGrid
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Datagrid<Admin> dataGrid(int page, int rows)  throws Exception{
        Datagrid<Admin> datagrid = new Datagrid<Admin>();
        Page<User> u = PageHelper.startPage(page, rows, "id desc");
        List<Admin> admins = adminMapper.selectAllAdmin();
        datagrid.setRows(admins);
        long total = u.getTotal();
        datagrid.setTotal(total);

        return datagrid ;
    }
}
