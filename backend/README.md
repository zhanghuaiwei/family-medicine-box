# 家庭药箱管理平台 - 后端

## 📋 项目概述

家庭药箱管理平台的后端服务，使用Spring Boot微服务架构，提供药品管理、库存监控、报警管理等API接口，支持Web管理端和微信小程序。

## 🛠️ 技术栈

- **后端框架**: Spring Boot 2.7.15
- **微服务架构**: Spring Cloud
- **数据库**: MySQL 8+
- **持久层**: MyBatis-Plus
- **认证授权**: Spring Security
- **API文档**: Swagger
- **日志框架**: Logback
- **构建工具**: Maven
- **JDK版本**: 1.8
- **包管理器**: Maven

## ✨ 实现功能

- **用户管理**: 用户注册、登录、权限管理
- **药品管理**: 药品的增删改查、分类管理
- **库存管理**: 库存监控、低库存预警、过期预警
- **报警管理**: 报警设置、报警触发、多渠道通知
- **数据统计**: 药品使用统计、库存状态分析
- **API接口**: RESTful API设计，支持前端和小程序调用

## 📁 目录结构

```
backend/
├── medicine-box-common/         # 公共模块
│   └── src/main/java/com/medicinebox/common/
│       ├── model/              # 数据模型
│       ├── response/           # 响应封装
│       └── utils/              # 工具类
├── medicine-box-biz/            # 业务模块
│   ├── user-service/           # 用户服务
│   ├── medicine-service/       # 药品服务
│   ├── inventory-service/      # 库存服务
│   └── alert-service/          # 报警服务
├── medicine-box-gateway/        # 网关服务
├── medicine-box-utils/          # 工具模块
├── db_init.sql                 # 数据库初始化脚本
├── pom.xml                     # Maven配置
└── README.md                   # 项目说明
```

## 🚀 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.6.0+
- MySQL 8+

### 数据库初始化

1. 创建数据库

```sql
CREATE DATABASE IF NOT EXISTS medicine_box DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 执行初始化脚本

```bash
mysql -u root -p medicine_box < db_init.sql
```

### 构建项目

```bash
mvn clean package
```

### 运行服务

```bash
# 运行网关服务
java -jar medicine-box-gateway/target/medicine-box-gateway-1.0.0-SNAPSHOT.jar

# 运行用户服务
java -jar medicine-box-biz/user-service/target/user-service-1.0.0-SNAPSHOT.jar

# 运行药品服务
java -jar medicine-box-biz/medicine-service/target/medicine-service-1.0.0-SNAPSHOT.jar

# 运行库存服务
java -jar medicine-box-biz/inventory-service/target/inventory-service-1.0.0-SNAPSHOT.jar

# 运行报警服务
java -jar medicine-box-biz/alert-service/target/alert-service-1.0.0-SNAPSHOT.jar
```

### 运行测试

```bash
mvn test
```

## 📝 测试用例

项目使用 JUnit + Mockito 进行单元测试，测试文件位于各服务的 `src/test/` 目录：

- **用户服务**: `user-service/src/test/java/com/medicinebox/user/controller/UserControllerTest.java`
- **药品服务**: `medicine-service/src/test/java/com/medicinebox/medicine/controller/MedicineControllerTest.java`

## 🔧 配置文件

- **pom.xml**: Maven依赖和项目配置
- **application.yml**: Spring Boot应用配置
- **db_init.sql**: 数据库初始化脚本

## 📡 API接口

### 基础路径

所有API接口的基础路径为：`http://localhost:8080/api`

### 主要接口

- **用户管理**: `/api/users`
- **药品管理**: `/api/medicines`
- **库存管理**: `/api/inventory`
- **报警管理**: `/api/alerts`

### API文档

启动服务后，可访问 Swagger UI 查看API文档：

```
http://localhost:8080/swagger-ui.html
```

## 📞 项目维护团队

- **项目维护者**: [zhanghuaiwei](https://github.com/zhanghuaiwei)
- **邮箱**: 947201280@qq.com

---

- **项目维护者**: [hddwsz](https://github.com/hddwsz)
- **邮箱**: hddwsz@163.com

---

**💊 家庭药箱管理平台 - 智能健康管理解决方案**
