package com.charge.service.front;

import com.charge.config.vo.Json;

/**
 * 评论---接口
 * @author liumw
 * @date 2016/8/15 0015
 */
public interface CommentServiceI {
    /**添加评论*/
    Json addComment(String chargeNo, Long authorId, String username, String info) throws Exception;
    /**根据电桩编码，获取所有评论*/
    Json findAllComment(String chargeNo);
    /**添加回复*/
    Json addReply(Long fatherCommentId, String info, Long authorId, String author,Long replyId, String reply) throws Exception;
}
