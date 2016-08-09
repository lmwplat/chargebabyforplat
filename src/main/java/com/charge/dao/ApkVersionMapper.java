package com.charge.dao;

import com.charge.model.ApkVersion;
import com.charge.model.ApkVersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApkVersionMapper {
    int countByExample(ApkVersionExample example);

    int deleteByExample(ApkVersionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ApkVersion record);

    int insertSelective(ApkVersion record);

    List<ApkVersion> selectByExample(ApkVersionExample example);

    ApkVersion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ApkVersion record, @Param("example") ApkVersionExample example);

    int updateByExample(@Param("record") ApkVersion record, @Param("example") ApkVersionExample example);

    int updateByPrimaryKeySelective(ApkVersion record);

    int updateByPrimaryKey(ApkVersion record);
    /**获取所有apk*/
    List<ApkVersion> selectAllApk();

    /**获取最新的版本*/
    ApkVersion selectLastVersion();
}