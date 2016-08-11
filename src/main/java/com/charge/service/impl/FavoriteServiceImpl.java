package com.charge.service.impl;

import com.charge.config.vo.Json;
import com.charge.config.vo.ReturnMsg;
import com.charge.dao.FavoriteMapper;
import com.charge.model.Charge;
import com.charge.model.Favorite;
import com.charge.model.User;
import com.charge.service.ChargeServiceI;
import com.charge.service.FavoriteServiceI;
import com.charge.service.UserServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户收藏充电桩---接口实现
 * @author liumw
 * @date 2016/8/11 0011
 */
@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteServiceI{

    private static final Logger logger = Logger.getLogger(FavoriteServiceImpl.class);
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private UserServiceI userService;
    @Autowired
    private ChargeServiceI chargeService;

    /**
     * 用户添加收藏
     * @param userId
     * @param chargeNo
     * @return
     */
    @Override
    public Json addFavorite(Long userId, String chargeNo)  throws Exception{
        Json json = new Json();

        //判断userID，chargeNo 是否合法
        Charge charge = chargeService.getChargeByNo(chargeNo);
        if (charge == null){
            json.setMsg("该充电桩不存在");
            json.setResult_code(ReturnMsg.CHARGE_NO_EXIST);
            json.setSuccess(false);
            return json;
        }
        //判断是否添加过
        Favorite f = favoriteMapper.selectByUserIdAndChargeNo(userId, chargeNo);
        if (f != null){
            json.setMsg("已经添加过该收藏");
            json.setResult_code(ReturnMsg.FAVORITE_EXIST);
            json.setSuccess(false);
            return json;
        }

        //添加favorite
        Favorite favorite = new Favorite();
        favorite.setAddress(charge.getAddress());
        favorite.setArea(charge.getArea());
        favorite.setChargeNo(chargeNo);
        favorite.setName(charge.getName());
        favorite.setUserId(userId);
        favoriteMapper.insert(favorite);

        json.setResult_code(ReturnMsg.SUCCESS);
        json.setSuccess(true);
        json.setMsg("成功");
        json.setObj(favorite);

        return json;
    }

    /**
     * 用户取消收藏
     * @param userId
     * @param id
     * @return
     */
    @Override
    public Json removeFavorite(Long userId, Long id) throws Exception {
        Json json = new Json();

        //判断userID，chargeNo 是否合法
        Favorite favorite = favoriteMapper.selectByPrimaryKey(id);
        if (favorite == null){
            json.setMsg("该收藏不存在");
            json.setResult_code(ReturnMsg.CHARGE_NO_EXIST);
            json.setSuccess(false);
            return json;
        }

        favoriteMapper.deleteByPrimaryKey(id);

        json.setResult_code(ReturnMsg.SUCCESS);
        json.setSuccess(true);
        json.setMsg("成功");

        return json;
    }

    /**
     *  获取该用户的收藏列表
     * @param userId
     * @return
     */
    @Override
    public Json findFavorite(Long userId) throws Exception {
        Json json = new Json();

        //判断userID，chargeNo 是否合法
        List<Favorite> favorites = favoriteMapper.findFavoriteByUserId(userId);

        json.setResult_code(ReturnMsg.SUCCESS);
        json.setSuccess(true);
        json.setMsg("成功");
        json.setObj(favorites);
        return json;
    }
}
