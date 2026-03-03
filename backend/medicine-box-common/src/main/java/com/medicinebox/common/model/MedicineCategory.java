package com.medicinebox.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 药品分类模型
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MedicineCategory extends BaseModel {
    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 父分类ID
     */
    private String parentId;

    /**
     * 分类描述
     */
    private String description;
}
