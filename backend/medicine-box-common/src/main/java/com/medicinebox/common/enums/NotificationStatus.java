package com.medicinebox.common.enums;

/**
 * 通知状态枚举
 */
public enum NotificationStatus {
    /**
     * 未读
     */
    UNREAD(0, "未读"),
    /**
     * 已读
     */
    READ(1, "已读");

    private final Integer code;
    private final String name;

    NotificationStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据代码获取枚举
     * @param code 代码
     * @return 枚举
     */
    public static NotificationStatus getByCode(Integer code) {
        for (NotificationStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return null;
    }
}
