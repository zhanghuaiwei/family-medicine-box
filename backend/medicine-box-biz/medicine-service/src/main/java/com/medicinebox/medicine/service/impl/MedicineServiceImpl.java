package com.medicinebox.medicine.service.impl;

import com.medicinebox.common.model.Medicine;
import com.medicinebox.common.model.MedicineCategory;
import com.medicinebox.medicine.repository.MedicineCategoryRepository;
import com.medicinebox.medicine.repository.MedicineRepository;
import com.medicinebox.medicine.service.MedicineService;
import com.medicinebox.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 药品服务实现类
 */
@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineCategoryRepository medicineCategoryRepository;

    @Autowired
    private MedicineRepository medicineRepository;

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
        return medicineCategoryRepository.save(category);
    }

    /**
     * 根据ID查询分类
     * @param id 分类ID
     * @return 分类信息
     */
    @Override
    public Optional<MedicineCategory> getCategoryById(String id) {
        return medicineCategoryRepository.findById(id);
    }

    /**
     * 更新分类信息
     * @param category 分类信息
     * @return 更新后的分类
     */
    @Override
    @Transactional
    public MedicineCategory updateCategory(MedicineCategory category) {
        MedicineCategory existingCategory = medicineCategoryRepository.findById(category.getId())
                .orElseThrow(() -> new RuntimeException("分类不存在"));
        
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
        
        return medicineCategoryRepository.save(existingCategory);
    }

    /**
     * 删除分类（逻辑删除）
     * @param id 分类ID
     */
    @Override
    @Transactional
    public void deleteCategory(String id) {
        MedicineCategory category = medicineCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("分类不存在"));
        category.setDeleted(1);
        medicineCategoryRepository.save(category);
    }

    /**
     * 获取所有分类
     * @param deleted 删除状态
     * @return 分类列表
     */
    @Override
    public List<MedicineCategory> getAllCategories(Integer deleted) {
        return medicineCategoryRepository.findAll().stream()
                .filter(category -> category.getDeleted().equals(deleted))
                .collect(Collectors.toList());
    }

    /**
     * 根据父分类ID查询子分类
     * @param parentId 父分类ID
     * @return 子分类列表
     */
    @Override
    public List<MedicineCategory> getSubCategories(String parentId) {
        return medicineCategoryRepository.findByParentId(parentId);
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
        return medicineRepository.save(medicine);
    }

    /**
     * 根据ID查询药品
     * @param id 药品ID
     * @return 药品信息
     */
    @Override
    public Optional<Medicine> getMedicineById(String id) {
        return medicineRepository.findById(id);
    }

    /**
     * 根据条形码查询药品
     * @param barcode 条形码
     * @return 药品信息
     */
    @Override
    public Optional<Medicine> getMedicineByBarcode(String barcode) {
        return medicineRepository.findByBarcode(barcode);
    }

    /**
     * 更新药品信息
     * @param medicine 药品信息
     * @return 更新后的药品
     */
    @Override
    @Transactional
    public Medicine updateMedicine(Medicine medicine) {
        Medicine existingMedicine = medicineRepository.findById(medicine.getId())
                .orElseThrow(() -> new RuntimeException("药品不存在"));
        
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
        if (medicine.getUsage() != null) {
            existingMedicine.setUsage(medicine.getUsage());
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
        return medicineRepository.save(existingMedicine);
    }

    /**
     * 删除药品（逻辑删除）
     * @param id 药品ID
     */
    @Override
    @Transactional
    public void deleteMedicine(String id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("药品不存在"));
        medicine.setDeleted(1);
        medicine.setUpdateTime(new Date());
        medicineRepository.save(medicine);
    }

    /**
     * 获取所有药品
     * @param deleted 删除状态
     * @return 药品列表
     */
    @Override
    public List<Medicine> getAllMedicines(Integer deleted) {
        return medicineRepository.findAll().stream()
                .filter(medicine -> medicine.getDeleted().equals(deleted))
                .collect(Collectors.toList());
    }

    /**
     * 根据分类ID查询药品
     * @param categoryId 分类ID
     * @return 药品列表
     */
    @Override
    public List<Medicine> getMedicinesByCategoryId(String categoryId) {
        return medicineRepository.findByCategoryId(categoryId);
    }

    /**
     * 根据状态查询药品
     * @param status 状态
     * @return 药品列表
     */
    @Override
    public List<Medicine> getMedicinesByStatus(Integer status) {
        return medicineRepository.findByStatus(status);
    }
}
