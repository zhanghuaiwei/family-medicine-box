package com.medicinebox.common.model;

import lombok.Data;
import java.util.Date;

/**
 * 报警模型
 */
@Data
public class Alert {
    /**
     * 报警ID（UUID）
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 药品ID
     */
    private String medicineId;

    /**
     * 报警类型（1-低库存，2-即将过期，3-已过期）
     */
    private Integer alertType;

    /**
     * 报警级别（1-提醒，2-警告，3-严重）
     */
    private Integer alertLevel;

    /**
     * 报警消息
     */
    private String message;

    /**
     * 状态（0-未处理，1-已处理）
     */
    private Integer status;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 解决时间
     */
    private Date resolveTime;

    /**
     * 创建时间
     */
    private Date createTime;
}
