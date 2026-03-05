package com.medicinebox.common.enums;

/**
 * 报警级别枚举
 */
public enum AlertLevel {
    /**
     * 提醒
     */
    REMINDER(1, "提醒"),
    /**
     * 警告
     */
    WARNING(2, "警告"),
    /**
     * 严重
     */
    SEVERE(3, "严重");

    private final Integer code;
    private final String name;

    AlertLevel(Integer code, String name) {
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
    public static AlertLevel getByCode(Integer code) {
        for (AlertLevel level : values()) {
            if (level.code.equals(code)) {
                return level;
            }
        }
        return null;
    }
}
