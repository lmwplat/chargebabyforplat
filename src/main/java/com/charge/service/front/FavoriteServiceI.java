package com.charge.service.front;

import com.charge.config.vo.Json;
import com.charge.model.Favorite;

import java.util.List;

/**
 * 用户收藏充电桩---接口
 * @author liumw
 * @date 2016/8/11 0011
 */
public interface FavoriteServiceI {

    /**用户添加收藏*/
    Json addFavorite(Long userId, String chargeNo) throws Exception;
    /**用户取消收藏*/
    Json removeFavorite(Long userId, String chargeNo) throws Exception;
    /** 获取该用户的收藏列表*/
    List<Favorite> findFavorite(Long userId) throws Exception;
}
