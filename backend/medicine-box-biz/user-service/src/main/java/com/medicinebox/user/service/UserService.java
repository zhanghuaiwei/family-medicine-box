package com.medicinebox.user.service;

import com.medicinebox.common.model.User;
import java.util.List;
import java.util.Optional;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 创建用户
     * @param user 用户信息
     * @return 创建的用户
     */
    User createUser(User user);

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    Optional<User> getUserById(String id);

    /**
     * 根据手机号查询用户
     * @param phone 手机号
     * @return 用户信息
     */
    Optional<User> getUserByPhone(String phone);

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 用户信息
     */
    Optional<User> getUserByEmail(String email);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 更新后的用户
     */
    User updateUser(User user);

    /**
     * 删除用户（逻辑删除）
     * @param id 用户ID
     */
    void deleteUser(String id);

    /**
     * 获取所有用户
     * @param deleted 删除状态
     * @return 用户列表
     */
    List<User> getAllUsers(Integer deleted);

    /**
     * 分页查询用户
     * @param page 页码
     * @param size 每页大小
     * @param deleted 删除状态
     * @return 分页结果
     */
    com.baomidou.mybatisplus.extension.plugins.pagination.Page<User> getUsersByPage(int page, int size, Integer deleted);

    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @return 登录成功的用户
     */
    User login(String phone, String password);

    /**
     * 更新用户登录时间
     * @param id 用户ID
     */
    void updateLastLoginTime(String id);
}
