<template>
  <view class="medicine-container">
    <view class="header">
      <text class="title">药品管理</text>
      <uni-icon type="plus" size="36rpx" @click="handleAdd"></uni-icon>
    </view>

    <view class="search-container">
      <uni-search-bar v-model="searchKeyword" placeholder="搜索药品名称" @confirm="handleSearch"></uni-search-bar>
    </view>

    <view class="category-filter">
      <scroll-view scroll-x="true" class="category-scroll">
        <view 
          class="category-item" 
          v-for="(category, index) in categories" 
          :key="index"
          :class="currentCategory === category.id ? 'active' : ''"
          @click="handleCategoryChange(category.id)"
        >
          {{ category.name }}
        </view>
      </scroll-view>
    </view>

    <view class="medicine-list">
      <view class="medicine-item" v-for="(medicine, index) in medicines" :key="index">
        <view class="medicine-info">
          <text class="medicine-name">{{ medicine.name }}</text>
          <text class="medicine-brand">{{ medicine.brand }}</text>
          <text class="medicine-spec">{{ medicine.specification }}</text>
        </view>
        <view class="medicine-stock">
          <text :class="medicine.stock <= medicine.minStock ? 'low-stock' : 'normal-stock'">
            库存: {{ medicine.stock }}{{ medicine.unit }}
          </text>
        </view>
      </view>
    </view>

    <!-- 添加药品对话框 -->
    <uni-popup ref="addPopup" type="bottom" :mask-show="true">
      <view class="popup-content">
        <view class="popup-header">
          <text class="popup-title">添加药品</text>
          <uni-icon type="close" size="32rpx" @click="$refs.addPopup.close()"></uni-icon>
        </view>
        <view class="popup-body">
          <uni-forms :model="form" ref="forms">
            <uni-forms-item label="药品名称">
              <uni-easyinput v-model="form.name" placeholder="请输入药品名称" />
            </uni-forms-item>
            <uni-forms-item label="品牌">
              <uni-easyinput v-model="form.brand" placeholder="请输入品牌" />
            </uni-forms-item>
            <uni-forms-item label="规格">
              <uni-easyinput v-model="form.specification" placeholder="请输入规格" />
            </uni-forms-item>
            <uni-forms-item label="库存">
              <uni-easyinput type="number" v-model="form.stock" placeholder="请输入库存" />
            </uni-forms-item>
            <uni-forms-item label="单位">
              <uni-easyinput v-model="form.unit" placeholder="请输入单位" />
            </uni-forms-item>
            <uni-forms-item label="最低库存">
              <uni-easyinput type="number" v-model="form.minStock" placeholder="请输入最低库存" />
            </uni-forms-item>
          </uni-forms>
        </view>
        <view class="popup-footer">
          <button class="cancel-btn" @click="$refs.addPopup.close()">取消</button>
          <button class="confirm-btn" @click="handleSave">保存</button>
        </view>
      </view>
    </uni-popup>
  </view>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'

const searchKeyword = ref('')
const currentCategory = ref('all')
const addPopup = ref(null)

const categories = ref([
  { id: 'all', name: '全部' },
  { id: 'cold', name: '感冒药' },
  { id: 'fever', name: '退烧药' },
  { id: 'anti-inflammatory', name: '消炎药' },
  { id: 'digestive', name: '消化系统药' },
  { id: 'cardiovascular', name: '心血管药' }
])

const medicines = ref([
  {
    id: '1',
    name: '阿莫西林胶囊',
    brand: '哈药集团',
    specification: '0.25g*24粒',
    stock: 5,
    minStock: 10,
    unit: '盒'
  },
  {
    id: '2',
    name: '布洛芬片',
    brand: '芬必得',
    specification: '0.2g*20片',
    stock: 15,
    minStock: 5,
    unit: '盒'
  },
  {
    id: '3',
    name: '感冒清热颗粒',
    brand: '同仁堂',
    specification: '10g*10袋',
    stock: 8,
    minStock: 5,
    unit: '盒'
  }
])

const form = reactive({
  name: '',
  brand: '',
  specification: '',
  stock: '',
  unit: '',
  minStock: ''
})

const handleSearch = () => {
  // 实际项目中这里会调用后端API
  console.log('搜索', searchKeyword.value)
}

const handleCategoryChange = (categoryId: string) => {
  currentCategory.value = categoryId
  // 实际项目中这里会根据分类筛选药品
  console.log('分类变更', categoryId)
}

const handleAdd = () => {
  // 重置表单
  Object.assign(form, {
    name: '',
    brand: '',
    specification: '',
    stock: '',
    unit: '',
    minStock: ''
  })
  addPopup.value.open()
}

const handleSave = () => {
  // 实际项目中这里会调用后端API
  console.log('保存药品', form)
  // 模拟保存
  const newMedicine = {
    id: Date.now().toString(),
    name: form.name,
    brand: form.brand,
    specification: form.specification,
    stock: parseInt(form.stock),
    minStock: parseInt(form.minStock),
    unit: form.unit
  }
  medicines.value.unshift(newMedicine)
  addPopup.value.close()
}
</script>

<style scoped>
.medicine-container {
  padding: 20rpx;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20rpx;
}

.title {
  font-size: 36rpx;
  font-weight: bold;
  color: #333;
}

.search-container {
  margin-bottom: 20rpx;
}

.category-filter {
  margin-bottom: 20rpx;
}

.category-scroll {
  white-space: nowrap;
}

.category-item {
  display: inline-block;
  padding: 10rpx 20rpx;
  margin-right: 10rpx;
  background-color: #f5f7fa;
  border-radius: 20rpx;
  font-size: 24rpx;
  color: #606266;
}

.category-item.active {
  background-color: #409eff;
  color: #fff;
}

.medicine-list {
  display: flex;
  flex-direction: column;
  gap: 15rpx;
}

.medicine-item {
  background-color: #f5f7fa;
  border-radius: 10rpx;
  padding: 20rpx;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.medicine-info {
  flex: 1;
}

.medicine-name {
  display: block;
  font-size: 28rpx;
  font-weight: bold;
  color: #333;
  margin-bottom: 5rpx;
}

.medicine-brand {
  display: block;
  font-size: 24rpx;
  color: #606266;
  margin-bottom: 5rpx;
}

.medicine-spec {
  font-size: 22rpx;
  color: #909399;
}

.medicine-stock {
  text-align: right;
}

.low-stock {
  color: #f56c6c;
  font-size: 24rpx;
  font-weight: bold;
}

.normal-stock {
  color: #67c23a;
  font-size: 24rpx;
}

.popup-content {
  background-color: #fff;
  border-radius: 20rpx 20rpx 0 0;
  padding: 20rpx;
}

.popup-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20rpx;
}

.popup-title {
  font-size: 32rpx;
  font-weight: bold;
  color: #333;
}

.popup-body {
  margin-bottom: 30rpx;
}

.popup-footer {
  display: flex;
  justify-content: space-between;
  gap: 20rpx;
}

.cancel-btn {
  flex: 1;
  background-color: #f5f7fa;
  color: #606266;
  border: none;
  border-radius: 10rpx;
  padding: 20rpx;
  font-size: 28rpx;
}

.confirm-btn {
  flex: 1;
  background-color: #409eff;
  color: #fff;
  border: none;
  border-radius: 10rpx;
  padding: 20rpx;
  font-size: 28rpx;
}
</style>
