# 小程序端规范化 Agent Skills

## 1. 代码规范

### 1.1 命名规范

- **文件命名**：使用小写字母，单词间用连字符分隔，例如 `home-page.vue`
- **组件命名**：使用大驼峰命名法，首字母大写，例如 `MedicineCard.vue`
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
  <!-- 首页组件 -->
  <view class="home">
    <!-- 内容 -->
  </view>
</template>

<script setup lang="ts">
/**
 * 首页组件
 * 显示快速访问、最近药品、报警信息等
 */
// 组件内容
</script>
```

### 2.2 函数注释

```typescript
/**
 * 获取药品列表
 * @returns Promise<Medicine[]> 药品列表
 */
async function getMedicines(): Promise<Medicine[]> {
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
- **uni.config.ts**：uni-app配置
- **pages.json**：页面配置
- **manifest.json**：小程序配置

### 3.2 页面配置

```json
// pages.json
{
  "pages": [
    {
      "path": "pages/home/home",
      "style": {
        "navigationBarTitleText": "家庭药箱"
      }
    },
    {
      "path": "pages/medicine/medicine",
      "style": {
        "navigationBarTitleText": "药品管理"
      }
    },
    {
      "path": "pages/inventory/inventory",
      "style": {
        "navigationBarTitleText": "库存管理"
      }
    },
    {
      "path": "pages/mine/mine",
      "style": {
        "navigationBarTitleText": "个人中心"
      }
    }
  ],
  "tabBar": {
    "color": "#666",
    "selectedColor": "#409EFF",
    "list": [
      {
        "pagePath": "pages/home/home",
        "text": "首页",
        "iconPath": "static/tabbar/home.png",
        "selectedIconPath": "static/tabbar/home-active.png"
      },
      {
        "pagePath": "pages/medicine/medicine",
        "text": "药品",
        "iconPath": "static/tabbar/medicine.png",
        "selectedIconPath": "static/tabbar/medicine-active.png"
      },
      {
        "pagePath": "pages/inventory/inventory",
        "text": "库存",
        "iconPath": "static/tabbar/inventory.png",
        "selectedIconPath": "static/tabbar/inventory-active.png"
      },
      {
        "pagePath": "pages/mine/mine",
        "text": "我的",
        "iconPath": "static/tabbar/mine.png",
        "selectedIconPath": "static/tabbar/mine-active.png"
      }
    ]
  }
}
```

### 3.3 小程序配置

```json
// manifest.json
{
  "name": "家庭药箱",
  "appid": "wx1234567890abcdef",
  "description": "家庭药箱管理小程序",
  "versionName": "1.0.0",
  "versionCode": "100",
  "uni-app": {
    "usingComponents": true
  },
  "mp-weixin": {
    "appid": "wx1234567890abcdef",
    "setting": {
      "urlCheck": false
    },
    "usingComponents": true
  }
}
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
  <view class="medicine-card">
    <text class="medicine-card__name">{{ medicine.name }}</text>
    <text class="medicine-card__specification">{{ medicine.specification }}</text>
    <button class="medicine-card__button" @click="handleClick">查看详情</button>
  </view>
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
  padding: 16rpx;
  margin-bottom: 16rpx;
}

.medicine-card__name {
  font-size: 32rpx;
  font-weight: bold;
  margin-bottom: 8rpx;
  color: #333;
}

.medicine-card__specification {
  font-size: 28rpx;
  color: #666;
  margin-bottom: 12rpx;
}

.medicine-card__button {
  background-color: #409EFF;
  color: white;
  border: none;
  padding: 12rpx 24rpx;
  border-radius: 4rpx;
  font-size: 28rpx;
}
</style>
```

## 5. API调用

### 5.1 网络请求封装

```typescript
// utils/request.ts
import { showToast } from '@dcloudio/uni-app'

interface RequestOptions {
  url: string
  method?: 'GET' | 'POST' | 'PUT' | 'DELETE'
  data?: any
  header?: any
}

export function request<T>(options: RequestOptions): Promise<T> {
  return new Promise((resolve, reject) => {
    uni.request({
      url: `https://api.medicinebox.com${options.url}`,
      method: options.method || 'GET',
      data: options.data,
      header: {
        'Content-Type': 'application/json',
        ...options.header
      },
      success: (res) => {
        if (res.statusCode === 200) {
          resolve(res.data as T)
        } else {
          showToast({
            title: res.data.message || '请求失败',
            icon: 'none'
          })
          reject(res.data)
        }
      },
      fail: (err) => {
        showToast({
          title: '网络错误，请检查网络连接',
          icon: 'none'
        })
        reject(err)
      }
    })
  })
}
```

### 5.2 API封装

```typescript
// api/medicine.ts
import { request } from '../utils/request'
import type { Medicine } from '../types'

export const medicineApi = {
  getMedicines: () => {
    return request<Medicine[]>('/api/medicines')
  },
  getMedicineById: (id: string) => {
    return request<Medicine>(`/api/medicines/${id}`)
  },
  createMedicine: (medicine: Omit<Medicine, 'id'>) => {
    return request<Medicine>('/api/medicines', {
      method: 'POST',
      data: medicine
    })
  }
}
```

## 6. 样式规范

### 6.1 CSS规范

- **命名规范**：使用BEM命名规范，例如 `.medicine-card__title`
- **选择器**：使用类选择器，避免使用ID选择器
- **嵌套**：避免深度嵌套，最多3层
- **单位**：使用rpx作为单位，适配不同屏幕
- **变量**：使用CSS变量管理颜色、字体等
- **响应式**：使用媒体查询实现响应式设计

### 6.2 样式示例

```css
/* variables.css */
:root {
  --primary-color: #409EFF;
  --success-color: #67C23A;
  --warning-color: #E6A23C;
  --danger-color: #F56C6C;
  --text-color: #333;
  --border-color: #eaeaea;
}

/* medicine-card.css */
.medicine-card {
  border: 1px solid var(--border-color);
  border-radius: 8rpx;
  padding: 16rpx;
  margin-bottom: 16rpx;
}

.medicine-card__name {
  font-size: 32rpx;
  font-weight: bold;
  margin-bottom: 8rpx;
  color: var(--text-color);
}

.medicine-card__specification {
  font-size: 28rpx;
  color: #666;
  margin-bottom: 12rpx;
}

.medicine-card__button {
  background-color: var(--primary-color);
  color: white;
  border: none;
  padding: 12rpx 24rpx;
  border-radius: 4rpx;
  font-size: 28rpx;
}

.medicine-card__button:hover {
  background-color: #66b1ff;
}
```

## 7. 错误处理

### 7.1 全局错误处理

```typescript
// utils/errorHandler.ts
export function handleError(error: any): string {
  if (error.message) {
    return error.message
  } else if (error.errMsg) {
    return error.errMsg
  } else {
    return '未知错误'
  }
}

// 全局异常捕获
uni.onError((error) => {
  console.error('全局错误:', error)
  uni.showToast({
    title: handleError(error),
    icon: 'none'
  })
})
```

### 7.2 组件错误处理

```vue
<template>
  <view>
    <view v-if="loading">加载中...</view>
    <view v-else-if="error">{{ error }}</view>
    <view v-else>
      <!-- 内容 -->
    </view>
  </view>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { medicineApi } from '../api/medicine'
import { handleError } from '../utils/errorHandler'

const loading = ref(false)
const error = ref('')
const medicines = ref<Medicine[]>([])

onMounted(async () => {
  try {
    loading.value = true
    medicines.value = await medicineApi.getMedicines()
  } catch (err) {
    error.value = handleError(err)
  } finally {
    loading.value = false
  }
})
</script>
```

## 8. 性能优化

### 8.1 代码优化

- **懒加载**：使用动态导入实现组件懒加载
- **虚拟滚动**：处理大量数据列表
- **缓存**：缓存API请求结果
- **防抖节流**：优化频繁触发的事件

### 8.2 小程序优化

- **减少setData**：避免频繁调用setData
- **使用自定义组件**：拆分复杂页面为多个组件
- **图片优化**：使用合适的图片尺寸和格式
- **网络请求优化**：合并请求，使用缓存

## 9. 测试规范

### 9.1 单元测试

```typescript
// tests/unit/home.test.ts
import { mount } from '@vue/test-utils'
import Home from '../../src/pages/home.vue'

describe('home.vue', () => {
  test('renders home page title', () => {
    const wrapper = mount(Home)
    expect(wrapper.find('text').text()).toBe('家庭药箱')
  })

  test('renders quick access section', () => {
    const wrapper = mount(Home)
    expect(wrapper.find('.quick-access').exists()).toBe(true)
  })
})
```

### 9.2 测试覆盖率

- 单元测试覆盖率不低于80%
- 测试关键业务逻辑
- 测试组件渲染和交互

## 10. 工具和插件

### 10.1 IDE插件

- **HBuilderX**：uni-app开发工具
- **Vetur**：Vue语法高亮
- **ESLint**：代码风格检查
- **Prettier**：代码格式化

### 10.2 构建工具

- **Vite**：构建工具
- **uni-app CLI**：uni-app命令行工具

### 10.3 代码质量工具

- **ESLint**：代码风格检查
- **Prettier**：代码格式化
- **TypeScript**：类型检查

## 11. 最佳实践

### 11.1 代码组织

- 按功能模块组织代码
- 使用目录结构清晰的项目结构
- 遵循单一职责原则

### 11.2 性能优化

- 使用Vue 3的Composition API
- 合理使用ref和reactive
- 避免不必要的渲染
- 使用computed和watch优化

### 11.3 安全性

- 输入验证
- XSS防护
- CSRF防护
- 敏感信息加密

### 11.4 可维护性

- 清晰的代码结构
- 详细的注释
- 模块化设计
- 可测试性

### 11.5 小程序特有规范

- **页面生命周期**：使用uni-app的页面生命周期
- **API调用**：使用uni-app的API而不是浏览器API
- **组件使用**：使用uni-app的组件或uni-ui组件
- **样式适配**：使用rpx单位适配不同屏幕

## 12. 总结

小程序端规范化Agent Skills提供了一套完整的代码规范、注释规范、配置规范等，帮助开发团队编写高质量、可维护的代码。通过遵循这些规范，可以提高代码质量，减少bug，提高开发效率，确保系统的稳定性和安全性。

这些规范不仅适用于当前项目，也可以作为其他uni-app项目的参考。通过持续的实践和改进，不断完善和优化这些规范，为项目的长期发展奠定坚实的基础。
