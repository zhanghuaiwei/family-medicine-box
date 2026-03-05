package com.medicinebox.common.enums;

/**
 * 库存变动类型枚举
 */
public enum InventoryChangeType {
    /**
     * 入库
     */
    IN(1, "入库"),
    /**
     * 出库
     */
    OUT(2, "出库"),
    /**
     * 调整
     */
    ADJUST(3, "调整");

    private final Integer code;
    private final String name;

    InventoryChangeType(Integer code, String name) {
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
    public static InventoryChangeType getByCode(Integer code) {
        for (InventoryChangeType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
}
