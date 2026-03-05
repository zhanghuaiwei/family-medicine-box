package com.medicinebox.common.enums;

/**
 * 药品状态枚举
 */
public enum MedicineStatus {
    /**
     * 禁用
     */
    DISABLED(0, "禁用"),
    /**
     * 启用
     */
    ENABLED(1, "启用");

    private final Integer code;
    private final String name;

    MedicineStatus(Integer code, String name) {
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
    public static MedicineStatus getByCode(Integer code) {
        for (MedicineStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return null;
    }
}
