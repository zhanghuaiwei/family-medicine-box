package com.medicinebox.common.model;

import lombok.Data;
import java.util.Date;

/**
 * 用药记录模型
 */
@Data
public class UsageRecord {
    /**
     * 记录ID（UUID）
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
     * 使用数量
     */
    private Integer usageQuantity;

    /**
     * 使用时间
     */
    private Date usageTime;

    /**
     * 备注
     */
    private String notes;

    /**
     * 创建时间
     */
    private Date createTime;
}
