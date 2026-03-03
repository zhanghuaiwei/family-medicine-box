package com.medicinebox.medicine.controller;

import com.medicinebox.common.model.Medicine;
import com.medicinebox.common.model.MedicineCategory;
import com.medicinebox.medicine.service.MedicineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MedicineControllerTest {

    @Mock
    private MedicineService medicineService;

    @InjectMocks
    private MedicineController medicineController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(medicineController).build();
    }

    @Test
    void testCreateCategory() throws Exception {
        MedicineCategory category = new MedicineCategory();
        category.setId("123");
        category.setCategoryName("感冒药");

        when(medicineService.createCategory(any(MedicineCategory.class))).thenReturn(category);

        mockMvc.perform(post("/api/medicines/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"categoryName\":\"感冒药\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value("123"))
                .andExpect(jsonPath("$.data.categoryName").value("感冒药"));
    }

    @Test
    void testCreateMedicine() throws Exception {
        Medicine medicine = new Medicine();
        medicine.setId("123");
        medicine.setName("感冒灵颗粒");
        medicine.setCategoryId("456");
        medicine.setSpecification("10袋/盒");

        when(medicineService.createMedicine(any(Medicine.class))).thenReturn(medicine);

        mockMvc.perform(post("/api/medicines")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"感冒灵颗粒\",\"categoryId\":\"456\",\"specification\":\"10袋/盒\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value("123"))
                .andExpect(jsonPath("$.data.name").value("感冒灵颗粒"));
    }

    @Test
    void testGetMedicineById() throws Exception {
        Medicine medicine = new Medicine();
        medicine.setId("123");
        medicine.setName("感冒灵颗粒");

        when(medicineService.getMedicineById("123")).thenReturn(Optional.of(medicine));

        mockMvc.perform(get("/api/medicines/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value("123"))
                .andExpect(jsonPath("$.data.name").value("感冒灵颗粒"));
    }

    @Test
    void testDeleteMedicine() throws Exception {
        doNothing().when(medicineService).deleteMedicine("123");

        mockMvc.perform(delete("/api/medicines/123"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllMedicines() throws Exception {
        List<Medicine> medicines = new ArrayList<>();
        Medicine medicine1 = new Medicine();
        medicine1.setId("123");
        medicine1.setName("感冒灵颗粒");
        medicines.add(medicine1);

        Medicine medicine2 = new Medicine();
        medicine2.setId("456");
        medicine2.setName("布洛芬");
        medicines.add(medicine2);

        when(medicineService.getAllMedicines(0)).thenReturn(medicines);

        mockMvc.perform(get("/api/medicines?deleted=0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].name").value("感冒灵颗粒"))
                .andExpect(jsonPath("$.data[1].name").value("布洛芬"));
    }

    @Test
    void testGetMedicinesByCategoryId() throws Exception {
        List<Medicine> medicines = new ArrayList<>();
        Medicine medicine = new Medicine();
        medicine.setId("123");
        medicine.setName("感冒灵颗粒");
        medicines.add(medicine);

        when(medicineService.getMedicinesByCategoryId("456")).thenReturn(medicines);

        mockMvc.perform(get("/api/medicines/category/456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(1))
                .andExpect(jsonPath("$.data[0].name").value("感冒灵颗粒"));
    }
}
