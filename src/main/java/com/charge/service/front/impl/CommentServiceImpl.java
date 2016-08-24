package com.charge.service.front.impl;

import com.charge.config.state.CommentStatusState;
import com.charge.config.utils.BeanUtils;
import com.charge.config.vo.CommentVo;
import com.charge.config.vo.Json;
import com.charge.config.vo.ReplyVo;
import com.charge.config.vo.ReturnMsg;
import com.charge.dao.CommentMapper;
import com.charge.model.Charge;
import com.charge.model.Comment;
import com.charge.service.front.ChargeServiceI;
import com.charge.service.front.CommentServiceI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论---接口实现
 * @author liumw
 * @date 2016/8/15 0015
 */
@Service("commentService")
public class CommentServiceImpl implements CommentServiceI {
    private static final Logger logger = Logger.getLogger(CommentServiceImpl.class);
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ChargeServiceI chargeService;
    /**
     * 添加评论
     * @param chargeNo
     * @param authorId
     * @param author
     * @param info
     * @return
     */
    @Override
    public Json addComment(String chargeNo, Long authorId, String author, String info) throws Exception {
        Json json = new Json();
        //判断chargeNo，是否合法
        Charge charge = chargeService.getChargeByNo(chargeNo);
        if (charge == null){
            json.setMsg("充电桩不存在");
            json.setResult_code(ReturnMsg.CHARGE_NO_EXIST);
            json.setSuccess(false);
            logger.error(author + "添加评论--充电桩不存在");
            return json;
        }

        Comment comment = new Comment();
        comment.setChargeNo(chargeNo);
        comment.setAuthor(author);
        comment.setAuthorId(authorId);
        comment.setInfo(info);
        comment.setType(CommentStatusState.discussion.getValue());
        comment.setReplyNum(0);//初始值为0
        commentMapper.insert(comment);

        List<Comment> commentList = commentMapper.findAllCommentByChargeNo(chargeNo);
        json.setMsg("成功");
        json.setResult_code(ReturnMsg.SUCCESS);
        json.setSuccess(true);
        json.setObj(commentList);
        logger.info(author + "添加评论--成功");
        return json;

    }

    /**
     * 根据电桩编码，获取所有评论
     * @param chargeNo
     * @return
     */
    @Override
    public Json findAllComment(String chargeNo) {
        Json json = new Json();
        List<Comment> commentList = commentMapper.findAllCommentByChargeNo(chargeNo);
        List<CommentVo> commentVos = new ArrayList<CommentVo>();
        for (Comment comment: commentList) {
            CommentVo commentVo = new CommentVo();
            BeanUtils.copyNotNullProperties(comment, commentVo);
            List<ReplyVo> replyVos = new ArrayList<ReplyVo>();
            List<Comment> replyList = commentMapper.findAllReplyByCommentId(comment.getId());
            for (Comment reply: replyList) {
                ReplyVo replyVo = new ReplyVo();
                BeanUtils.copyNotNullProperties(reply, replyVo);
                replyVos.add(replyVo);
            }
            commentVo.setReplyVoList(replyVos);
            commentVos.add(commentVo);
        }
        json.setMsg("成功");
        json.setResult_code(ReturnMsg.SUCCESS);
        json.setSuccess(true);
        json.setObj(commentVos);
        logger.info("获取所有评论--成功");
        return json;
    }

    /**
     * 添加回复
     * @param author
     * @param info
     * @param authorId
     * @param fatherCommentId
     * @param reply
     * @param replyId
     * @return
     */
    @Override
    public Json addReply(Long fatherCommentId, String info, Long authorId, String author,Long replyId, String reply) throws Exception {
        Json json = new Json();
        //判断commentId,是否合法
        Comment comment = commentMapper.selectByPrimaryKey(fatherCommentId);
        if (comment == null){
            json.setMsg("评论不存在");
            json.setResult_code(ReturnMsg.COMMENT_EXIST);
            json.setSuccess(false);
            logger.error(author + "添加回复--充电桩不存在");
            return json;
        }
        //判断chargeNo，是否合法
        Charge charge = chargeService.getChargeByNo(comment.getChargeNo());
        if (charge == null){
            json.setMsg("充电桩不存在");
            json.setResult_code(ReturnMsg.CHARGE_NO_EXIST);
            json.setSuccess(false);
            logger.error(author + "添加回复--充电桩不存在");
            return json;
        }

        Comment commentReply = new Comment();
        commentReply.setChargeNo(comment.getChargeNo());
        commentReply.setAuthor(author);
        commentReply.setAuthorId(authorId);
        commentReply.setInfo(info);
        commentReply.setType(CommentStatusState.reply.getValue());
        commentReply.setReply(reply);
        commentReply.setReplyId(replyId);
        commentReply.setFatherCommentId(fatherCommentId);
        commentMapper.insert(commentReply);

        List<Long> childReplyList = comment.getChildReplyList();
        childReplyList.add(commentReply.getId());
        comment.setChildReplyList(childReplyList);
        commentMapper.updateByPrimaryKey(comment);

        json.setMsg("成功");
        json.setResult_code(ReturnMsg.SUCCESS);
        json.setSuccess(true);
        logger.info(author + "添加回复--成功");
        return json;
    }


}
