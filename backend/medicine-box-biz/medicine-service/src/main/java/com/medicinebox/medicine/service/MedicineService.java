package com.medicinebox.medicine.service;

import com.medicinebox.common.model.Medicine;
import com.medicinebox.common.model.MedicineCategory;
import java.util.List;
import java.util.Optional;

/**
 * 药品服务接口
 */
public interface MedicineService {

    // 药品分类相关方法
    /**
     * 创建药品分类
     * @param category 分类信息
     * @return 创建的分类
     */
    MedicineCategory createCategory(MedicineCategory category);

    /**
     * 根据ID查询分类
     * @param id 分类ID
     * @return 分类信息
     */
    Optional<MedicineCategory> getCategoryById(String id);

    /**
     * 更新分类信息
     * @param category 分类信息
     * @return 更新后的分类
     */
    MedicineCategory updateCategory(MedicineCategory category);

    /**
     * 删除分类（逻辑删除）
     * @param id 分类ID
     */
    void deleteCategory(String id);

    /**
     * 获取所有分类
     * @param deleted 删除状态
     * @return 分类列表
     */
    List<MedicineCategory> getAllCategories(Integer deleted);

    /**
     * 根据父分类ID查询子分类
     * @param parentId 父分类ID
     * @return 子分类列表
     */
    List<MedicineCategory> getSubCategories(String parentId);

    // 药品相关方法
    /**
     * 创建药品
     * @param medicine 药品信息
     * @return 创建的药品
     */
    Medicine createMedicine(Medicine medicine);

    /**
     * 根据ID查询药品
     * @param id 药品ID
     * @return 药品信息
     */
    Optional<Medicine> getMedicineById(String id);

    /**
     * 根据条形码查询药品
     * @param barcode 条形码
     * @return 药品信息
     */
    Optional<Medicine> getMedicineByBarcode(String barcode);

    /**
     * 更新药品信息
     * @param medicine 药品信息
     * @return 更新后的药品
     */
    Medicine updateMedicine(Medicine medicine);

    /**
     * 删除药品（逻辑删除）
     * @param id 药品ID
     */
    void deleteMedicine(String id);

    /**
     * 获取所有药品
     * @param deleted 删除状态
     * @return 药品列表
     */
    List<Medicine> getAllMedicines(Integer deleted);

    /**
     * 根据分类ID查询药品
     * @param categoryId 分类ID
     * @return 药品列表
     */
    List<Medicine> getMedicinesByCategoryId(String categoryId);

    /**
     * 根据状态查询药品
     * @param status 状态
     * @return 药品列表
     */
    List<Medicine> getMedicinesByStatus(Integer status);
}
