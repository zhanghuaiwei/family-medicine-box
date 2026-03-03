<template>
  <div class="medicine-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>药品管理</span>
          <el-button type="primary" @click="handleAdd">添加药品</el-button>
        </div>
      </template>
      
      <div class="search-container">
        <el-input
          v-model="searchForm.name"
          placeholder="药品名称"
          style="width: 200px; margin-right: 10px"
        />
        <el-select
          v-model="searchForm.categoryId"
          placeholder="药品分类"
          style="width: 150px; margin-right: 10px"
        >
          <el-option
            v-for="category in categories"
            :key="category.id"
            :label="category.categoryName"
            :value="category.id"
          />
        </el-select>
        <el-button type="primary" @click="handleSearch">搜索</el-button>
      </div>

      <el-table :data="medicines" style="width: 100%" border>
        <el-table-column prop="name" label="药品名称" width="180" />
        <el-table-column prop="categoryName" label="分类" width="120" />
        <el-table-column prop="brand" label="品牌" width="120" />
        <el-table-column prop="specification" label="规格" width="150" />
        <el-table-column prop="dosageForm" label="剂型" width="100" />
        <el-table-column prop="price" label="价格" width="100" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="scope">
            <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
              {{ scope.row.status === 1 ? '正常' : '停用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template #default="scope">
            <el-button type="primary" size="small" @click="handleEdit(scope.row)">
              编辑
            </el-button>
            <el-button type="danger" size="small" @click="handleDelete(scope.row.id)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination-container">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        />
      </div>
    </el-card>

    <!-- 添加/编辑药品对话框 -->
    <el-dialog
      :title="dialogTitle"
      v-model="dialogVisible"
      width="500px"
    >
      <el-form :model="form" label-width="100px">
        <el-form-item label="药品名称">
          <el-input v-model="form.name" placeholder="请输入药品名称" />
        </el-form-item>
        <el-form-item label="药品分类">
          <el-select v-model="form.categoryId" placeholder="请选择药品分类">
            <el-option
              v-for="category in categories"
              :key="category.id"
              :label="category.categoryName"
              :value="category.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="品牌">
          <el-input v-model="form.brand" placeholder="请输入品牌" />
        </el-form-item>
        <el-form-item label="规格">
          <el-input v-model="form.specification" placeholder="请输入规格" />
        </el-form-item>
        <el-form-item label="剂型">
          <el-input v-model="form.dosageForm" placeholder="请输入剂型" />
        </el-form-item>
        <el-form-item label="价格">
          <el-input-number v-model="form.price" :min="0" :step="0.01" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="状态">
          <el-switch v-model="form.status" active-value="1" inactive-value="0" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleSave">保存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'

// 搜索表单
const searchForm = reactive({
  name: '',
  categoryId: ''
})

// 分类列表
const categories = ref([
  { id: '1', categoryName: '感冒药' },
  { id: '2', categoryName: '退烧药' },
  { id: '3', categoryName: '消炎药' },
  { id: '4', categoryName: '消化系统药' },
  { id: '5', categoryName: '心血管药' }
])

// 药品列表
const medicines = ref([
  {
    id: '1',
    name: '阿莫西林胶囊',
    categoryId: '3',
    categoryName: '消炎药',
    brand: '哈药集团',
    specification: '0.25g*24粒',
    dosageForm: '胶囊',
    price: 25.5,
    status: 1
  },
  {
    id: '2',
    name: '布洛芬片',
    categoryId: '2',
    categoryName: '退烧药',
    brand: '芬必得',
    specification: '0.2g*20片',
    dosageForm: '片剂',
    price: 18.8,
    status: 1
  },
  {
    id: '3',
    name: '感冒清热颗粒',
    categoryId: '1',
    categoryName: '感冒药',
    brand: '同仁堂',
    specification: '10g*10袋',
    dosageForm: '颗粒',
    price: 32.0,
    status: 1
  }
])

// 分页参数
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(medicines.value.length)

// 对话框参数
const dialogVisible = ref(false)
const dialogTitle = ref('添加药品')
const form = reactive({
  id: '',
  name: '',
  categoryId: '',
  brand: '',
  specification: '',
  dosageForm: '',
  price: 0,
  status: 1
})

// 搜索方法
const handleSearch = () => {
  // 实际项目中这里会调用后端API
  console.log('搜索', searchForm)
}

// 分页方法
const handleSizeChange = (size: number) => {
  pageSize.value = size
}

const handleCurrentChange = (current: number) => {
  currentPage.value = current
}

// 添加药品
const handleAdd = () => {
  dialogTitle.value = '添加药品'
  // 重置表单
  Object.assign(form, {
    id: '',
    name: '',
    categoryId: '',
    brand: '',
    specification: '',
    dosageForm: '',
    price: 0,
    status: 1
  })
  dialogVisible.value = true
}

// 编辑药品
const handleEdit = (row: any) => {
  dialogTitle.value = '编辑药品'
  // 复制数据到表单
  Object.assign(form, row)
  dialogVisible.value = true
}

// 删除药品
const handleDelete = (id: string) => {
  // 实际项目中这里会调用后端API
  console.log('删除药品', id)
  // 模拟删除
  const index = medicines.value.findIndex(item => item.id === id)
  if (index > -1) {
    medicines.value.splice(index, 1)
    total.value = medicines.value.length
  }
}

// 保存药品
const handleSave = () => {
  // 实际项目中这里会调用后端API
  console.log('保存药品', form)
  // 模拟保存
  if (form.id) {
    // 编辑
    const index = medicines.value.findIndex(item => item.id === form.id)
    if (index > -1) {
      medicines.value[index] = { ...form }
    }
  } else {
    // 添加
    const newMedicine = {
      ...form,
      id: Date.now().toString(),
      categoryName: categories.value.find(c => c.id === form.categoryId)?.categoryName || ''
    }
    medicines.value.unshift(newMedicine)
    total.value = medicines.value.length
  }
  dialogVisible.value = false
}
</script>

<style scoped lang="less">
.medicine-container {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 16px;
  font-weight: bold;
}

.search-container {
  margin-bottom: 20px;
  display: flex;
  align-items: center;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.dialog-footer {
  text-align: right;
}
</style>
