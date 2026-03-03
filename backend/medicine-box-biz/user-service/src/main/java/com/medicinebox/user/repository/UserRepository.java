package com.medicinebox.user.repository;

import com.medicinebox.common.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * 用户Repository接口
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 根据手机号查询用户
     * @param phone 手机号
     * @return 用户信息
     */
    Optional<User> findByPhone(String phone);

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 用户信息
     */
    Optional<User> findByEmail(String email);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    Optional<User> findByUsername(String username);

    /**
     * 根据手机号和状态查询用户
     * @param phone 手机号
     * @param status 状态
     * @return 用户信息
     */
    Optional<User> findByPhoneAndStatus(String phone, Integer status);

    /**
     * 统计用户数量
     * @param deleted 删除状态
     * @return 用户数量
     */
    long countByDeleted(Integer deleted);
}
