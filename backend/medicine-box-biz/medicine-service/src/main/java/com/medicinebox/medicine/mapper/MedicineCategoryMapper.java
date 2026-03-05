package com.medicinebox.medicine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.medicinebox.common.model.MedicineCategory;
import java.util.List;

/**
 * 药品分类Mapper接口
 */
public interface MedicineCategoryMapper extends BaseMapper<MedicineCategory> {

    /**
     * 根据分类名称查询分类
     * @param categoryName 分类名称
     * @return 分类信息
     */
    MedicineCategory findByCategoryName(String categoryName);

    /**
     * 根据父分类ID查询子分类
     * @param parentId 父分类ID
     * @return 子分类列表
     */
    List<MedicineCategory> findByParentId(String parentId);

    /**
     * 统计分类数量
     * @param deleted 删除状态
     * @return 分类数量
     */
    long countByDeleted(Integer deleted);
}
