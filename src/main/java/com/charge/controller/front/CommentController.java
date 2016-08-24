package com.charge.controller.front;

import com.alibaba.fastjson.JSON;
import com.charge.config.vo.CommentVo;
import com.charge.config.vo.Json;
import com.charge.config.vo.ReplyVo;
import com.charge.config.vo.ReturnMsg;
import com.charge.controller.BaseController;
import com.charge.model.User;
import com.charge.service.front.CommentServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 评论管理
 * @author liumw
 * @date 2016/8/15 0015
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController{
    private static final Logger logger = Logger.getLogger(CommentController.class);

    @Autowired
    private CommentServiceI commentService;

    /**
     * 添加评论
     * *@param authorId 作者Id
     * *@param chargeNo 充电桩编码
     * *@param info 评论信息
     */
    @ResponseBody
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public Json addComment(@ModelAttribute("comment") CommentVo comment){
        Json json = new Json();
        if (isNull(comment.getChargeNo(), comment.getInfo()) || (comment.getAuthorId() == null)){
            json.setMsg("参数错误");
            json.setResult_code(ReturnMsg.PARAMETER_ERROR);
            json.setSuccess(false);
            logger.error("添加评论--参数错误");
            return json;
        }
        try {
            User user = getUserById(comment.getAuthorId());
            if (user == null){
                json.setMsg("用户不存在");
                json.setResult_code(ReturnMsg.USER_NO_EXIST);
                json.setSuccess(false);
                logger.error("添加评论--用户不存在");
                return json;
            }

            json = commentService.addComment(comment.getChargeNo(), comment.getAuthorId(), user.getUsername(), comment.getInfo());
            logger.info(JSON.toJSONString(json));
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("系统错误");
            json.setResult_code(ReturnMsg.SYS_FAIL);
            logger.error("添加评论失败" + e.getMessage());
        }
        return json;
    }


    /**
     * 获取该充电桩评论列表
     * *@param chargeNo 电桩编码
     */
    @ResponseBody
    @RequestMapping(value = "/findAllComment", method = RequestMethod.POST)
    public Json findAllComment(@ModelAttribute("comment") CommentVo comment){
        Json json = new Json();

        if (isNull(comment.getChargeNo())){
            json.setMsg("参数错误");
            json.setResult_code(ReturnMsg.PARAMETER_ERROR);
            json.setSuccess(false);
            logger.error("获取该充电桩评论列表--参数错误");
            return json;
        }
        try {
            json = commentService.findAllComment(comment.getChargeNo());
            logger.info(JSON.toJSONString(json));
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("系统错误");
            json.setResult_code(ReturnMsg.SYS_FAIL);
            logger.error("获取该充电桩评论列表失败" + e.getMessage());
        }
        return json;
    }


    /**
     * 添加回复
     * *@param commentId 评论id notNull
     * *@param authorId 作者id notNull
     * *@param info  内容     notNull
     * *@param replyId   回复对象Id
     *
     */
    @ResponseBody
    @RequestMapping(value = "/addReply", method = RequestMethod.POST)
    public Json addReply(@ModelAttribute("replyVo") ReplyVo replyVo){
        Json json = new Json();

        if (isNull(replyVo.getInfo())
                || (replyVo.getAuthorId() == null) || (replyVo.getFatherCommentId() == null)){
            json.setMsg("参数错误");
            json.setResult_code(ReturnMsg.PARAMETER_ERROR);
            json.setSuccess(false);
            logger.error("添加回复--参数错误");
            return json;
        }

        try {
            User author = getUserById(replyVo.getAuthorId());
            if (author == null){
                json.setMsg("作者不存在");
                json.setResult_code(ReturnMsg.USER_NO_EXIST);
                json.setSuccess(false);
                logger.error("添加回复--用户不存在");
                return json;
            }
            User reply = null;
            if (replyVo.getReplyId()!= null){
                reply = getUserById(replyVo.getReplyId());
                if (reply == null){
                    json.setMsg("回复对象不存在");
                    json.setResult_code(ReturnMsg.USER_NO_EXIST);
                    json.setSuccess(false);
                    logger.error("添加回复--用户不存在");
                    return json;
                }
            }

            json = commentService.addReply(replyVo.getFatherCommentId(), replyVo.getInfo(),
                    replyVo.getAuthorId(), author.getUsername(), replyVo.getReplyId(), reply.getUsername());
            logger.info(JSON.toJSONString(json));
        } catch (Exception e) {
            e.printStackTrace();
            json.setMsg("系统错误");
            json.setResult_code(ReturnMsg.SYS_FAIL);
            logger.error("添加回复失败" + e.getMessage());
        }


        return json;
    }

}
