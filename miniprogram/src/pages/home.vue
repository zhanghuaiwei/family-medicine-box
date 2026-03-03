<template>
  <view class="home-container">
    <view class="header">
      <text class="title">家庭药箱</text>
      <view class="user-info">
        <text class="username">张三</text>
        <uni-avatar size="50rpx" src="/static/avatar.png"></uni-avatar>
      </view>
    </view>

    <view class="stats-container">
      <view class="stat-item">
        <text class="stat-value">128</text>
        <text class="stat-label">药品总数</text>
      </view>
      <view class="stat-item">
        <text class="stat-value">5</text>
        <text class="stat-label">低库存</text>
      </view>
      <view class="stat-item">
        <text class="stat-value">3</text>
        <text class="stat-label">即将过期</text>
      </view>
      <view class="stat-item">
        <text class="stat-value">1</text>
        <text class="stat-label">已过期</text>
      </view>
    </view>

    <view class="alerts-container">
      <view class="section-header">
        <text class="section-title">最近报警</text>
        <text class="more">查看更多</text>
      </view>
      <view class="alert-list">
        <view class="alert-item" v-for="(alert, index) in alerts" :key="index">
          <view class="alert-type" :class="alert.type === '低库存' ? 'low-stock' : alert.type === '即将过期' ? 'expiring' : 'expired'">
            {{ alert.type }}
          </view>
          <view class="alert-content">
            <text class="alert-message">{{ alert.message }}</text>
            <text class="alert-time">{{ alert.time }}</text>
          </view>
        </view>
      </view>
    </view>

    <view class="quick-actions">
      <view class="action-item">
        <uni-icon type="plus" size="36rpx"></uni-icon>
        <text>添加药品</text>
      </view>
      <view class="action-item">
        <uni-icon type="scan" size="36rpx"></uni-icon>
        <text>扫码录入</text>
      </view>
      <view class="action-item">
        <uni-icon type="calendar" size="36rpx"></uni-icon>
        <text>用药提醒</text>
      </view>
      <view class="action-item">
        <uni-icon type="settings" size="36rpx"></uni-icon>
        <text>设置</text>
      </view>
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const alerts = ref([
  { type: '低库存', message: '阿莫西林胶囊库存不足', time: '10:30' },
  { type: '即将过期', message: '布洛芬片将在30天后过期', time: '09:15' },
  { type: '已过期', message: '感冒清热颗粒已过期', time: '昨天' }
])
</script>

<style scoped>
.home-container {
  padding: 20rpx;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30rpx;
}

.title {
  font-size: 36rpx;
  font-weight: bold;
  color: #333;
}

.user-info {
  display: flex;
  align-items: center;
}

.username {
  margin-right: 10rpx;
  font-size: 28rpx;
  color: #666;
}

.stats-container {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20rpx;
  margin-bottom: 30rpx;
}

.stat-item {
  background-color: #f5f7fa;
  border-radius: 10rpx;
  padding: 20rpx;
  text-align: center;
}

.stat-value {
  display: block;
  font-size: 32rpx;
  font-weight: bold;
  color: #409eff;
  margin-bottom: 5rpx;
}

.stat-label {
  font-size: 24rpx;
  color: #606266;
}

.alerts-container {
  margin-bottom: 30rpx;
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15rpx;
}

.section-title {
  font-size: 32rpx;
  font-weight: bold;
  color: #333;
}

.more {
  font-size: 24rpx;
  color: #409eff;
}

.alert-list {
  background-color: #f5f7fa;
  border-radius: 10rpx;
  padding: 20rpx;
}

.alert-item {
  display: flex;
  align-items: center;
  margin-bottom: 15rpx;
  padding-bottom: 15rpx;
  border-bottom: 1rpx solid #e4e7ed;
}

.alert-item:last-child {
  margin-bottom: 0;
  padding-bottom: 0;
  border-bottom: none;
}

.alert-type {
  padding: 5rpx 15rpx;
  border-radius: 15rpx;
  font-size: 20rpx;
  color: #fff;
  margin-right: 15rpx;
}

.low-stock {
  background-color: #f56c6c;
}

.expiring {
  background-color: #e6a23c;
}

.expired {
  background-color: #909399;
}

.alert-content {
  flex: 1;
}

.alert-message {
  display: block;
  font-size: 26rpx;
  color: #333;
  margin-bottom: 5rpx;
}

.alert-time {
  font-size: 22rpx;
  color: #909399;
}

.quick-actions {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20rpx;
}

.action-item {
  background-color: #f5f7fa;
  border-radius: 10rpx;
  padding: 30rpx 20rpx;
  text-align: center;
}

.action-item uni-icon {
  display: block;
  margin: 0 auto 10rpx;
  color: #409eff;
}

.action-item text {
  font-size: 24rpx;
  color: #606266;
}
</style>
