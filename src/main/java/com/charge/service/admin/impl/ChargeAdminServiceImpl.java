package com.charge.service.admin.impl;

import com.charge.config.vo.Datagrid;
import com.charge.dao.ChargeMapper;
import com.charge.model.Charge;
import com.charge.model.Collect;
import com.charge.service.admin.ChargeAdminServiceI;
import com.charge.service.front.ChargeServiceI;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liumw
 * @date 2016/8/24 0024
 */
@Service("chargeAdminService")
public class ChargeAdminServiceImpl implements ChargeAdminServiceI{
    @Autowired
    private ChargeMapper chargeMapper;

    /**
     * 获取电桩列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Datagrid<Charge> dataGrid(int page, int rows) {
        Datagrid<Charge> datagrid = new Datagrid<Charge>();

        Page<Charge> u = PageHelper.startPage(page, rows, "id desc");
        List<Charge> charges = chargeMapper.selectAllCharge();
        datagrid.setRows(charges);
        long total = u.getTotal();
        datagrid.setTotal(total);
        return datagrid;
    }
}
