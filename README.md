# 家庭药箱小助手

## 📋 项目概述

**家庭药箱小助手**是一个集Web管理端和微信小程序于一体的智能健康管理解决方案，旨在解决家庭药品管理的痛点问题，为有老人、小孩的家庭提供便捷的药品管理工具。

### 项目名称
家庭药箱小助手

### 项目描述
本系统通过Web管理端和微信小程序，实现药品的集中管理、库存监控、过期预警和低库存提醒等功能，帮助家庭用户更好地管理药品，避免药品过期浪费，确保常用药品随时可用。

### 解决的用户痛点
1. **药品管理混乱**: 家庭药品分散存放，难以统一管理
2. **过期药品风险**: 药品过期未及时发现，存在安全隐患
3. **库存不足**: 常用药品用完了还不清楚，需要时找不到
4. **使用不便**: 传统药箱缺乏智能提醒功能
5. **信息不对称**: 家庭成员之间药品使用情况不透明

## 🛠️ 技术栈

### Web管理端
- **框架**: Vue 3
- **构建工具**: Vite 7.3.1
- **UI组件库**: Element Plus
- **状态管理**: Pinia
- **类型系统**: TypeScript
- **HTTP客户端**: Axios
- **图表库**: ECharts
- **CSS预处理器**: Less
- **node版本**: 22.18.0+
- **包管理器**: pnpm

### 服务端
- **框架**: Spring Boot 2.7.15
- **微服务架构**: Spring Cloud
- **数据库**: MySQL 8+
- **持久层**: MyBatis-Plus
- **认证授权**: Spring Security
- **API文档**: Swagger
- **JDK版本**: 1.8
- **构建工具**: Maven

### 微信小程序
- **框架**: Vue 3 + uni-app
- **UI组件库**: uni-ui
- **构建工具**: Vite 7.3.1
- **类型系统**: TypeScript
- **node版本**: 22.18.0+
- **包管理器**: pnpm

## ✨ 实现功能

### Web管理端
- **首页仪表盘**: 药品统计、库存状态、最近报警
- **药品管理**: 药品增删改查、分类管理
- **库存管理**: 库存监控、低库存预警、过期预警
- **报警管理**: 报警设置、报警历史、多渠道通知
- **响应式设计**: 适配不同屏幕尺寸

### 微信小程序
- **首页**: 快速访问、最近药品、报警信息
- **药品管理**: 药品增删改查、分类浏览
- **库存管理**: 库存状态查看、预警信息
- **个人中心**: 用户信息管理、系统设置
- **扫码功能**: 扫描药品条形码快速添加
- **消息通知**: 接收报警提醒

### 后端服务
- **用户管理**: 注册、登录、权限管理
- **药品管理**: 药品和分类的CRUD操作
- **库存管理**: 库存监控和预警逻辑
- **报警管理**: 报警触发和多渠道通知
- **数据统计**: 药品使用统计、库存分析
- **API接口**: RESTful API设计

## 📁 项目结构

```
家庭药箱/
├── frontend/         # Web管理端
├── miniprogram/      # 微信小程序
├── backend/          # 后端服务
│   ├── medicine-box-common/     # 公共模块
│   ├── medicine-box-biz/        # 业务模块
│   ├── medicine-box-gateway/    # 网关服务
│   ├── medicine-box-utils/      # 工具模块
│   └── db_init.sql             # 数据库初始化脚本
├── README.md         # 项目说明
└── LICENSE           # 开源协议
```

## 🚀 快速开始

### 环境要求

- **前端/小程序**: Node.js 22.18.0+, pnpm 10.0.0+
- **后端**: JDK 1.8+, Maven 3.6.0+, MySQL 8+

### 安装和运行

#### 1. 后端服务

```bash
# 进入后端目录
cd backend

# 构建项目
mvn clean package

# 运行服务
java -jar medicine-box-gateway/target/medicine-box-gateway-1.0.0-SNAPSHOT.jar
java -jar medicine-box-biz/user-service/target/user-service-1.0.0-SNAPSHOT.jar
java -jar medicine-box-biz/medicine-service/target/medicine-service-1.0.0-SNAPSHOT.jar
java -jar medicine-box-biz/inventory-service/target/inventory-service-1.0.0-SNAPSHOT.jar
java -jar medicine-box-biz/alert-service/target/alert-service-1.0.0-SNAPSHOT.jar
```

#### 2. Web管理端

```bash
# 进入前端目录
cd frontend

# 安装依赖
pnpm install

# 运行开发模式
pnpm dev

# 构建生产版本
pnpm build
```

#### 3. 微信小程序

```bash
# 进入小程序目录
cd miniprogram

# 安装依赖
pnpm install

# 运行开发模式
pnpm dev

# 构建生产版本
pnpm build
```

## 🔮 未来规划

### 技术集成
1. **AI智能识别**: 集成OCR技术，自动识别药品包装信息
2. **语音助手**: 添加语音交互功能，方便老人使用
3. **健康数据集成**: 对接智能设备，记录家庭成员健康数据
4. **区块链技术**: 实现药品溯源，确保药品真实性
5. **云服务部署**: 提供云端存储和备份功能

### 功能扩展
1. **药品推荐**: 根据家庭成员情况智能推荐药品
2. **用药提醒**: 定时提醒用药，避免漏服
3. **药品共享**: 支持家庭成员间药品共享和借用
4. **健康知识库**: 提供药品使用指南和健康知识
5. **社区互动**: 建立健康社区，分享用药经验
6. **紧急求助**: 一键求助功能，应对突发情况
7. **多语言支持**: 支持中英文切换，方便国际化用户

## 📞 联系方式

- **项目维护者**: 开发团队
- **邮箱**: contact@medicinebox.com
- **版本**: 1.0.0

## 📄 开源协议

本项目采用 MIT 开源协议，详见 [LICENSE](LICENSE) 文件。

---

**💊 家庭药箱管理系统 - 守护家人健康的智能助手**
