package com.charge.service.impl;

import com.charge.config.state.AppConstants;
import com.charge.config.utils.ConfigUtils;
import com.charge.config.vo.Datagrid;
import com.charge.config.vo.Json;
import com.charge.config.vo.ReturnMsg;
import com.charge.dao.ApkVersionMapper;
import com.charge.model.Admin;
import com.charge.model.AdminExample;
import com.charge.model.ApkVersion;
import com.charge.model.ApkVersionExample;
import com.charge.service.ApkVersionServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * apk控制管理--接口实现
 * @author liumw
 * @date 2016/8/3 0003
 */
@Service("apkVersionService")
public class ApkVersionServiceImpl implements ApkVersionServiceI {
    @Autowired
    private ApkVersionMapper apkVersionMapper;

    /**
     * 获取apkVersion信息的dataGrid
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Datagrid<ApkVersion> dataGrid(int page, int rows)  throws Exception{
        Datagrid<ApkVersion> datagrid = new Datagrid<ApkVersion>();

        List<ApkVersion> apks= apkVersionMapper.selectAllApk();
        datagrid.setRows(apks);

        ApkVersionExample example = new ApkVersionExample();
        int total = apkVersionMapper.countByExample(example);
        datagrid.setTotal(total);

        return datagrid ;
    }

    /**
     * 添加一个apk
     * @param apkVersion
     * @return
     */
    @Override
    public Json addApk(ApkVersion apkVersion)  throws Exception{
        Json json = new Json();

        ApkVersion lastApk = apkVersionMapper.selectLastVersion();
        apkVersion.setVersionNo(lastApk.getVersionNo());
        apkVersion.setUrl(AppConstants.APK_SERVER);
        apkVersion.setCreateTime(new Date());
        apkVersion.setUpdateTime(new Date());
        apkVersionMapper.insert(apkVersion);

        return json;
    }

    /**
     * 获取最新版本信息
     * @return
     */
    @Override
    public Json getLastApkVersion()  throws Exception{
        Json json = new Json();

        ApkVersion lastApk = apkVersionMapper.selectLastVersion();
        if (lastApk != null){
            json.setSuccess(true);
            json.setResult_code(ReturnMsg.SUCCESS);
            json.setMsg("成功");
            json.setObj(lastApk);
        }else{
            json.setSuccess(false);
            json.setResult_code(ReturnMsg.APK_VERSION_NO_EXIST);
            json.setMsg("版本信息不存在");
        }

        return json;
    }

    /**
     * 检测apk版本
     * @param versionNo
     * @return
     */
    @Override
    public Json checkApkVersion(Integer versionNo)  throws Exception{
        Json json = new Json();
        ApkVersion lastApk = apkVersionMapper.selectLastVersion();

        if (lastApk == null){
            initApkVersion();
            json.setSuccess(false);
            json.setResult_code(ReturnMsg.APK_VERSION_NO_EXIST);
            json.setMsg("版本信息不存在");
            return json;
        }

        if (versionNo >= lastApk.getVersionNo()){
            json.setSuccess(false);
            json.setResult_code(ReturnMsg.APK_VERSION_LASTEST);
            json.setMsg("apk版本已经是最新");
            return json;
        }
        json.setSuccess(true);
        json.setResult_code(ReturnMsg.SUCCESS);
        json.setMsg("成功");
        json.setObj(lastApk);

        return json;
    }

    /**
     * 初始化apkVersion
     */
    private void initApkVersion() throws Exception{
        ApkVersion apkVersion = new ApkVersion();
        apkVersion.setDescription("初始版本");
        apkVersion.setUrl(AppConstants.APK_DOWNLOAD_ADDRESS);
        apkVersionMapper.insert(apkVersion);
    }
}
