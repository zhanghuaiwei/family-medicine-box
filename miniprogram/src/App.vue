<template>
  <view class="app-container">
    <uni-tab-bar :list="tabBarList" @click="handleTabClick"></uni-tab-bar>
    <view class="content">
      <home v-if="currentTab === 'home'" />
      <medicine v-if="currentTab === 'medicine'" />
      <inventory v-if="currentTab === 'inventory'" />
      <mine v-if="currentTab === 'mine'" />
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import home from './pages/home.vue'
import medicine from './pages/medicine.vue'
import inventory from './pages/inventory.vue'
import mine from './pages/mine.vue'

const currentTab = ref('home')

const tabBarList = [
  {
    pagePath: '/pages/home',
    text: '首页',
    iconPath: '/static/home.png',
    selectedIconPath: '/static/home-active.png'
  },
  {
    pagePath: '/pages/medicine',
    text: '药品',
    iconPath: '/static/medicine.png',
    selectedIconPath: '/static/medicine-active.png'
  },
  {
    pagePath: '/pages/inventory',
    text: '库存',
    iconPath: '/static/inventory.png',
    selectedIconPath: '/static/inventory-active.png'
  },
  {
    pagePath: '/pages/mine',
    text: '我的',
    iconPath: '/static/mine.png',
    selectedIconPath: '/static/mine-active.png'
  }
]

const handleTabClick = (e: any) => {
  currentTab.value = e.detail
}
</script>

<style scoped>
.app-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.content {
  flex: 1;
  padding: 20rpx;
}

uni-tab-bar {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 999;
}
</style>
