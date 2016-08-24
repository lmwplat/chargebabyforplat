package com.charge.config.state;

/**
 * @author liumw
 * @date 2016/8/15 0015
 */
public enum CommentStatusState {
    /** 评论(0) */
    discussion(0, "评论"),
    /** 评论回复 (1)*/
    reply(1, "评论回复");

    /**
     * 值 Integer 型	 */
    private final Integer value;
    /**
     * 描述 String 类型	 */
    private final String description;

    CommentStatusState(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * 获取值	 * @return value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * 获取描述信息
     * @return description
     */
    public String getDescription() {
        return description;
    }

    public static CommentStatusState getReplyStatusState(Integer value) {
        if (null == value)
            return null;
        for (CommentStatusState _enum : CommentStatusState.values()) {
            if (value.equals(_enum.getValue()))
                return _enum;
        }
        return null;
    }

    public static CommentStatusState getReplyStatusState(String description) {
        if (null == description)
            return null;
        for (CommentStatusState _enum : CommentStatusState.values()) {
            if (description.equals(_enum.getDescription()))
                return _enum;
        }
        return null;
    }
}
