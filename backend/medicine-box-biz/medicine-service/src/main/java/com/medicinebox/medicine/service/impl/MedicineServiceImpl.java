package com.medicinebox.medicine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.medicinebox.common.model.Medicine;
import com.medicinebox.common.model.MedicineCategory;
import com.medicinebox.medicine.mapper.MedicineCategoryMapper;
import com.medicinebox.medicine.mapper.MedicineMapper;
import com.medicinebox.medicine.service.MedicineService;
import com.medicinebox.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 药品服务实现类
 */
@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineCategoryMapper medicineCategoryMapper;

    @Autowired
    private MedicineMapper medicineMapper;

    // 药品分类相关方法
    /**
     * 创建药品分类
     * @param category 分类信息
     * @return 创建的分类
     */
    @Override
    @Transactional
    public MedicineCategory createCategory(MedicineCategory category) {
        // 生成UUID
        category.setId(UUIDUtil.generateUUID());
        // 设置默认值
        category.setDeleted(0); // 默认未删除
        category.setCreateTime(new Date());
        medicineCategoryMapper.insert(category);
        return category;
    }

    /**
     * 根据ID查询分类
     * @param id 分类ID
     * @return 分类信息
     */
    @Override
    public Optional<MedicineCategory> getCategoryById(String id) {
        MedicineCategory category = medicineCategoryMapper.selectById(id);
        return Optional.ofNullable(category);
    }

    /**
     * 更新分类信息
     * @param category 分类信息
     * @return 更新后的分类
     */
    @Override
    @Transactional
    public MedicineCategory updateCategory(MedicineCategory category) {
        MedicineCategory existingCategory = medicineCategoryMapper.selectById(category.getId());
        if (existingCategory == null) {
            throw new RuntimeException("分类不存在");
        }
        
        // 更新非空字段
        if (category.getCategoryName() != null) {
            existingCategory.setCategoryName(category.getCategoryName());
        }
        if (category.getParentId() != null) {
            existingCategory.setParentId(category.getParentId());
        }
        if (category.getDescription() != null) {
            existingCategory.setDescription(category.getDescription());
        }
        
        medicineCategoryMapper.updateById(existingCategory);
        return existingCategory;
    }

    /**
     * 删除分类（逻辑删除）
     * @param id 分类ID
     */
    @Override
    @Transactional
    public void deleteCategory(String id) {
        MedicineCategory category = medicineCategoryMapper.selectById(id);
        if (category == null) {
            throw new RuntimeException("分类不存在");
        }
        category.setDeleted(1);
        medicineCategoryMapper.updateById(category);
    }

    /**
     * 获取所有分类
     * @param deleted 删除状态
     * @return 分类列表
     */
    @Override
    public List<MedicineCategory> getAllCategories(Integer deleted) {
        QueryWrapper<MedicineCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted", deleted);
        return medicineCategoryMapper.selectList(wrapper);
    }

    /**
     * 根据父分类ID查询子分类
     * @param parentId 父分类ID
     * @return 子分类列表
     */
    @Override
    public List<MedicineCategory> getSubCategories(String parentId) {
        return medicineCategoryMapper.findByParentId(parentId);
    }

    // 药品相关方法
    /**
     * 创建药品
     * @param medicine 药品信息
     * @return 创建的药品
     */
    @Override
    @Transactional
    public Medicine createMedicine(Medicine medicine) {
        // 生成UUID
        medicine.setId(UUIDUtil.generateUUID());
        // 设置默认值
        medicine.setStatus(1); // 默认启用
        medicine.setDeleted(0); // 默认未删除
        medicine.setCreateTime(new Date());
        medicine.setUpdateTime(new Date());
        medicineMapper.insert(medicine);
        return medicine;
    }

    /**
     * 根据ID查询药品
     * @param id 药品ID
     * @return 药品信息
     */
    @Override
    public Optional<Medicine> getMedicineById(String id) {
        Medicine medicine = medicineMapper.selectById(id);
        return Optional.ofNullable(medicine);
    }

    /**
     * 根据条形码查询药品
     * @param barcode 条形码
     * @return 药品信息
     */
    @Override
    public Optional<Medicine> getMedicineByBarcode(String barcode) {
        Medicine medicine = medicineMapper.findByBarcode(barcode);
        return Optional.ofNullable(medicine);
    }

    /**
     * 更新药品信息
     * @param medicine 药品信息
     * @return 更新后的药品
     */
    @Override
    @Transactional
    public Medicine updateMedicine(Medicine medicine) {
        Medicine existingMedicine = medicineMapper.selectById(medicine.getId());
        if (existingMedicine == null) {
            throw new RuntimeException("药品不存在");
        }
        
        // 更新非空字段
        if (medicine.getName() != null) {
            existingMedicine.setName(medicine.getName());
        }
        if (medicine.getCategoryId() != null) {
            existingMedicine.setCategoryId(medicine.getCategoryId());
        }
        if (medicine.getBrand() != null) {
            existingMedicine.setBrand(medicine.getBrand());
        }
        if (medicine.getSpecification() != null) {
            existingMedicine.setSpecification(medicine.getSpecification());
        }
        if (medicine.getDosageForm() != null) {
            existingMedicine.setDosageForm(medicine.getDosageForm());
        }
        if (medicine.getUsageInstructions() != null) {
            existingMedicine.setUsageInstructions(medicine.getUsageInstructions());
        }
        if (medicine.getIndications() != null) {
            existingMedicine.setIndications(medicine.getIndications());
        }
        if (medicine.getContraindications() != null) {
            existingMedicine.setContraindications(medicine.getContraindications());
        }
        if (medicine.getSideEffects() != null) {
            existingMedicine.setSideEffects(medicine.getSideEffects());
        }
        if (medicine.getStorageCondition() != null) {
            existingMedicine.setStorageCondition(medicine.getStorageCondition());
        }
        if (medicine.getExpiryDateDays() != null) {
            existingMedicine.setExpiryDateDays(medicine.getExpiryDateDays());
        }
        if (medicine.getManufacturer() != null) {
            existingMedicine.setManufacturer(medicine.getManufacturer());
        }
        if (medicine.getApprovalNumber() != null) {
            existingMedicine.setApprovalNumber(medicine.getApprovalNumber());
        }
        if (medicine.getPrice() != null) {
            existingMedicine.setPrice(medicine.getPrice());
        }
        if (medicine.getBarcode() != null) {
            existingMedicine.setBarcode(medicine.getBarcode());
        }
        if (medicine.getStatus() != null) {
            existingMedicine.setStatus(medicine.getStatus());
        }
        
        existingMedicine.setUpdateTime(new Date());
        medicineMapper.updateById(existingMedicine);
        return existingMedicine;
    }

    /**
     * 删除药品（逻辑删除）
     * @param id 药品ID
     */
    @Override
    @Transactional
    public void deleteMedicine(String id) {
        Medicine medicine = medicineMapper.selectById(id);
        if (medicine == null) {
            throw new RuntimeException("药品不存在");
        }
        medicine.setDeleted(1);
        medicine.setUpdateTime(new Date());
        medicineMapper.updateById(medicine);
    }

    /**
     * 获取所有药品
     * @param deleted 删除状态
     * @return 药品列表
     */
    @Override
    public List<Medicine> getAllMedicines(Integer deleted) {
        QueryWrapper<Medicine> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted", deleted);
        return medicineMapper.selectList(wrapper);
    }

    /**
     * 根据分类ID查询药品
     * @param categoryId 分类ID
     * @return 药品列表
     */
    @Override
    public List<Medicine> getMedicinesByCategoryId(String categoryId) {
        return medicineMapper.findByCategoryId(categoryId);
    }

    /**
     * 根据状态查询药品
     * @param status 状态
     * @return 药品列表
     */
    @Override
    public List<Medicine> getMedicinesByStatus(Integer status) {
        return medicineMapper.findByStatus(status);
    }
}
