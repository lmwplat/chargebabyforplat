package com.charge.model;

import com.charge.config.state.CommentStatusState;
import com.charge.config.state.StatusState;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment implements Serializable {
    private Long id;
    /**创建时间*/
    private Date createTime;
    /**修改时间*/
    private Date updateTime;
    /**回复状态，默认正常*/
    private Integer status = StatusState.normal.getValue();
    /**评论类型*/
    private Integer type;
    /**回复数量，针对本条评论的回复数,当为null时，即为给评论的回复*/
    private Integer replyNum;
    /**评论内容*/
    private String info;
    /**作者用户名*/
    private String author;
    /**作者ID*/
    private Long authorId;
    /**回复对象*/
    private String reply;
    /**回复对象ID*/
    private Long replyId;
    /**充电桩编码*/
    private String chargeNo;
    /**多对一关系,树结构父节点,即回复父节点*/
    private Long fatherCommentId;
    /**一对多关系,树结构子节点。即评论的孩子节点,*/
    private String childReplyIds;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo == null ? null : chargeNo.trim();
    }

    public Long getFatherCommentId() {
        return fatherCommentId;
    }

    public void setFatherCommentId(Long fatherCommentId) {
        this.fatherCommentId = fatherCommentId;
    }

    public String getChildReplyIds() {
        return childReplyIds;
    }

    public void setChildReplyIds(String childReplyIds) {
        this.childReplyIds = childReplyIds == null ? null : childReplyIds.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Comment other = (Comment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getReplyNum() == null ? other.getReplyNum() == null : this.getReplyNum().equals(other.getReplyNum()))
            && (this.getInfo() == null ? other.getInfo() == null : this.getInfo().equals(other.getInfo()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getAuthorId() == null ? other.getAuthorId() == null : this.getAuthorId().equals(other.getAuthorId()))
            && (this.getReply() == null ? other.getReply() == null : this.getReply().equals(other.getReply()))
            && (this.getReplyId() == null ? other.getReplyId() == null : this.getReplyId().equals(other.getReplyId()))
            && (this.getChargeNo() == null ? other.getChargeNo() == null : this.getChargeNo().equals(other.getChargeNo()))
            && (this.getFatherCommentId() == null ? other.getFatherCommentId() == null : this.getFatherCommentId().equals(other.getFatherCommentId()))
            && (this.getChildReplyIds() == null ? other.getChildReplyIds() == null : this.getChildReplyIds().equals(other.getChildReplyIds()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getReplyNum() == null) ? 0 : getReplyNum().hashCode());
        result = prime * result + ((getInfo() == null) ? 0 : getInfo().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getAuthorId() == null) ? 0 : getAuthorId().hashCode());
        result = prime * result + ((getReply() == null) ? 0 : getReply().hashCode());
        result = prime * result + ((getReplyId() == null) ? 0 : getReplyId().hashCode());
        result = prime * result + ((getChargeNo() == null) ? 0 : getChargeNo().hashCode());
        result = prime * result + ((getFatherCommentId() == null) ? 0 : getFatherCommentId().hashCode());
        result = prime * result + ((getChildReplyIds() == null) ? 0 : getChildReplyIds().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", replyNum=").append(replyNum);
        sb.append(", info=").append(info);
        sb.append(", author=").append(author);
        sb.append(", authorId=").append(authorId);
        sb.append(", reply=").append(reply);
        sb.append(", replyId=").append(replyId);
        sb.append(", chargeNo=").append(chargeNo);
        sb.append(", fatherCommentId=").append(fatherCommentId);
        sb.append(", childReplyIds=").append(childReplyIds);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 用于业务逻辑的字段，代表不需要持久化到数据库中
     *
     * @return
     */
    /**
     *  评论状态描述
     */
    public String getCommentStatusView() {
        StatusState r = StatusState.getStatusState(status);
        String str = "";
        if(r != null){
            str = r.getDescription();
        }
        return str;
    }

    /**
     *  评论类型描述
     */
    public String getCommentTypeView() {
        CommentStatusState r = CommentStatusState.getReplyStatusState(type);
        String str = "";
        if(r != null){
            str = r.getDescription();
        }
        return str;
    }

    /**
     * 获取所有的回复
     */
    public List<Long> getChildReplyList(){
        List<Long> replyList = new ArrayList<Long>();
        if(StringUtils.isEmpty(childReplyIds)) {
            return new ArrayList<Long>();
        }
        String[] replyIdStrs = childReplyIds.split(",");
        for(String replyIdStr : replyIdStrs) {
            if(StringUtils.isEmpty(replyIdStr)) {
                continue;
            }
            replyList.add(Long.valueOf(replyIdStr));
        }
        return replyList;
    }

    /**
     * 获取所有的回复
     */
    public void setChildReplyList(List<Long> replyIdsList){
        if(CollectionUtils.isEmpty(replyIdsList)) {
            childReplyIds = "";
            return ;
        }
        StringBuilder s = new StringBuilder();
        for(Long replyId : replyIdsList) {
            s.append(replyId);
            s.append(",");
        }
        childReplyIds = s.toString();
    }
}