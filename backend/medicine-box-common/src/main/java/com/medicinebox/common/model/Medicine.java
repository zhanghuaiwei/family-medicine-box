package com.medicinebox.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

/**
 * 药品模型
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Medicine extends BaseModel {
    /**
     * 药品名称
     */
    private String name;

    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 规格
     */
    private String specification;

    /**
     * 剂型（片剂、胶囊、颗粒等）
     */
    private String dosageForm;

    /**
     * 用法用量
     */
    private String usageInstructions;

    /**
     * 适应症
     */
    private String indications;

    /**
     * 禁忌症
     */
    private String contraindications;

    /**
     * 不良反应
     */
    private String sideEffects;

    /**
     * 储存条件
     */
    private String storageCondition;

    /**
     * 保质期（天数）
     */
    private Integer expiryDateDays;

    /**
     * 生产厂家
     */
    private String manufacturer;

    /**
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 条形码
     */
    private String barcode;

    /**
     * 状态（1-正常，0-停用）
     */
    private Integer status;
}
