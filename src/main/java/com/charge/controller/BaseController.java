package com.charge.controller;

import com.charge.config.utils.DateUtils;
import com.charge.config.utils.SpringMVCHolder;
import com.charge.config.utils.StringEscapeEditor;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * 控制器支持类
 * @author liumw
 * @date 2016/8/2 0002
 */
public class BaseController {
    /**
     * 日志对象
     */
    protected Logger logger = Logger.getLogger(getClass());



    protected BaseController() {
    }

    protected int id;// 主键
    protected int page = 1;// 当前页
    protected int rows = 10;// 每页显示记录数
    protected String sort;// 排序字段
    protected String order = "asc";// asc/desc
    protected String q;// easyui的combo和其子类过滤时使用

    protected String ids;// 主键集合，逗号分割

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    /**
     * 用户跳转JSP页面
     * <p/>
     * 此方法不考虑权限控制
     *
     * @param folder 路径
     * @param pageName 页面名称(不加后缀)
     * @return 指定JSP页面
     */
    @RequestMapping("/{folder}/{pageName}")
    public String redirectJsp(@PathVariable String folder, @PathVariable String pageName) {
        return "/" + folder + "/" + pageName;
    }

    /**
     * 用户跳转JSP页面
     * <p/>
     * 此方法不考虑权限控制
     *
     * @param prefix   前缀
     * @param toPage   跳转页面名称（包含目录以及后缀）
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("redirect")
    public void redirectJsp(String prefix, String toPage, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtils.isEmpty(prefix)) {
            prefix = "/WEB-INF/views/";
        }
        if (StringUtils.isBlank(toPage)) {
            logger.warn("重定向页面为空!");
            response.sendError(404);
        } else {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("重定向到页面:" + prefix + toPage);
            }
            request.getRequestDispatcher(prefix + toPage).forward(request, response);
        }
    }


    /**
     * 添加Model消息
     *
     * @param messages 消息
     */
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        model.addAttribute("message", sb.toString());
    }

    /**
     * 添加Flash消息
     *
     * @param messages 消息
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
        }
        redirectAttributes.addFlashAttribute("message", sb.toString());
    }

    /**
     * 初始化数据绑定
     * 1. 设置被排除的属性 不自动绑定
     * 2. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 3. 将字段中Date类型转换为String类型
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
//        xss(binder);
    }

    /**
     * String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
     *
     * @param binder
     */
    protected void xss(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));

    }



    /**
     * 获得request
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        return SpringMVCHolder.getRequest();
    }

    /**
     * 获得response
     *
     * @return
     */
    public HttpServletResponse getResponse() {
        return SpringMVCHolder.getResponse();
    }

    /**
     * 获得session
     *
     * @return
     */
    public HttpSession getSession() {
        return SpringMVCHolder.getRequest().getSession();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
