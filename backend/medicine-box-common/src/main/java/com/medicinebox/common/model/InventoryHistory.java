package com.medicinebox.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 库存历史模型类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InventoryHistory extends BaseModel {
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
