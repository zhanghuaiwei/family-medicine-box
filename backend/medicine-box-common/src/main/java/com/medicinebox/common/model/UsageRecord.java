package com.medicinebox.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 用药记录模型类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UsageRecord extends BaseModel {
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
}
