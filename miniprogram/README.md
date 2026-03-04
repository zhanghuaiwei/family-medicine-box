# 家庭药箱 - 微信小程序

## 📋 项目概述

家庭药箱微信小程序，使用uni-app框架开发，为用户提供便捷的药品管理、库存查询、报警提醒等功能，随时随地查看家庭药箱状态。

## 🛠️ 技术栈

- **前端框架**: Vue 3
- **跨端框架**: uni-app
- **UI组件库**: uni-ui
- **构建工具**: Vite 7.3.1
- **类型系统**: TypeScript
- **包管理器**: pnpm

## ✨ 实现功能

- **首页**: 快速访问常用功能、查看最近药品、显示报警信息
- **药品管理**: 药品的增删改查、分类浏览
- **库存管理**: 库存状态查看、低库存预警、过期预警
- **个人中心**: 用户信息管理、系统设置、关于我们
- **扫码功能**: 支持扫描药品条形码快速添加药品
- **消息通知**: 接收报警提醒和系统通知

## 📁 目录结构

```
miniprogram/
├── src/
│   ├── assets/         # 静态资源
│   ├── components/     # 通用组件
│   ├── pages/          # 页面组件
│   │   ├── home.vue    # 首页
│   │   ├── medicine.vue # 药品管理
│   │   ├── inventory.vue # 库存管理
│   │   └── mine.vue    # 个人中心
│   ├── utils/          # 工具函数
│   ├── api/            # API接口
│   ├── App.vue         # 根组件
│   └── main.ts         # 应用入口
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
- HBuilderX (用于小程序打包)

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

- `home.test.ts` - 测试首页组件
- `medicine.test.ts` - 测试药品管理组件
- `inventory.test.ts` - 测试库存管理组件
- `mine.test.ts` - 测试个人中心组件

## 🔧 配置文件

- **vite.config.ts**: Vite构建配置
- **vitest.config.ts**: 测试框架配置
- **tsconfig.json**: TypeScript配置
- **package.json**: 项目依赖和脚本

## 📱 小程序使用说明

1. **扫码登录**: 使用微信扫描小程序码进入应用
2. **注册/登录**: 首次使用需要注册账号或登录
3. **添加药品**: 点击首页的"添加药品"按钮，或使用扫码功能
4. **查看库存**: 在"库存管理"页面查看所有药品的库存状态
5. **接收提醒**: 开启消息通知，及时接收低库存和过期提醒

## 📞 项目维护团队

- **项目维护者**: [zhanghuaiwei](https://github.com/zhanghuaiwei)
- **邮箱**: 947201280@qq.com

---

- **项目维护者**: [hddwsz](https://github.com/hddwsz)
- **邮箱**: hddwsz@163.com

---

**💊 家庭药箱 - 您的家庭健康小助手**
