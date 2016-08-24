package com.charge.service.admin.impl;

import com.charge.config.vo.Datagrid;
import com.charge.dao.CollectMapper;
import com.charge.model.Collect;
import com.charge.service.admin.CollectAdminServiceI;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liumw
 * @date 2016/8/24 0024
 */
@Service("collectAdminService")
public class CollectAdminServiceImpl implements CollectAdminServiceI {
    @Autowired
    private CollectMapper collectMapper;

    /**
     * 获取电桩采集列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Datagrid<Collect> dataGrid(int page, int rows) {
        Datagrid<Collect> datagrid = new Datagrid<Collect>();

        Page<Collect> u = PageHelper.startPage(page, rows, "id desc");
        List<Collect> collects = collectMapper.selectAllCollect();
        datagrid.setRows(collects);
        long total = u.getTotal();
        datagrid.setTotal(total);
        return datagrid;
    }
}
