package com.charge.config.state;

/**
 * 评论状态
 * @author liumw
 * @date 2016/8/15 0015
 */
public enum StatusState {
    /** 待审核(0) */
    audit(0, "待审核"),
    /** 正常(1)，及审核通过 */
    normal(1, "正常"),
    /** 屏蔽(2) */
    delete(2, "屏蔽");
    /**
     * 值 Integer 型	 */
    private final Integer value;
    /**
     * 描述 String 类型	 */
    private final String description;

    StatusState(Integer value, String description) {
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

    public static StatusState getStatusState(Integer value) {
        if (null == value)
            return null;
        for (StatusState _enum : StatusState.values()) {
            if (value.equals(_enum.getValue()))
                return _enum;
        }
        return null;
    }

    public static StatusState getStatusState(String description) {
        if (null == description)
            return null;
        for (StatusState _enum : StatusState.values()) {
            if (description.equals(_enum.getDescription()))
                return _enum;
        }
        return null;
    }
}
