package com.medicinebox.common.enums;

/**
 * 用户角色枚举
 */
public enum UserRole {
    /**
     * 普通用户
     */
    NORMAL(0, "普通用户"),
    /**
     * 管理员
     */
    ADMIN(1, "管理员");

    private final Integer code;
    private final String name;

    UserRole(Integer code, String name) {
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
    public static UserRole getByCode(Integer code) {
        for (UserRole role : values()) {
            if (role.code.equals(code)) {
                return role;
            }
        }
        return null;
    }
}
