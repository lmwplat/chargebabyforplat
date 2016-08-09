package com.charge.config.state;

import com.charge.config.utils.ConfigUtils;

/**
 * 系统使用的静态变量
 * @author liumw
 * @date 2016/8/3 0003
 */
public class AppConstants {
    /**配置文件名*/
    public static final String CONFIG_FILE_PATH = "config";
    /**sessionInfo名字*/
    public static final String SESSION_INFO = "sessionInfoName";
    /**apk下载服务器*/
    public static final String APK_SERVER = "apkServer";
    /**apk名称*/
    public static final String APK_NAME = "桩宝宝";
    /***apk安装文件名*/
    public static final String APK_INSTALL_NAME = "chargebaby.apk";
    /**apk下载地址*/
    public static final String APK_DOWNLOAD_ADDRESS = ConfigUtils.get(APK_SERVER) + APK_INSTALL_NAME;

    /**APK文件存储目录 放置于webapp下 应用相对路径*/
    public static final String DISK_BASE_DIR_APK = "baseDirApk";

    /**ftp.host 主机名*/
    public static final String FTP_HOST = "ftp.host";
    /**ftp.port 端口号*/
    public static final String FTP_PORT = "ftp.port";
    /**ftp.username 用户名*/
    public static final String FTP_UERNAME = "ftp.username";
    /**ftp.password 密码*/
    public static final String FTP_PASSWORD = "ftp.password";



}
