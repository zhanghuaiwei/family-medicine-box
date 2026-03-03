# 后端Spring Boot规范化 Agent Skills

## 1. 代码规范

### 1.1 命名规范

- **包名**：使用小写字母，采用反向域名命名法，例如 `com.medicinebox.common.model`
- **类名**：使用驼峰命名法，首字母大写，例如 `UserController`
- **方法名**：使用驼峰命名法，首字母小写，例如 `createUser`
- **变量名**：使用驼峰命名法，首字母小写，例如 `userId`
- **常量名**：使用全大写，单词间用下划线分隔，例如 `MAX_PAGE_SIZE`
- **参数名**：使用驼峰命名法，首字母小写，例如 `userName`

### 1.2 代码格式

- **缩进**：使用4个空格进行缩进
- **行宽**：每行代码不超过120个字符
- **空行**：
  - 类的成员变量与方法之间空一行
  - 方法与方法之间空一行
  - 方法内部的逻辑块之间空一行
- **大括号**：使用K&R风格，左大括号与代码在同一行，右大括号单独占一行

### 1.3 代码结构

- **类结构**：
  1. 包声明
  2. 导入语句
  3. 类注释
  4. 类定义
  5. 成员变量
  6. 构造方法
  7. 普通方法
  8. getter/setter方法

- **方法结构**：
  1. 方法注释
  2. 方法定义
  3. 参数校验
  4. 业务逻辑
  5. 返回结果

## 2. 注释规范

### 2.1 类注释

```java
/**
 * 用户控制器
 * 处理用户相关的HTTP请求
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    // 类内容
}
```

### 2.2 方法注释

```java
/**
 * 创建用户
 * @param user 用户信息
 * @return 响应结果
 */
@PostMapping
public Response<User> createUser(@RequestBody User user) {
    // 方法内容
}
```

### 2.3 字段注释

```java
/**
 * 主键ID（UUID）
 */
private String id;

/**
 * 用户名
 */
private String username;
```

### 2.4 代码注释

- **单行注释**：使用 `//` 注释单行代码
- **多行注释**：使用 `/* */` 注释多行代码
- **TODO注释**：使用 `// TODO:` 标记待完成的任务
- **FIXME注释**：使用 `// FIXME:` 标记需要修复的问题

## 3. 配置规范

### 3.1 配置文件结构

- **application.yml**：主配置文件
- **application-dev.yml**：开发环境配置
- **application-test.yml**：测试环境配置
- **application-prod.yml**：生产环境配置

### 3.2 配置项命名

- 使用小写字母，单词间用连字符分隔，例如 `spring.datasource.url`
- 配置项分组管理，例如：
  - 数据库配置：`spring.datasource.*`
  - 服务配置：`server.*`
  - 业务配置：`medicinebox.*`

### 3.3 配置示例

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/medicine_box
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver

server:
  port: 8080
  servlet:
    context-path: /api

medicinebox:
  jwt:
    secret: your-secret-key
    expiration: 86400
  alert:
    threshold: 3
```

## 4. 异常处理

### 4.1 全局异常处理

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Response<Void> handleException(Exception e) {
        return Response.error(500, "服务器内部错误: " + e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public Response<Void> handleBadRequestException(BadRequestException e) {
        return Response.error(400, e.getMessage());
    }
}
```

### 4.2 自定义异常

```java
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
```

## 5. 日志规范

### 5.1 日志级别

- **DEBUG**：调试信息，仅在开发环境使用
- **INFO**：普通信息，记录正常的业务流程
- **WARN**：警告信息，记录可能的问题
- **ERROR**：错误信息，记录异常情况

### 5.2 日志使用

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User createUser(User user) {
        logger.info("创建用户: {}", user.getUsername());
        try {
            // 业务逻辑
        } catch (Exception e) {
            logger.error("创建用户失败: {}", e.getMessage(), e);
            throw e;
        }
        return user;
    }
}
```

## 6. 安全规范

### 6.1 密码加密

```java
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(String password) {
        return encoder.encode(password);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
```

### 6.2 SQL注入防护

- 使用MyBatis-Plus的参数化查询
- 避免直接拼接SQL语句
- 使用#{}而不是${}

### 6.3 XSS防护

- 对用户输入进行过滤和转义
- 使用Spring Security的XSS防护

## 7. 性能优化

### 7.1 数据库优化

- 使用合适的索引
- 避免全表扫描
- 合理使用JOIN操作
- 使用分页查询

### 7.2 代码优化

- 避免重复代码
- 使用缓存
- 合理使用线程池
- 避免内存泄漏

### 7.3 接口优化

- 使用异步处理
- 合理设置超时时间
- 使用连接池
- 避免长事务

## 8. 测试规范

### 8.1 单元测试

```java
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        // 测试代码
    }
}
```

### 8.2 集成测试

- 测试完整的业务流程
- 使用真实的数据库
- 测试API接口

### 8.3 测试覆盖率

- 单元测试覆盖率不低于80%
- 集成测试覆盖主要业务流程

## 9. 依赖管理

### 9.1 依赖版本控制

- 在parent pom.xml中统一管理依赖版本
- 使用dependencyManagement管理依赖版本
- 避免依赖冲突

### 9.2 依赖选择

- 使用稳定版本的依赖
- 避免使用过时的依赖
- 定期更新依赖版本

## 10. 部署规范

### 10.1 构建规范

- 使用Maven构建项目
- 执行单元测试和集成测试
- 生成可执行jar包

### 10.2 部署环境

- 开发环境：本地开发服务器
- 测试环境：测试服务器
- 生产环境：生产服务器

### 10.3 环境变量

- 使用环境变量配置敏感信息
- 避免硬编码配置

## 11. 代码审查

### 11.1 审查内容

- 代码风格
- 代码质量
- 安全问题
- 性能问题
- 功能正确性

### 11.2 审查流程

1. 提交代码前自检
2. 团队成员代码审查
3. 解决审查中发现的问题
4. 合并代码

## 12. 文档规范

### 12.1 技术文档

- 架构文档
- 数据库设计文档
- API接口文档
- 部署文档

### 12.2 代码文档

- 类注释
- 方法注释
- 字段注释
- README.md

## 13. 最佳实践

### 13.1 代码组织

- 按功能模块组织代码
- 遵循分层架构
- 保持代码简洁

### 13.2 错误处理

- 统一错误处理
- 详细的错误信息
- 适当的日志记录

### 13.3 安全性

- 密码加密
- 输入验证
- 权限控制
- 防止SQL注入

### 13.4 性能

- 数据库优化
- 代码优化
- 缓存使用
- 异步处理

## 14. 工具和插件

### 14.1 IDE插件

- Lombok：简化代码
- MyBatis-Plus Generator：代码生成
- Spring Boot DevTools：开发工具

### 14.2 构建工具

- Maven：项目构建
- Jenkins：持续集成

### 14.3 代码质量工具

- SonarQube：代码质量分析
- Checkstyle：代码风格检查
- FindBugs：代码缺陷检查

## 15. 总结

后端Spring Boot规范化Agent Skills提供了一套完整的代码规范、注释规范、配置规范等，帮助开发团队编写高质量、可维护的代码。通过遵循这些规范，可以提高代码质量，减少bug，提高开发效率，确保系统的稳定性和安全性。

这些规范不仅适用于当前项目，也可以作为其他Spring Boot项目的参考。通过持续的实践和改进，不断完善和优化这些规范，为项目的长期发展奠定坚实的基础。
