<template>
  <div class="alert-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>报警设置</span>
        </div>
      </template>

      <div class="alert-settings">
        <el-card class="setting-card">
          <template #header>
            <div class="setting-header">
              <span>低库存报警</span>
              <el-switch v-model="lowStockAlert.enabled" @change="handleLowStockChange"></el-switch>
            </div>
          </template>
          <div class="setting-content">
            <el-form :model="lowStockAlert" label-width="120px">
              <el-form-item label="低库存阈值">
                <el-input-number v-model="lowStockAlert.threshold" :min="1" :step="1" placeholder="请输入低库存阈值" />
              </el-form-item>
              <el-form-item label="通知方式">
                <el-checkbox-group v-model="lowStockAlert.notificationMethods">
                  <el-checkbox label="sms">短信</el-checkbox>
                  <el-checkbox label="wechat">微信</el-checkbox>
                  <el-checkbox label="email">邮件</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="报警级别">
                <el-select v-model="lowStockAlert.alertLevel" placeholder="请选择报警级别">
                  <el-option label="提醒" value="1" />
                  <el-option label="警告" value="2" />
                  <el-option label="严重" value="3" />
                </el-select>
              </el-form-item>
            </el-form>
          </div>
        </el-card>

        <el-card class="setting-card">
          <template #header>
            <div class="setting-header">
              <span>过期药品报警</span>
              <el-switch v-model="expiryAlert.enabled" @change="handleExpiryChange"></el-switch>
            </div>
          </template>
          <div class="setting-content">
            <el-form :model="expiryAlert" label-width="120px">
              <el-form-item label="提前天数">
                <el-input-number v-model="expiryAlert.threshold" :min="1" :step="1" placeholder="请输入提前天数" />
              </el-form-item>
              <el-form-item label="通知方式">
                <el-checkbox-group v-model="expiryAlert.notificationMethods">
                  <el-checkbox label="sms">短信</el-checkbox>
                  <el-checkbox label="wechat">微信</el-checkbox>
                  <el-checkbox label="email">邮件</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="报警级别">
                <el-select v-model="expiryAlert.alertLevel" placeholder="请选择报警级别">
                  <el-option label="提醒" value="1" />
                  <el-option label="警告" value="2" />
                  <el-option label="严重" value="3" />
                </el-select>
              </el-form-item>
            </el-form>
          </div>
        </el-card>

        <el-card class="setting-card">
          <template #header>
            <div class="setting-header">
              <span>用药提醒</span>
              <el-switch v-model="usageReminder.enabled" @change="handleUsageReminderChange"></el-switch>
            </div>
          </template>
          <div class="setting-content">
            <el-form :model="usageReminder" label-width="120px">
              <el-form-item label="提醒时间">
                <el-time-picker v-model="usageReminder.reminderTime" placeholder="选择提醒时间" style="width: 100%" />
              </el-form-item>
              <el-form-item label="通知方式">
                <el-checkbox-group v-model="usageReminder.notificationMethods">
                  <el-checkbox label="wechat">微信</el-checkbox>
                  <el-checkbox label="app">App通知</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </div>

      <div class="alert-history">
        <el-card>
          <template #header>
            <div class="history-header">
              <span>报警历史</span>
              <el-input
                v-model="searchKeyword"
                placeholder="搜索报警消息"
                style="width: 300px"
                prefix-icon="i-ep-search"
                @input="handleSearch"
              />
            </div>
          </template>
          <el-table :data="filteredAlerts" style="width: 100%" border>
            <el-table-column prop="type" label="报警类型" width="120" />
            <el-table-column prop="message" label="报警消息" />
            <el-table-column prop="level" label="报警级别" width="100">
              <template #default="scope">
                <el-tag :type="getLevelTagType(scope.row.level)">
                  {{ scope.row.levelText }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="time" label="报警时间" width="180" />
            <el-table-column prop="status" label="状态" width="100">
              <template #default="scope">
                <el-tag :type="scope.row.status === '已处理' ? 'success' : 'warning'">
                  {{ scope.row.status }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="120">
              <template #default="scope">
                <el-button type="primary" size="small" @click="handleResolve(scope.row.id)" :disabled="scope.row.status === '已处理'">
                  处理
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
      </div>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from 'vue'
import { iEpSearch } from '@element-plus/icons-vue'

// 低库存报警设置
const lowStockAlert = reactive({
  enabled: true,
  threshold: 5,
  notificationMethods: ['sms', 'wechat'],
  alertLevel: 2
})

// 过期药品报警设置
const expiryAlert = reactive({
  enabled: true,
  threshold: 30,
  notificationMethods: ['wechat', 'email'],
  alertLevel: 3
})

// 用药提醒设置
const usageReminder = reactive({
  enabled: true,
  reminderTime: new Date('2026-03-03T08:00:00'),
  notificationMethods: ['wechat']
})

// 报警历史
const alerts = ref([
  {
    id: '1',
    type: '低库存',
    message: '阿莫西林胶囊库存不足，当前库存5盒，最低库存10盒',
    level: 2,
    levelText: '警告',
    time: '2026-03-03 10:30:00',
    status: '未处理'
  },
  {
    id: '2',
    type: '即将过期',
    message: '布洛芬片将在30天后过期',
    level: 1,
    levelText: '提醒',
    time: '2026-03-03 09:15:00',
    status: '已处理'
  },
  {
    id: '3',
    type: '已过期',
    message: '感冒清热颗粒已过期',
    level: 3,
    levelText: '严重',
    time: '2026-03-02 16:45:00',
    status: '未处理'
  }
])

// 搜索和分页
const searchKeyword = ref('')
const currentPage = ref(1)
const pageSize = ref(10)
const total = ref(alerts.value.length)

// 过滤后的报警列表
const filteredAlerts = computed(() => {
  if (!searchKeyword.value) {
    return alerts.value
  }
  return alerts.value.filter(item => 
    item.message.toLowerCase().includes(searchKeyword.value.toLowerCase())
  )
})

// 处理低库存报警开关变化
const handleLowStockChange = () => {
  console.log('低库存报警开关变化', lowStockAlert.enabled)
}

// 处理过期药品报警开关变化
const handleExpiryChange = () => {
  console.log('过期药品报警开关变化', expiryAlert.enabled)
}

// 处理用药提醒开关变化
const handleUsageReminderChange = () => {
  console.log('用药提醒开关变化', usageReminder.enabled)
}

// 处理搜索
const handleSearch = () => {
  currentPage.value = 1
}

// 处理分页
const handleSizeChange = (size: number) => {
  pageSize.value = size
}

const handleCurrentChange = (current: number) => {
  currentPage.value = current
}

// 获取报警级别标签类型
const getLevelTagType = (level: number) => {
  switch (level) {
    case 1:
      return 'info'
    case 2:
      return 'warning'
    case 3:
      return 'danger'
    default:
      return 'info'
  }
}

// 处理报警
const handleResolve = (id: string) => {
  // 实际项目中这里会调用后端API
  console.log('处理报警', id)
  // 模拟处理
  const index = alerts.value.findIndex(item => item.id === id)
  if (index > -1) {
    alerts.value[index].status = '已处理'
  }
}
</script>

<style scoped lang="less">
.alert-container {
  padding: 20px;
}

.card-header {
  font-size: 16px;
  font-weight: bold;
}

.alert-settings {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(400px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.setting-card {
  border-left: 4px solid #409eff;
}

.setting-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 14px;
  font-weight: bold;
}

.setting-content {
  margin-top: 20px;
}

.alert-history {
  margin-top: 30px;
}

.history-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 16px;
  font-weight: bold;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}
</style>
