package com.medicinebox.common.enums;

/**
 * 报警类型枚举
 */
public enum AlertType {
    /**
     * 低库存
     */
    LOW_STOCK(1, "低库存"),
    /**
     * 即将过期
     */
    SOON_EXPIRE(2, "即将过期"),
    /**
     * 已过期
     */
    EXPIRED(3, "已过期");

    private final Integer code;
    private final String name;

    AlertType(Integer code, String name) {
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
    public static AlertType getByCode(Integer code) {
        for (AlertType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
