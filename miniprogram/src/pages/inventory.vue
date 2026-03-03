<template>
  <view class="inventory-container">
    <view class="header">
      <text class="title">库存管理</text>
    </view>

    <view class="tabs">
      <view 
        class="tab-item" 
        :class="currentTab === 'all' ? 'active' : ''"
        @click="currentTab = 'all'"
      >
        全部
      </view>
      <view 
        class="tab-item" 
        :class="currentTab === 'low' ? 'active' : ''"
        @click="currentTab = 'low'"
      >
        低库存
      </view>
      <view 
        class="tab-item" 
        :class="currentTab === 'expiring' ? 'active' : ''"
        @click="currentTab = 'expiring'"
      >
        即将过期
      </view>
      <view 
        class="tab-item" 
        :class="currentTab === 'expired' ? 'active' : ''"
        @click="currentTab = 'expired'"
      >
        已过期
      </view>
    </view>

    <view class="inventory-list">
      <view class="inventory-item" v-for="(item, index) in filteredInventory" :key="index">
        <view class="item-header">
          <text class="medicine-name">{{ item.name }}</text>
          <text class="expiry-date" :class="getExpiryClass(item.expiryDate)">
            {{ item.expiryDate }}
          </text>
        </view>
        <view class="item-body">
          <view class="stock-info">
            <text class="stock-label">库存:</text>
            <text class="stock-value" :class="item.stock <= item.minStock ? 'low-stock' : 'normal-stock'">
              {{ item.stock }}{{ item.unit }}
            </text>
          </view>
          <view class="min-stock-info">
            <text class="min-stock-label">最低库存:</text>
            <text class="min-stock-value">{{ item.minStock }}{{ item.unit }}</text>
          </view>
          <view class="location-info">
            <text class="location-label">存放位置:</text>
            <text class="location-value">{{ item.location }}</text>
          </view>
        </view>
        <view class="item-footer">
          <button class="edit-btn" @click="handleEdit(item)">编辑</button>
          <button class="usage-btn" @click="handleUsage(item)">使用</button>
        </view>
      </view>
    </view>

    <!-- 编辑库存对话框 -->
    <uni-popup ref="editPopup" type="bottom" :mask-show="true">
      <view class="popup-content">
        <view class="popup-header">
          <text class="popup-title">编辑库存</text>
          <uni-icon type="close" size="32rpx" @click="$refs.editPopup.close()"></uni-icon>
        </view>
        <view class="popup-body">
          <uni-forms :model="editForm" ref="editForms">
            <uni-forms-item label="库存数量">
              <uni-easyinput type="number" v-model="editForm.stock" placeholder="请输入库存数量" />
            </uni-forms-item>
            <uni-forms-item label="最低库存">
              <uni-easyinput type="number" v-model="editForm.minStock" placeholder="请输入最低库存" />
            </uni-forms-item>
            <uni-forms-item label="存放位置">
              <uni-easyinput v-model="editForm.location" placeholder="请输入存放位置" />
            </uni-forms-item>
          </uni-forms>
        </view>
        <view class="popup-footer">
          <button class="cancel-btn" @click="$refs.editPopup.close()">取消</button>
          <button class="confirm-btn" @click="handleEditSave">保存</button>
        </view>
      </view>
    </uni-popup>

    <!-- 用药记录对话框 -->
    <uni-popup ref="usagePopup" type="bottom" :mask-show="true">
      <view class="popup-content">
        <view class="popup-header">
          <text class="popup-title">用药记录</text>
          <uni-icon type="close" size="32rpx" @click="$refs.usagePopup.close()"></uni-icon>
        </view>
        <view class="popup-body">
          <uni-forms :model="usageForm" ref="usageForms">
            <uni-forms-item label="使用数量">
              <uni-easyinput type="number" v-model="usageForm.quantity" placeholder="请输入使用数量" />
            </uni-forms-item>
            <uni-forms-item label="使用时间">
              <uni-date-picker v-model="usageForm.time" type="datetime" placeholder="请选择使用时间" />
            </uni-forms-item>
            <uni-forms-item label="备注">
              <uni-easyinput type="textarea" v-model="usageForm.notes" placeholder="请输入备注" />
            </uni-forms-item>
          </uni-forms>
        </view>
        <view class="popup-footer">
          <button class="cancel-btn" @click="$refs.usagePopup.close()">取消</button>
          <button class="confirm-btn" @click="handleUsageSave">保存</button>
        </view>
      </view>
    </uni-popup>
  </view>
</template>

<script setup lang="ts">
import { ref, computed, reactive } from 'vue'

const currentTab = ref('all')
const editPopup = ref(null)
const usagePopup = ref(null)

const inventory = ref([
  {
    id: '1',
    name: '阿莫西林胶囊',
    stock: 5,
    minStock: 10,
    unit: '盒',
    expiryDate: '2026-12-31',
    location: '药箱上层'
  },
  {
    id: '2',
    name: '布洛芬片',
    stock: 15,
    minStock: 5,
    unit: '盒',
    expiryDate: '2026-06-30',
    location: '药箱中层'
  },
  {
    id: '3',
    name: '感冒清热颗粒',
    stock: 8,
    minStock: 5,
    unit: '盒',
    expiryDate: '2026-03-15',
    location: '药箱下层'
  },
  {
    id: '4',
    name: '盐酸伐地那非片',
    stock: 2,
    minStock: 5,
    unit: '盒',
    expiryDate: '2025-12-31',
    location: '药箱中层'
  }
])

const editForm = reactive({
  id: '',
  stock: '',
  minStock: '',
  location: ''
})

const usageForm = reactive({
  id: '',
  quantity: '',
  time: '',
  notes: ''
})

const filteredInventory = computed(() => {
  switch (currentTab.value) {
    case 'low':
      return inventory.value.filter(item => item.stock <= item.minStock)
    case 'expiring':
      const now = new Date()
      const thirtyDaysLater = new Date(now.getTime() + 30 * 24 * 60 * 60 * 1000)
      return inventory.value.filter(item => {
        const expiryDate = new Date(item.expiryDate)
        return expiryDate > now && expiryDate <= thirtyDaysLater
      })
    case 'expired':
      const currentDate = new Date()
      return inventory.value.filter(item => new Date(item.expiryDate) < currentDate)
    default:
      return inventory.value
  }
})

const getExpiryClass = (expiryDate: string) => {
  const now = new Date()
  const expiry = new Date(expiryDate)
  const thirtyDaysLater = new Date(now.getTime() + 30 * 24 * 60 * 60 * 1000)
  
  if (expiry < now) {
    return 'expired'
  } else if (expiry <= thirtyDaysLater) {
    return 'expiring'
  } else {
    return 'normal'
  }
}

const handleEdit = (item: any) => {
  editForm.id = item.id
  editForm.stock = item.stock.toString()
  editForm.minStock = item.minStock.toString()
  editForm.location = item.location
  editPopup.value.open()
}

const handleEditSave = () => {
  const item = inventory.value.find(i => i.id === editForm.id)
  if (item) {
    item.stock = parseInt(editForm.stock)
    item.minStock = parseInt(editForm.minStock)
    item.location = editForm.location
  }
  editPopup.value.close()
}

const handleUsage = (item: any) => {
  usageForm.id = item.id
  usageForm.quantity = ''
  usageForm.time = new Date().toISOString()
  usageForm.notes = ''
  usagePopup.value.open()
}

const handleUsageSave = () => {
  const item = inventory.value.find(i => i.id === usageForm.id)
  if (item) {
    item.stock -= parseInt(usageForm.quantity)
  }
  usagePopup.value.close()
}
</script>

<style scoped>
.inventory-container {
  padding: 20rpx;
}

.header {
  margin-bottom: 20rpx;
}

.title {
  font-size: 36rpx;
  font-weight: bold;
  color: #333;
}

.tabs {
  display: flex;
  margin-bottom: 20rpx;
  border-bottom: 1rpx solid #e4e7ed;
}

.tab-item {
  flex: 1;
  text-align: center;
  padding: 15rpx 0;
  font-size: 26rpx;
  color: #606266;
  border-bottom: 2rpx solid transparent;
}

.tab-item.active {
  color: #409eff;
  border-bottom-color: #409eff;
}

.inventory-list {
  display: flex;
  flex-direction: column;
  gap: 15rpx;
}

.inventory-item {
  background-color: #f5f7fa;
  border-radius: 10rpx;
  padding: 20rpx;
}

.item-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15rpx;
}

.medicine-name {
  font-size: 28rpx;
  font-weight: bold;
  color: #333;
}

.expiry-date {
  font-size: 24rpx;
  padding: 5rpx 10rpx;
  border-radius: 10rpx;
}

.expiry-date.normal {
  background-color: #ecf5ff;
  color: #409eff;
}

.expiry-date.expiring {
  background-color: #fdf6ec;
  color: #e6a23c;
}

.expiry-date.expired {
  background-color: #fef0f0;
  color: #f56c6c;
}

.item-body {
  margin-bottom: 15rpx;
}

.stock-info,
.min-stock-info,
.location-info {
  display: flex;
  margin-bottom: 10rpx;
}

.stock-label,
.min-stock-label,
.location-label {
  width: 120rpx;
  font-size: 24rpx;
  color: #606266;
}

.stock-value,
.min-stock-value,
.location-value {
  flex: 1;
  font-size: 24rpx;
  color: #333;
}

.stock-value.low-stock {
  color: #f56c6c;
  font-weight: bold;
}

.stock-value.normal-stock {
  color: #67c23a;
}

.item-footer {
  display: flex;
  gap: 10rpx;
}

.edit-btn,
.usage-btn {
  flex: 1;
  padding: 15rpx;
  border: none;
  border-radius: 10rpx;
  font-size: 24rpx;
}

.edit-btn {
  background-color: #ecf5ff;
  color: #409eff;
}

.usage-btn {
  background-color: #f0f9eb;
  color: #67c23a;
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
