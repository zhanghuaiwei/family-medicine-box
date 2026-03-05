package com.medicinebox.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 提醒设置模型类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AlertSetting extends BaseModel {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 报警类型
     */
    private Integer alertType;

    /**
     * 报警级别
     */
    private Integer alertLevel;

    /**
     * 阈值（如低库存阈值）
     */
    private Integer threshold;

    /**
     * 是否启用
     */
    private Integer enabled;

    /**
     * 通知方式（短信、微信、邮件，逗号分隔）
     */
    private String notificationMethods;

    /**
     * 更新时间
     */
    private Date updateTime;
}
