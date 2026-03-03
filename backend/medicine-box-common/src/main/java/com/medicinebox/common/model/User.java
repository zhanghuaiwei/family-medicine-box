package com.medicinebox.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 用户模型
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码（加密存储）
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 性别（0-未知，1-男，2-女）
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date birthdate;

    /**
     * 角色（0-普通用户，1-管理员）
     */
    private Integer role;

    /**
     * 状态（1-正常，0-禁用）
     */
    private Integer status;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
}
