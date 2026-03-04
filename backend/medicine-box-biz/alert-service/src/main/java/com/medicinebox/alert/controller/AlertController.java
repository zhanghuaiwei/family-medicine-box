package com.medicinebox.alert.controller;

import com.medicinebox.common.model.Alert;
import com.medicinebox.common.model.AlertSetting;
import com.medicinebox.common.response.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 提醒Controller
 */
@RestController
@RequestMapping("/api/alert")
public class AlertController {

    /**
     * 创建提醒
     * @param alert 提醒信息
     * @return 响应结果
     */
    @PostMapping
    public Response<Alert> createAlert(@RequestBody Alert alert) {
        // 实现逻辑
        return Response.success(alert);
    }

    /**
     * 根据ID查询提醒
     * @param id 提醒ID
     * @return 响应结果
     */
    @GetMapping("/{id}")
    public Response<Alert> getAlertById(@PathVariable String id) {
        // 实现逻辑
        Alert alert = new Alert();
        return Response.success(alert);
    }

    /**
     * 更新提醒信息
     * @param alert 提醒信息
     * @return 响应结果
     */
    @PutMapping
    public Response<Alert> updateAlert(@RequestBody Alert alert) {
        // 实现逻辑
        return Response.success(alert);
    }

    /**
     * 删除提醒（逻辑删除）
     * @param id 提醒ID
     * @return 响应结果
     */
    @DeleteMapping("/{id}")
    public Response<Void> deleteAlert(@PathVariable String id) {
        // 实现逻辑
        return Response.success();
    }

    /**
     * 获取所有提醒
     * @param deleted 删除状态
     * @return 响应结果
     */
    @GetMapping
    public Response<List<Alert>> getAllAlerts(@RequestParam Integer deleted) {
        // 实现逻辑
        return Response.success(null);
    }

    /**
     * 根据用户ID查询提醒
     * @param userId 用户ID
     * @return 响应结果
     */
    @GetMapping("/user/{userId}")
    public Response<List<Alert>> getAlertsByUserId(@PathVariable String userId) {
        // 实现逻辑
        return Response.success(null);
    }

    /**
     * 创建提醒设置
     * @param setting 提醒设置
     * @return 响应结果
     */
    @PostMapping("/setting")
    public Response<AlertSetting> createAlertSetting(@RequestBody AlertSetting setting) {
        // 实现逻辑
        return Response.success(setting);
    }

    /**
     * 根据ID查询提醒设置
     * @param id 设置ID
     * @return 响应结果
     */
    @GetMapping("/setting/{id}")
    public Response<AlertSetting> getAlertSettingById(@PathVariable String id) {
        // 实现逻辑
        AlertSetting setting = new AlertSetting();
        return Response.success(setting);
    }

    /**
     * 更新提醒设置
     * @param setting 提醒设置
     * @return 响应结果
     */
    @PutMapping("/setting")
    public Response<AlertSetting> updateAlertSetting(@RequestBody AlertSetting setting) {
        // 实现逻辑
        return Response.success(setting);
    }

    /**
     * 根据用户ID查询提醒设置
     * @param userId 用户ID
     * @return 响应结果
     */
    @GetMapping("/setting/user/{userId}")
    public Response<AlertSetting> getAlertSettingByUserId(@PathVariable String userId) {
        // 实现逻辑
        AlertSetting setting = new AlertSetting();
        return Response.success(setting);
    }
}