<template>
  <div class="home-container">
    <el-card class="welcome-card">
      <template #header>
        <div class="card-header">
          <span>欢迎使用家庭药箱管理系统</span>
        </div>
      </template>
      <div class="welcome-content">
        <p>本系统帮助您管理家庭药品，监控库存状态，及时提醒药品过期和库存不足的情况。</p>
        <p>主要功能包括：</p>
        <ul>
          <li>药品信息管理</li>
          <li>库存状态监控</li>
          <li>过期药品提醒</li>
          <li>低库存预警</li>
          <li>用药记录管理</li>
        </ul>
      </div>
    </el-card>

    <div class="stats-container">
      <el-card class="stat-card">
        <div class="stat-item">
          <div class="stat-value">128</div>
          <div class="stat-label">药品总数</div>
        </div>
      </el-card>
      <el-card class="stat-card">
        <div class="stat-item">
          <div class="stat-value">5</div>
          <div class="stat-label">低库存药品</div>
        </div>
      </el-card>
      <el-card class="stat-card">
        <div class="stat-item">
          <div class="stat-value">3</div>
          <div class="stat-label">即将过期药品</div>
        </div>
      </el-card>
      <el-card class="stat-card">
        <div class="stat-item">
          <div class="stat-value">1</div>
          <div class="stat-label">已过期药品</div>
        </div>
      </el-card>
    </div>

    <el-card class="recent-alerts">
      <template #header>
        <div class="card-header">
          <span>最近报警</span>
        </div>
      </template>
      <el-table :data="alerts" style="width: 100%">
        <el-table-column prop="type" label="报警类型" width="180" />
        <el-table-column prop="message" label="报警消息" />
        <el-table-column prop="time" label="时间" width="180" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="scope">
            <el-tag :type="scope.row.status === '未处理' ? 'danger' : 'success'">
              {{ scope.row.status }}
            </el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const alerts = ref([
  { type: '低库存', message: '阿莫西林胶囊库存不足', time: '2026-03-03 10:30', status: '未处理' },
  { type: '即将过期', message: '布洛芬片将在30天后过期', time: '2026-03-03 09:15', status: '已处理' },
  { type: '已过期', message: '感冒清热颗粒已过期', time: '2026-03-02 16:45', status: '未处理' }
])
</script>

<style scoped lang="less">
.home-container {
  padding: 20px;
}

.welcome-card {
  margin-bottom: 20px;
}

.card-header {
  font-size: 16px;
  font-weight: bold;
}

.welcome-content {
  p {
    margin-bottom: 10px;
  }
  ul {
    margin: 10px 0;
    padding-left: 20px;
    li {
      margin-bottom: 5px;
    }
  }
}

.stats-container {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
  margin-bottom: 20px;
}

.stat-card {
  text-align: center;
  .stat-item {
    .stat-value {
      font-size: 32px;
      font-weight: bold;
      color: #409eff;
      margin-bottom: 5px;
    }
    .stat-label {
      font-size: 14px;
      color: #606266;
    }
  }
}

.recent-alerts {
  margin-top: 20px;
}
</style>
