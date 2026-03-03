package com.medicinebox.user.controller;

import com.medicinebox.common.model.User;
import com.medicinebox.common.response.Response;
import com.medicinebox.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户Controller
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 创建用户
     * @param user 用户信息
     * @return 响应结果
     */
    @PostMapping
    public Response<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return Response.success(createdUser);
    }

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 响应结果
     */
    @GetMapping("/{id}")
    public Response<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        return Response.success(user);
    }

    /**
     * 根据手机号查询用户
     * @param phone 手机号
     * @return 响应结果
     */
    @GetMapping("/phone/{phone}")
    public Response<User> getUserByPhone(@PathVariable String phone) {
        User user = userService.getUserByPhone(phone)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        return Response.success(user);
    }

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 响应结果
     */
    @PutMapping
    public Response<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return Response.success(updatedUser);
    }

    /**
     * 删除用户（逻辑删除）
     * @param id 用户ID
     * @return 响应结果
     */
    @DeleteMapping("/{id}")
    public Response<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return Response.success();
    }

    /**
     * 获取所有用户
     * @param deleted 删除状态
     * @return 响应结果
     */
    @GetMapping
    public Response<List<User>> getAllUsers(@RequestParam Integer deleted) {
        List<User> users = userService.getAllUsers(deleted);
        return Response.success(users);
    }

    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @return 响应结果
     */
    @PostMapping("/login")
    public Response<User> login(@RequestParam String phone, @RequestParam String password) {
        User user = userService.login(phone, password);
        return Response.success(user);
    }
}
