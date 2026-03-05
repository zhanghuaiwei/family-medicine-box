package com.medicinebox.medicine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.medicinebox.common.model.Medicine;
import java.util.List;

/**
 * 药品Mapper接口
 */
public interface MedicineMapper extends BaseMapper<Medicine> {

    /**
     * 根据药品名称查询药品
     * @param name 药品名称
     * @return 药品信息
     */
    Medicine findByName(String name);

    /**
     * 根据分类ID查询药品
     * @param categoryId 分类ID
     * @return 药品列表
     */
    List<Medicine> findByCategoryId(String categoryId);

    /**
     * 根据条形码查询药品
     * @param barcode 条形码
     * @return 药品信息
     */
    Medicine findByBarcode(String barcode);

    /**
     * 根据状态查询药品
     * @param status 状态
     * @return 药品列表
     */
    List<Medicine> findByStatus(Integer status);

    /**
     * 统计药品数量
     * @param deleted 删除状态
     * @return 药品数量
     */
    long countByDeleted(Integer deleted);
}
