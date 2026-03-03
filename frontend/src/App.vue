<template>
  <div class="app-container">
    <!-- 导航栏 -->
    <el-header height="60px" class="header">
      <div class="logo">家庭药箱管理系统</div>
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#1f2329"
        text-color="#fff"
        active-text-color="#ffd04b"
      >
        <el-menu-item index="1">首页</el-menu-item>
        <el-menu-item index="2">药品管理</el-menu-item>
        <el-menu-item index="3">库存监控</el-menu-item>
        <el-menu-item index="4">报警设置</el-menu-item>
        <el-menu-item index="5">用户管理</el-menu-item>
      </el-menu>
    </el-header>

    <!-- 主要内容区域 -->
    <el-main>
      <router-view />
    </el-main>

    <!-- 页脚 -->
    <el-footer height="40px" class="footer">
      <span>© 2026 家庭药箱管理系统</span>
    </el-footer>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const activeIndex = ref('1')

const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
</script>

<style scoped lang="less">
.app-container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);

  .logo {
    font-size: 20px;
    font-weight: bold;
    color: #fff;
  }
}

.el-main {
  flex: 1;
  padding: 20px;
  background-color: #f5f7fa;
}

.footer {
  text-align: center;
  line-height: 40px;
  background-color: #1f2329;
  color: #fff;
  font-size: 14px;
}
</style>
