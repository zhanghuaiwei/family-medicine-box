package com.medicinebox.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 通知模型类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Notification extends BaseModel {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 通知类型（1-系统通知，2-报警通知，3-用药提醒）
     */
    private Integer type;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 是否已读
     */
    private Integer isRead;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 阅读时间
     */
    private Date readTime;
}
