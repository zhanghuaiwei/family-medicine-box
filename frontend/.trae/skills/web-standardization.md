# 前端Web端规范化 Agent Skills

## 1. 代码规范

### 1.1 命名规范

- **文件命名**：使用小写字母，单词间用连字符分隔，例如 `home-page.vue`
- **组件命名**：使用大驼峰命名法，首字母大写，例如 `HomePage.vue`
- **变量命名**：使用小驼峰命名法，首字母小写，例如 `userId`
- **常量命名**：使用全大写，单词间用下划线分隔，例如 `MAX_PAGE_SIZE`
- **函数命名**：使用小驼峰命名法，首字母小写，例如 `createUser`
- **类命名**：使用大驼峰命名法，首字母大写，例如 `UserService`
- **接口命名**：使用大驼峰命名法，首字母大写，前面加 `I` 前缀，例如 `IUser`

### 1.2 代码格式

- **缩进**：使用2个空格进行缩进
- **行宽**：每行代码不超过120个字符
- **空行**：
  - 函数与函数之间空一行
  - 逻辑块之间空一行
  - 文件末尾保留一个空行
- **大括号**：使用K&R风格，左大括号与代码在同一行，右大括号单独占一行
- **分号**：使用分号结束语句
- **引号**：使用单引号

### 1.3 代码结构

- **Vue组件结构**：
  1. `<template>`：模板部分
  2. `<script setup>`：脚本部分
  3. `<style scoped>`：样式部分

- **脚本结构**：
  1. 导入语句
  2. 组件定义
  3. 响应式数据
  4. 计算属性
  5. 方法
  6. 生命周期钩子

## 2. 注释规范

### 2.1 组件注释

```vue
<template>
  <!-- 首页仪表盘组件 -->
  <div class="dashboard">
    <!-- 内容 -->
  </div>
</template>

<script setup lang="ts">
/**
 * 首页仪表盘组件
 * 显示药品统计、库存状态、最近报警等信息
 */
// 组件内容
</script>
```

### 2.2 函数注释

```typescript
/**
 * 创建用户
 * @param user 用户信息
 * @returns Promise<User> 创建的用户
 */
async function createUser(user: User): Promise<User> {
  // 函数内容
}
```

### 2.3 变量注释

```typescript
/**
 * 用户ID
 */
const userId = ref<string>('');

/**
 * 药品列表
 */
const medicines = ref<Medicine[]>([]);
```

### 2.4 代码注释

- **单行注释**：使用 `//` 注释单行代码
- **多行注释**：使用 `/* */` 注释多行代码
- **TODO注释**：使用 `// TODO:` 标记待完成的任务
- **FIXME注释**：使用 `// FIXME:` 标记需要修复的问题

## 3. 配置规范

### 3.1 配置文件结构

- **vite.config.ts**：Vite构建配置
- **tsconfig.json**：TypeScript配置
- **package.json**：项目依赖和脚本
- **.eslintrc.js**：ESLint配置
- **.prettierrc**：Prettier配置

### 3.2 配置项命名

- 使用小写字母，单词间用连字符分隔
- 配置项分组管理，例如：
  - 构建配置：`build.*`
  - 服务器配置：`server.*`
  - 插件配置：`plugins.*`

### 3.3 配置示例

```typescript
// vite.config.ts
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173,
    open: true
  },
  build: {
    outDir: 'dist',
    sourcemap: false
  }
})
```

## 4. 组件规范

### 4.1 组件设计

- **单一职责**：每个组件只负责一个功能
- **可复用性**：设计可复用的组件
- **Props设计**：
  - 使用TypeScript类型定义props
  - 为props添加默认值
  - 为props添加验证

### 4.2 组件示例

```vue
<template>
  <div class="medicine-card">
    <h3>{{ medicine.name }}</h3>
    <p>{{ medicine.specification }}</p>
    <button @click="handleClick">查看详情</button>
  </div>
</template>

<script setup lang="ts">
import { defineProps, defineEmits } from 'vue'

interface Medicine {
  id: string
  name: string
  specification: string
}

const props = defineProps<{
  medicine: Medicine
}>()

const emit = defineEmits<{
  (e: 'click', medicine: Medicine): void
}>()

function handleClick() {
  emit('click', props.medicine)
}
</script>

<style scoped>
.medicine-card {
  border: 1px solid #eaeaea;
  border-radius: 8px;
  padding: 16px;
  margin-bottom: 16px;
}
</style>
```

## 5. 状态管理

### 5.1 Pinia使用规范

- **Store设计**：按功能模块创建store
- **State设计**：使用TypeScript类型定义state
- **Actions**：处理异步操作
- **Getters**：处理计算属性

### 5.2 Store示例

```typescript
// stores/user.ts
import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import type { User } from '../types'

export const useUserStore = defineStore('user', () => {
  const user = ref<User | null>(null)
  const token = ref<string>('')

  const isLoggedIn = computed(() => !!token.value)

  function setUser(newUser: User) {
    user.value = newUser
  }

  function setToken(newToken: string) {
    token.value = newToken
  }

  function logout() {
    user.value = null
    token.value = ''
  }

  return {
    user,
    token,
    isLoggedIn,
    setUser,
    setToken,
    logout
  }
})
```

## 6. API调用

### 6.1 Axios配置

```typescript
// api/axios.ts
import axios from 'axios'
import { useUserStore } from '../stores/user'

const axiosInstance = axios.create({
  baseURL: '/api',
  timeout: 10000
})

axiosInstance.interceptors.request.use(
  (config) => {
    const userStore = useUserStore()
    if (userStore.token) {
      config.headers.Authorization = `Bearer ${userStore.token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

axiosInstance.interceptors.response.use(
  (response) => {
    return response.data
  },
  (error) => {
    if (error.response?.status === 401) {
      const userStore = useUserStore()
      userStore.logout()
    }
    return Promise.reject(error)
  }
)

export default axiosInstance
```

### 6.2 API封装

```typescript
// api/user.ts
import axios from './axios'
import type { User } from '../types'

export const userApi = {
  createUser: (user: Omit<User, 'id'>) => {
    return axios.post<User>('/users', user)
  },
  getUserById: (id: string) => {
    return axios.get<User>(`/users/${id}`)
  },
  updateUser: (user: User) => {
    return axios.put<User>('/users', user)
  },
  deleteUser: (id: string) => {
    return axios.delete(`/users/${id}`)
  },
  login: (phone: string, password: string) => {
    return axios.post<User>('/users/login', { phone, password })
  }
}
```

## 7. 样式规范

### 7.1 CSS规范

- **命名规范**：使用BEM命名规范，例如 `.medicine-card__title`
- **选择器**：使用类选择器，避免使用ID选择器
- **嵌套**：避免深度嵌套，最多3层
- **变量**：使用Less变量管理颜色、字体等
- **响应式**：使用媒体查询实现响应式设计

### 7.2 样式示例

```less
// variables.less
@primary-color: #409EFF;
@success-color: #67C23A;
@warning-color: #E6A23C;
@danger-color: #F56C6C;
@text-color: #303133;

// medicine-card.less
.medicine-card {
  border: 1px solid #eaeaea;
  border-radius: 8px;
  padding: 16px;
  margin-bottom: 16px;
  
  &__title {
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 8px;
    color: @text-color;
  }
  
  &__specification {
    font-size: 14px;
    color: #606266;
    margin-bottom: 12px;
  }
  
  &__button {
    background-color: @primary-color;
    color: white;
    border: none;
    padding: 8px 16px;
    border-radius: 4px;
    cursor: pointer;
    
    &:hover {
      background-color: darken(@primary-color, 10%);
    }
  }
}
```

## 8. 错误处理

### 8.1 全局错误处理

```typescript
// utils/errorHandler.ts
export function handleError(error: any): string {
  if (error.response) {
    // 服务器返回错误
    return error.response.data.message || '服务器错误'
  } else if (error.request) {
    // 请求发送失败
    return '网络错误，请检查网络连接'
  } else {
    // 其他错误
    return error.message || '未知错误'
  }
}
```

### 8.2 组件错误处理

```vue
<template>
  <div>
    <div v-if="loading">加载中...</div>
    <div v-else-if="error">{{ error }}</div>
    <div v-else>
      <!-- 内容 -->
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { userApi } from '../api/user'
import { handleError } from '../utils/errorHandler'

const loading = ref(false)
const error = ref('')
const user = ref(null)

onMounted(async () => {
  try {
    loading.value = true
    user.value = await userApi.getUserById('123')
  } catch (err) {
    error.value = handleError(err)
  } finally {
    loading.value = false
  }
})
</script>
```

## 9. 性能优化

### 9.1 代码优化

- **懒加载**：使用动态导入实现组件懒加载
- **虚拟滚动**：处理大量数据列表
- **缓存**：缓存API请求结果
- **防抖节流**：优化频繁触发的事件

### 9.2 构建优化

- **代码分割**：使用动态导入实现代码分割
- **tree shaking**：移除未使用的代码
- **压缩**：压缩CSS和JavaScript
- **CDN**：使用CDN加载第三方库

## 10. 测试规范

### 10.1 单元测试

```typescript
// tests/unit/Medicine.test.ts
import { mount } from '@vue/test-utils'
import Medicine from '../../src/views/Medicine.vue'

describe('Medicine.vue', () => {
  test('renders medicine management title', () => {
    const wrapper = mount(Medicine)
    expect(wrapper.find('h1').text()).toBe('药品管理')
  })

  test('renders medicine list section', () => {
    const wrapper = mount(Medicine)
    expect(wrapper.find('.medicine-list').exists()).toBe(true)
  })
})
```

### 10.2 测试覆盖率

- 单元测试覆盖率不低于80%
- 测试关键业务逻辑
- 测试组件渲染和交互

## 11. 工具和插件

### 11.1 IDE插件

- **Vetur**：Vue语法高亮
- **ESLint**：代码风格检查
- **Prettier**：代码格式化
- **TypeScript**：TypeScript支持

### 11.2 构建工具

- **Vite**：构建工具
- **Rollup**：打包工具
- **ESBuild**：快速构建

### 11.3 代码质量工具

- **ESLint**：代码风格检查
- **Prettier**：代码格式化
- **TypeScript**：类型检查

## 12. 最佳实践

### 12.1 代码组织

- 按功能模块组织代码
- 使用目录结构清晰的项目结构
- 遵循单一职责原则

### 12.2 性能优化

- 使用Vue 3的Composition API
- 合理使用ref和reactive
- 避免不必要的渲染
- 使用computed和watch优化

### 12.3 安全性

- 输入验证
- XSS防护
- CSRF防护
- 敏感信息加密

### 12.4 可维护性

- 清晰的代码结构
- 详细的注释
- 模块化设计
- 可测试性

## 13. 总结

前端Web端规范化Agent Skills提供了一套完整的代码规范、注释规范、配置规范等，帮助开发团队编写高质量、可维护的代码。通过遵循这些规范，可以提高代码质量，减少bug，提高开发效率，确保系统的稳定性和安全性。

这些规范不仅适用于当前项目，也可以作为其他Vue 3项目的参考。通过持续的实践和改进，不断完善和优化这些规范，为项目的长期发展奠定坚实的基础。
