package com.charge.dao;

import com.charge.model.Favorite;
import com.charge.model.FavoriteExample;
import java.util.List;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteMapper {
    int countByExample(FavoriteExample example);

    int deleteByExample(FavoriteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Favorite record);

    int insertSelective(Favorite record);

    List<Favorite> selectByExample(FavoriteExample example);

    Favorite selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Favorite record, @Param("example") FavoriteExample example);

    int updateByExample(@Param("record") Favorite record, @Param("example") FavoriteExample example);

    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);
    /**根据用户名，获取收藏列表*/
    List<Favorite> findFavoriteByUserId(@Param("userId")Long userId);
    /**根据用户id和充电桩编码，获取收藏信息*/
    Favorite selectByUserIdAndChargeNo(@Param("userId")Long userId, @Param("chargeNo")String chargeNo);
}