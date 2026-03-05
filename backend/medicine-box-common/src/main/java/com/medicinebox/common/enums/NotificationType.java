package com.medicinebox.common.enums;

/**
 * 通知类型枚举
 */
public enum NotificationType {
    /**
     * 系统通知
     */
    SYSTEM(1, "系统通知"),
    /**
     * 库存通知
     */
    INVENTORY(2, "库存通知"),
    /**
     * 用药提醒
     */
    MEDICATION(3, "用药提醒"),
    /**
     * 报警通知
     */
    ALERT(4, "报警通知");

    private final Integer code;
    private final String name;

    NotificationType(Integer code, String name) {
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
    public static NotificationType getByCode(Integer code) {
        for (NotificationType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
