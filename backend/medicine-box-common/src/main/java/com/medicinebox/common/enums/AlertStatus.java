package com.medicinebox.common.enums;

/**
 * 报警状态枚举
 */
public enum AlertStatus {
    /**
     * 未处理
     */
    UNRESOLVED(0, "未处理"),
    /**
     * 已处理
     */
    RESOLVED(1, "已处理");

    private final Integer code;
    private final String name;

    AlertStatus(Integer code, String name) {
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
    public static AlertStatus getByCode(Integer code) {
        for (AlertStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return null;
    }
}
