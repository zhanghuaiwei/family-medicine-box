package com.medicinebox.medicine.repository;

import com.medicinebox.common.model.MedicineCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 药品分类Repository接口
 */
public interface MedicineCategoryRepository extends JpaRepository<MedicineCategory, String> {

    /**
     * 根据分类名称查询分类
     * @param categoryName 分类名称
     * @return 分类信息
     */
    Optional<MedicineCategory> findByCategoryName(String categoryName);

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
