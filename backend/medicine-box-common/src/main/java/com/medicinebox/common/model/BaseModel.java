package com.medicinebox.common.model;

import lombok.Data;
import java.util.Date;

/**
 * 基础模型类，包含通用字段
 */
@Data
public class BaseModel {
    /**
     * 主键ID（UUID）
     */
    private String id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除标识（0-未删除，1-已删除）
     */
    private Integer deleted;
}
