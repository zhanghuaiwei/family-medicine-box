package com.medicinebox.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 库存模型
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Inventory extends BaseModel {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 药品ID
     */
    private String medicineId;

    /**
     * 批次号
     */
    private String batchNumber;

    /**
     * 购买日期
     */
    private Date purchaseDate;

    /**
     * 过期日期
     */
    private Date expiryDate;

    /**
     * 当前数量
     */
    private Integer currentQuantity;

    /**
     * 最低库存
     */
    private Integer minStock;

    /**
     * 单位（瓶、盒、片等）
     */
    private String unit;

    /**
     * 存放位置
     */
    private String location;

    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;
}
