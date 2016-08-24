package com.charge.dao;

import com.charge.config.vo.ReplyVo;
import com.charge.model.Comment;
import com.charge.model.CommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {
    int countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExampleWithBLOBs(CommentExample example);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExampleWithBLOBs(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    /**根据电桩编码，获取所有评论列表*/
    List<Comment> findAllCommentByChargeNo(@Param("chargeNo")String chargeNo);
    /**根据评论id，获取所有回复*/
    List<Comment> findAllReplyByCommentId(@Param("fatherCommentId")Long fatherCommentId);
    /**根据评论id和电桩编码，获取评论*/
    Comment selectByIdAndChargeNo(@Param("fatherCommentId")Long fatherCommentId, @Param("chargeNo")String chargeNo);
}