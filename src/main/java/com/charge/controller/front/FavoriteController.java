package com.charge.controller.front;

import com.alibaba.fastjson.JSON;
import com.charge.config.vo.Json;
import com.charge.config.vo.ReturnMsg;
import com.charge.controller.BaseController;
import com.charge.model.Favorite;
import com.charge.service.FavoriteServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户收藏充电桩
 * @author liumw
 * @date 2016/8/11 0011
 */
@Controller
@RequestMapping("/favorite")
public class FavoriteController  extends BaseController {
    private static final Logger logger = Logger.getLogger(FavoriteController.class);

    @Autowired
    private FavoriteServiceI favoriteServiceI;

    /**
     * 用户添加收藏
     * *@param userId 用户id
     * *@param chargeNo 充电桩编码
     */
    @ResponseBody
    @RequestMapping(value = "/addFavorite", method = RequestMethod.POST)
    public Json addFavorite(@ModelAttribute("favorite") Favorite favorite){
        Json json = new Json();
        if (isNull(favorite.getChargeNo()) || (favorite.getUserId() == null)){
            json.setMsg("参数错误");
            json.setResult_code(ReturnMsg.PARAMETER_ERROR);
            json.setSuccess(false);
            return json;
        }



        try {
            if (checkUser(favorite, json)) return json;

            json =  favoriteServiceI.addFavorite(favorite.getUserId(), favorite.getChargeNo());
            logger.info(JSON.toJSONString(json));
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("用户添加收藏失败" + e.getMessage());
            json.setResult_code(ReturnMsg.SYS_FAIL);
            json.setSuccess(false);
            return json;
        }


    }

    private boolean checkUser(@ModelAttribute("favorite") Favorite favorite, Json json) throws Exception {
        if (checkUserById(favorite.getUserId())){
            json.setMsg("参数错误");
            json.setResult_code(ReturnMsg.PARAMETER_ERROR);
            json.setSuccess(false);
            return true;
        }
        return false;
    }

    /**
     * 用户取消收藏
     * *@param userId 用户id
     * *@param id 收藏id
     */
    @ResponseBody
    @RequestMapping(value = "/removeFavorite", method = RequestMethod.POST)
    public Json removeFavorite(@ModelAttribute("favorite") Favorite favorite){
        Json json = new Json();
        if (favorite.getId() == null || (favorite.getUserId() == null)){
            json.setMsg("参数错误");
            json.setResult_code(ReturnMsg.PARAMETER_ERROR);
            json.setSuccess(false);
            return json;
        }


        try {
            if (checkUser(favorite, json)) {
                return json;
            }
            json =  favoriteServiceI.removeFavorite(favorite.getUserId(), favorite.getId());
            logger.info(JSON.toJSONString(json));
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("用户取消收藏失败" + e.getMessage());
            json.setResult_code(ReturnMsg.SYS_FAIL);
            json.setSuccess(false);
            return json;
        }
    }

    /**
     * 获取该用户的收藏列表
     * *@param userId 用户id
     */
    @ResponseBody
    @RequestMapping(value = "/findFavorite", method = RequestMethod.POST)
    public Json findFavorite(@ModelAttribute("favorite") Favorite favorite){
        Json json = new Json();
        if (favorite.getUserId() == null){
            json.setMsg("参数错误");
            json.setResult_code(ReturnMsg.PARAMETER_ERROR);
            json.setSuccess(false);
            return json;
        }


        try {
            List<Favorite>  favorites =  favoriteServiceI.findFavorite(favorite.getUserId());
            json.setResult_code(ReturnMsg.SUCCESS);
            json.setSuccess(true);
            json.setMsg("成功");
            json.setObj(favorites);
            logger.info(JSON.toJSONString(json));
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("获取该用户的收藏列表失败" + e.getMessage());
            json.setResult_code(ReturnMsg.SYS_FAIL);
            json.setSuccess(false);
            return json;
        }
    }
}
