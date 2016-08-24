package com.charge.controller.admin;

import com.charge.config.vo.Datagrid;
import com.charge.controller.BaseController;
import com.charge.model.User;
import com.charge.service.admin.UserAdminServiceI;
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
@RequestMapping("/admin/userAdmin")
public class UserAdminController extends BaseController {
    private static final Logger logger = Logger
            .getLogger(UserAdminController.class);

    @Autowired
    private UserAdminServiceI userAdminService;

    /**
     * 获取用户数据表格
     *
     * @return
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public Datagrid<User> getUserGrid() throws Exception{
        return userAdminService.dataGrid(page, rows);
    }


}
