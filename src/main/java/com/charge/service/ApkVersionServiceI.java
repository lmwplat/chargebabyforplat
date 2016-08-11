package com.charge.service;

import com.charge.config.vo.Datagrid;
import com.charge.config.vo.Json;
import com.charge.model.ApkVersion;

/**
 * apk控制管理--接口
 * @author liumw
 * @date 2016/8/3 0003
 */
public interface ApkVersionServiceI {
    /**获取apkVersion信息的dataGrid*/
    Datagrid<ApkVersion> dataGrid(int page, int rows) throws Exception;

    /**添加一个apk*/
    Json addApk(ApkVersion apkVersion) throws Exception;

    /**获取最新版本信息*/
    Json getLastApkVersion() throws Exception;

    /**检测apk版本*/
    Json checkApkVersion(Integer versionNo) throws Exception;
}
