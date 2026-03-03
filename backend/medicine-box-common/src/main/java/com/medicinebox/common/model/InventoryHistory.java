package com.medicinebox.common.model;

import lombok.Data;
import java.util.Date;

/**
 * 库存变动记录模型
 */
@Data
public class InventoryHistory {
    /**
     * 记录ID（UUID）
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 库存ID
     */
    private String inventoryId;

    /**
     * 药品ID
     */
    private String medicineId;

    /**
     * 变动类型（1-增加，2-减少）
     */
    private Integer changeType;

    /**
     * 变动数量
     */
    private Integer changeQuantity;

    /**
     * 变动前数量
     */
    private Integer beforeQuantity;

    /**
     * 变动后数量
     */
    private Integer afterQuantity;

    /**
     * 变动原因
     */
    private String reason;

    /**
     * 变动时间
     */
    private Date changeTime;
}
