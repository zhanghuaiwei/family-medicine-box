package com.medicinebox.medicine.controller;

import com.medicinebox.common.model.Medicine;
import com.medicinebox.common.model.MedicineCategory;
import com.medicinebox.common.response.Response;
import com.medicinebox.medicine.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 药品Controller
 */
@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    // 药品分类相关接口
    /**
     * 创建药品分类
     * @param category 分类信息
     * @return 响应结果
     */
    @PostMapping("/categories")
    public Response<MedicineCategory> createCategory(@RequestBody MedicineCategory category) {
        MedicineCategory createdCategory = medicineService.createCategory(category);
        return Response.success(createdCategory);
    }

    /**
     * 根据ID查询分类
     * @param id 分类ID
     * @return 响应结果
     */
    @GetMapping("/categories/{id}")
    public Response<MedicineCategory> getCategoryById(@PathVariable String id) {
        MedicineCategory category = medicineService.getCategoryById(id)
                .orElseThrow(() -> new RuntimeException("分类不存在"));
        return Response.success(category);
    }

    /**
     * 更新分类信息
     * @param category 分类信息
     * @return 响应结果
     */
    @PutMapping("/categories")
    public Response<MedicineCategory> updateCategory(@RequestBody MedicineCategory category) {
        MedicineCategory updatedCategory = medicineService.updateCategory(category);
        return Response.success(updatedCategory);
    }

    /**
     * 删除分类（逻辑删除）
     * @param id 分类ID
     * @return 响应结果
     */
    @DeleteMapping("/categories/{id}")
    public Response<Void> deleteCategory(@PathVariable String id) {
        medicineService.deleteCategory(id);
        return Response.success();
    }

    /**
     * 获取所有分类
     * @param deleted 删除状态
     * @return 响应结果
     */
    @GetMapping("/categories")
    public Response<List<MedicineCategory>> getAllCategories(@RequestParam Integer deleted) {
        List<MedicineCategory> categories = medicineService.getAllCategories(deleted);
        return Response.success(categories);
    }

    /**
     * 根据父分类ID查询子分类
     * @param parentId 父分类ID
     * @return 响应结果
     */
    @GetMapping("/categories/sub/{parentId}")
    public Response<List<MedicineCategory>> getSubCategories(@PathVariable String parentId) {
        List<MedicineCategory> subCategories = medicineService.getSubCategories(parentId);
        return Response.success(subCategories);
    }

    // 药品相关接口
    /**
     * 创建药品
     * @param medicine 药品信息
     * @return 响应结果
     */
    @PostMapping
    public Response<Medicine> createMedicine(@RequestBody Medicine medicine) {
        Medicine createdMedicine = medicineService.createMedicine(medicine);
        return Response.success(createdMedicine);
    }

    /**
     * 根据ID查询药品
     * @param id 药品ID
     * @return 响应结果
     */
    @GetMapping("/{id}")
    public Response<Medicine> getMedicineById(@PathVariable String id) {
        Medicine medicine = medicineService.getMedicineById(id)
                .orElseThrow(() -> new RuntimeException("药品不存在"));
        return Response.success(medicine);
    }

    /**
     * 根据条形码查询药品
     * @param barcode 条形码
     * @return 响应结果
     */
    @GetMapping("/barcode/{barcode}")
    public Response<Medicine> getMedicineByBarcode(@PathVariable String barcode) {
        Medicine medicine = medicineService.getMedicineByBarcode(barcode)
                .orElseThrow(() -> new RuntimeException("药品不存在"));
        return Response.success(medicine);
    }

    /**
     * 更新药品信息
     * @param medicine 药品信息
     * @return 响应结果
     */
    @PutMapping
    public Response<Medicine> updateMedicine(@RequestBody Medicine medicine) {
        Medicine updatedMedicine = medicineService.updateMedicine(medicine);
        return Response.success(updatedMedicine);
    }

    /**
     * 删除药品（逻辑删除）
     * @param id 药品ID
     * @return 响应结果
     */
    @DeleteMapping("/{id}")
    public Response<Void> deleteMedicine(@PathVariable String id) {
        medicineService.deleteMedicine(id);
        return Response.success();
    }

    /**
     * 获取所有药品
     * @param deleted 删除状态
     * @return 响应结果
     */
    @GetMapping
    public Response<List<Medicine>> getAllMedicines(@RequestParam Integer deleted) {
        List<Medicine> medicines = medicineService.getAllMedicines(deleted);
        return Response.success(medicines);
    }

    /**
     * 根据分类ID查询药品
     * @param categoryId 分类ID
     * @return 响应结果
     */
    @GetMapping("/category/{categoryId}")
    public Response<List<Medicine>> getMedicinesByCategoryId(@PathVariable String categoryId) {
        List<Medicine> medicines = medicineService.getMedicinesByCategoryId(categoryId);
        return Response.success(medicines);
    }

    /**
     * 根据状态查询药品
     * @param status 状态
     * @return 响应结果
     */
    @GetMapping("/status/{status}")
    public Response<List<Medicine>> getMedicinesByStatus(@PathVariable Integer status) {
        List<Medicine> medicines = medicineService.getMedicinesByStatus(status);
        return Response.success(medicines);
    }
}
