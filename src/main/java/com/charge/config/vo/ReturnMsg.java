package com.charge.config.vo;

/**
 * 返回信息状态
 * @author liumw
 * @date 2016/7/28 0028
 */
public class ReturnMsg {
    /**成功*/
    public static final String SUCCESS = "0000";

    /**系统异常*/
    public static final String SYS_FAIL = "9999";

    /**参数错误*/
    public static final String PARAMETER_ERROR = "9998";


    /**用户名已存在*/
    public static final String USERNAME_EXIST = "1001";
    /**用户名不存在*/
    public static final String USERNAME_NO_EXIST = "1002";
    /**用户名密码错误*/
    public static final String USERNAME_PASS_ERROR = "1003";


    /**apk版本信息不存在*/
    public static final String APK_VERSION_NO_EXIST = "1101";
    /**apk版本已经是最新*/
    public static final String APK_VERSION_LASTEST = "1102";
}
