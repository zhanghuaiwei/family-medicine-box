package com.medicinebox.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 药品推荐模型
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MedicineRecommendation extends BaseModel {
    /**
     * 推荐类别（按人群、季节等）
     */
    private String category;

    /**
     * 目标人群（小孩、成人、老人、男人、女人）
     */
    private String targetGroup;

    /**
     * 季节（春、夏、秋、冬）
     */
    private String season;

    /**
     * 推荐药品列表（JSON格式）
     */
    private String recommendedMedicines;

    /**
     * 推荐说明
     */
    private String description;

    /**
     * 更新时间
     */
    private Date updateTime;
}
