<template>
  <view class="mine-container">
    <view class="user-info">
      <uni-avatar size="120rpx" src="/static/avatar.png"></uni-avatar>
      <view class="user-details">
        <text class="username">张三</text>
        <text class="user-phone">138****8888</text>
      </view>
    </view>

    <view class="menu-section">
      <view class="menu-item" @click="handleMenuItemClick('records')">
        <uni-icon type="document" size="36rpx"></uni-icon>
        <text class="menu-text">我的用药记录</text>
        <uni-icon type="right" size="28rpx"></uni-icon>
      </view>
      <view class="menu-item" @click="handleMenuItemClick('alerts')">
        <uni-icon type="bell" size="36rpx"></uni-icon>
        <text class="menu-text">报警设置</text>
        <uni-icon type="right" size="28rpx"></uni-icon>
      </view>
      <view class="menu-item" @click="handleMenuItemClick('settings')">
        <uni-icon type="settings" size="36rpx"></uni-icon>
        <text class="menu-text">系统设置</text>
        <uni-icon type="right" size="28rpx"></uni-icon>
      </view>
      <view class="menu-item" @click="handleMenuItemClick('about')">
        <uni-icon type="info" size="36rpx"></uni-icon>
        <text class="menu-text">关于我们</text>
        <uni-icon type="right" size="28rpx"></uni-icon>
      </view>
    </view>

    <view class="logout-section">
      <button class="logout-btn" @click="handleLogout">退出登录</button>
    </view>

    <view class="version-info">
      <text class="version">版本 1.0.0</text>
    </view>
  </view>
</template>

<script setup lang="ts">
const handleMenuItemClick = (menu: string) => {
  // 实际项目中这里会跳转到相应的页面
  console.log('点击菜单', menu)
}

const handleLogout = () => {
  // 实际项目中这里会执行退出登录操作
  console.log('退出登录')
}
</script>

<style scoped>
.mine-container {
  padding: 40rpx 20rpx;
}

.user-info {
  display: flex;
  align-items: center;
  margin-bottom: 40rpx;
  padding: 30rpx;
  background-color: #f5f7fa;
  border-radius: 20rpx;
}

.user-details {
  margin-left: 20rpx;
}

.username {
  display: block;
  font-size: 32rpx;
  font-weight: bold;
  color: #333;
  margin-bottom: 10rpx;
}

.user-phone {
  font-size: 24rpx;
  color: #606266;
}

.menu-section {
  background-color: #f5f7fa;
  border-radius: 20rpx;
  margin-bottom: 40rpx;
}

.menu-item {
  display: flex;
  align-items: center;
  padding: 25rpx 30rpx;
  border-bottom: 1rpx solid #e4e7ed;
}

.menu-item:last-child {
  border-bottom: none;
}

.menu-item uni-icon:first-child {
  color: #409eff;
  margin-right: 20rpx;
}

.menu-text {
  flex: 1;
  font-size: 28rpx;
  color: #333;
}

.menu-item uni-icon:last-child {
  color: #c0c4cc;
}

.logout-section {
  margin-bottom: 40rpx;
}

.logout-btn {
  width: 100%;
  padding: 25rpx;
  background-color: #f56c6c;
  color: #fff;
  border: none;
  border-radius: 10rpx;
  font-size: 28rpx;
}

.version-info {
  text-align: center;
}

.version {
  font-size: 22rpx;
  color: #909399;
}
</style>
