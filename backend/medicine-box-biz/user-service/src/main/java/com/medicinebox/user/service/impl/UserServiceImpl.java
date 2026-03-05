package com.medicinebox.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.medicinebox.common.model.User;
import com.medicinebox.user.mapper.UserMapper;
import com.medicinebox.user.service.UserService;
import com.medicinebox.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 创建用户
     * @param user 用户信息
     * @return 创建的用户
     */
    @Override
    @Transactional
    public User createUser(User user) {
        // 生成UUID
        user.setId(UUIDUtil.generateUUID());
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 设置默认值
        user.setRole(0); // 默认普通用户
        user.setStatus(1); // 默认启用
        user.setDeleted(0); // 默认未删除
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insert(user);
        return user;
    }

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
    public Optional<User> getUserById(String id) {
        User user = userMapper.selectById(id);
        return Optional.ofNullable(user);
    }

    /**
     * 根据手机号查询用户
     * @param phone 手机号
     * @return 用户信息
     */
    @Override
    public Optional<User> getUserByPhone(String phone) {
        User user = userMapper.findByPhone(phone);
        return Optional.ofNullable(user);
    }

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 用户信息
     */
    @Override
    public Optional<User> getUserByEmail(String email) {
        User user = userMapper.findByEmail(email);
        return Optional.ofNullable(user);
    }

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 更新后的用户
     */
    @Override
    @Transactional
    public User updateUser(User user) {
        User existingUser = userMapper.selectById(user.getId());
        if (existingUser == null) {
            throw new RuntimeException("用户不存在");
        }
        
        // 更新非空字段
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getPhone() != null) {
            existingUser.setPhone(user.getPhone());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getNickname() != null) {
            existingUser.setNickname(user.getNickname());
        }
        if (user.getAvatar() != null) {
            existingUser.setAvatar(user.getAvatar());
        }
        if (user.getGender() != null) {
            existingUser.setGender(user.getGender());
        }
        if (user.getBirthdate() != null) {
            existingUser.setBirthdate(user.getBirthdate());
        }
        if (user.getRole() != null) {
            existingUser.setRole(user.getRole());
        }
        if (user.getStatus() != null) {
            existingUser.setStatus(user.getStatus());
        }
        
        existingUser.setUpdateTime(new Date());
        userMapper.updateById(existingUser);
        return existingUser;
    }

    /**
     * 删除用户（逻辑删除）
     * @param id 用户ID
     */
    @Override
    @Transactional
    public void deleteUser(String id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        user.setDeleted(1);
        user.setUpdateTime(new Date());
        userMapper.updateById(user);
    }

    /**
     * 获取所有用户
     * @param deleted 删除状态
     * @return 用户列表
     */
    @Override
    public List<User> getAllUsers(Integer deleted) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted", deleted);
        return userMapper.selectList(wrapper);
    }

    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @return 登录成功的用户
     */
    @Override
    @Transactional
    public User login(String phone, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone)
               .eq("status", 1);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new RuntimeException("用户不存在或已禁用");
        }
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        
        // 更新登录时间
        updateLastLoginTime(user.getId());
        return user;
    }

    /**
     * 更新用户登录时间
     * @param id 用户ID
     */
    @Override
    @Transactional
    public void updateLastLoginTime(String id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        user.setLastLoginTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.updateById(user);
    }
}
