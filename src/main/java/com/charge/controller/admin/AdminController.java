package com.charge.controller.admin;

import com.charge.config.vo.Datagrid;
import com.charge.controller.BaseController;
import com.charge.model.Admin;
import com.charge.service.AdminServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * @author liumw
 * @date 2016/8/2 0002
 */
@Controller
@RequestMapping("/admin/userAdmin")
public class AdminController extends BaseController {
    private static final Logger logger = Logger
            .getLogger(AdminController.class);

    @Autowired
    private AdminServiceI adminService;



    /*
     * 到达后台管理员“用户系统管理-用户管理-添加用户表单”页面
     *
    */
    @RequestMapping(value="/userAddPage", method = RequestMethod.GET)
    public String toUserAddAdmin(Model model) throws Exception{
        Admin admin = new Admin();
        model.addAttribute("admin", admin);
        //  System.out.println("query user list from db");
        return "user/userAdd";
    }

    /*
     * 到达后台管理员“用户系统管理-用户管理-修改用户表单”页面
     *
    */
    @RequestMapping(value = "userEditPage/{id}", method = RequestMethod.GET)
    public String toUserEditAdmin(@PathVariable Long id, Model uiModel )	throws Exception{

        Admin admin = adminService.getAdminById(id);
        uiModel.addAttribute("admin", admin);
        return "user/userEdit" ;
    }

    /*
     * 到达后台管理员“用户系统管理-用户管理-用户表单”页面
     *
    */
    @RequestMapping(value = "userShowPage/{id}", method = RequestMethod.GET)
    public String toUserFormAdmin(@PathVariable Long id, Model uiModel )	throws Exception{
        Admin admin = adminService.getAdminById(id);
        uiModel.addAttribute("admin", admin);
        return "user/userShow" ;
    }

    /**
     * 获取用户数据表格
     *
     * @param user
     * @return
     */
    @RequestMapping("/dataGrid")
    @ResponseBody
    public Datagrid<Admin> getUserGrid() throws Exception{
        return adminService.dataGrid(page, rows);
    }


    /**
     * 新建一个用户
     */
    /*@RequestMapping("/add")
    @ResponseBody
    public void saveUser(@ModelAttribute("admin") Admin admin) throws Exception{
        Json json = new Json();

        HqlFilter hqlFilter = new HqlFilter();
        hqlFilter.addFilter("QUERY_t#loginName_S_EQ", admin.getLoginName());
        Admin u = adminService.getByFilter(hqlFilter);
        if (u != null) {
            json.setMsg("新建用户失败，用户名已存在！");
        } else {
            admin.setPwd(Encrypt.e("123456"));
            adminService.save(admin);
            json.setMsg("新建用户成功！默认密码：123456");
            json.setSuccess(true);
        }
        writeJson(json);
    }*/

    /**
     * 更新一个用户
     */
   /* @RequestMapping(value ="/edit", method=RequestMethod.POST)
    @ResponseBody
    synchronized public void updateUser(@ModelAttribute("model") Admin admin) throws Exception{
        Json j = new Json();
        if (admin != null) {
            Admin user = adminService.getById(admin.getId());
            BeanUtil.copyNotNullProperties(admin, user, new String[] { "createDatetime" });
            adminService.update(user);
            j.setSuccess(true);
            j.setMsg("修改成功！");
        }else {
            j.setMsg("修改失败");
        }
        super.writeJson(j);
    }*/

    /**
     * 获得一个用户对象
     */
  /*  @RequestMapping("/show")
    @ResponseBody
    public void getUserById(@ModelAttribute("model") Admin administrator) throws Exception{
        if (!StringUtils.isBlank(administrator.getId().toString())) {
            writeJson(adminService.getById(administrator.getId()));
        } else {
            Json j = new Json();
            j.setMsg("主键不可为空！");
            writeJson(j);
        }
    }
*/
    /**
     * 删除一个用户对象
    */
  /*  @RequestMapping("/delete")
    @ResponseBody
    public void deleteUser(@ModelAttribute("model") Admin admin) throws Exception{
        Json j = new Json();
        if (admin != null) {
            adminService.delete(adminService.getById(admin.getId()));
            j.setSuccess(true);
            j.setMsg("删除成功！");
        }else {
            j.setMsg("删除失败");
        }
        super.writeJson(j);
    }*/

}
