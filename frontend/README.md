# 家庭药箱管理平台 - 前端

## 📋 项目概述

家庭药箱管理平台的Web管理端，使用Vue 3 + Element Plus + Vite + TypeScript + Pinia技术栈，实现药品管理、库存监控、报警管理等功能。

## 🛠️ 技术栈

- **前端框架**: Vue 3
- **构建工具**: Vite 7.3.1
- **UI组件库**: Element Plus
- **状态管理**: Pinia
- **类型系统**: TypeScript
- **HTTP客户端**: Axios
- **图表库**: ECharts
- **CSS预处理器**: Less
- **包管理器**: pnpm

## ✨ 实现功能

- **首页仪表盘**: 显示药品统计、库存状态、最近报警等信息
- **药品管理**: 药品的增删改查、分类管理
- **库存管理**: 库存监控、低库存预警、过期预警
- **报警管理**: 报警设置、报警历史、多渠道通知
- **响应式设计**: 适配不同屏幕尺寸

## 📁 目录结构

```
frontend/
├── src/
│   ├── assets/         # 静态资源
│   ├── components/     # 通用组件
│   ├── views/          # 页面组件
│   │   ├── Home.vue    # 首页
│   │   ├── Medicine.vue # 药品管理
│   │   ├── Inventory.vue # 库存管理
│   │   └── Alert.vue   # 报警管理
│   ├── router/         # 路由配置
│   ├── store/          # Pinia状态管理
│   ├── utils/          # 工具函数
│   ├── api/            # API接口
│   ├── main.ts         # 应用入口
│   └── App.vue         # 根组件
├── tests/              # 测试用例
│   └── unit/           # 单元测试
├── public/             # 公共文件
├── index.html          # HTML模板
├── vite.config.ts      # Vite配置
├── vitest.config.ts    # Vitest配置
├── tsconfig.json       # TypeScript配置
├── package.json        # 项目配置
└── README.md           # 项目说明
```

## 🚀 快速开始

### 环境要求

- Node.js 22.18.0+
- pnpm 10.0.0+

### 安装依赖

```bash
pnpm install
```

### 开发模式

```bash
pnpm dev
```

### 构建生产版本

```bash
pnpm build
```

### 预览生产版本

```bash
pnpm preview
```

### 运行测试

```bash
pnpm test
```

## 📝 测试用例

项目使用 Vitest + Vue Test Utils 进行单元测试，测试文件位于 `tests/unit/` 目录：

- `Home.test.ts` - 测试首页组件
- `Medicine.test.ts` - 测试药品管理组件
- `Inventory.test.ts` - 测试库存管理组件
- `Alert.test.ts` - 测试报警管理组件

## 🔧 配置文件

- **vite.config.ts**: Vite构建配置
- **vitest.config.ts**: 测试框架配置
- **tsconfig.json**: TypeScript配置
- **package.json**: 项目依赖和脚本

## 📞 联系方式

- 项目维护者: 开发团队
- 邮箱: contact@medicinebox.com
- 版本: 1.0.0

---

**💊 家庭药箱管理平台 - 守护家人健康的智能助手**
