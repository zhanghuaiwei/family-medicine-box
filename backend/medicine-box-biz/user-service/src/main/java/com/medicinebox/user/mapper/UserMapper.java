package com.medicinebox.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.medicinebox.common.model.User;

/**
 * 用户Mapper接口
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据手机号查询用户
     * @param phone 手机号
     * @return 用户信息
     */
    User findByPhone(String phone);

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 用户信息
     */
    User findByEmail(String email);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);

    /**
     * 根据手机号和状态查询用户
     * @param phone 手机号
     * @param status 状态
     * @return 用户信息
     */
    User findByPhoneAndStatus(String phone, Integer status);

    /**
     * 统计用户数量
     * @param deleted 删除状态
     * @return 用户数量
     */
    long countByDeleted(Integer deleted);
}
