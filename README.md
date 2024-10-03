
---

# 项目名称

> 使用 Spring Boot、Redis、JWT、Spring Security 等技术构建的后台管理系统

## 目录

- [简介](#简介)
- [功能](#功能)
- [技术栈](#技术栈)
- [项目结构](#项目结构)
- [安装与使用](#安装与使用)
- [API 接口](#api-接口)
- [开发](#开发)
- [许可证](#许可证)

## 简介

本项目是一个基于 Spring Boot 的后台管理系统，提供用户认证、权限管理、数据处理等功能。使用了 Spring Security 进行安全控制，JWT 进行用户身份验证，并通过 Redis 提高系统性能。

## 功能

- 用户管理：用户的增删改查
- 权限管理：基于角色的权限分配
- 登录认证：JWT 实现登录与身份验证
- 数据缓存：通过 Redis 提升查询速度
- 数据操作：使用 MyBatis 进行数据库操作

## 技术栈

- **后端框架**：Spring Boot
- **安全框架**：Spring Security, JWT
- **缓存**：Redis
- **数据库**：MySQL, MyBatis
- **前端**：Vue3 + Element Plus
- **工具**：Lombok, Maven
- **API 文档**：Postman

## 项目结构

## 项目结构

```bash
src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─ssm
│  │  │          ├─announcement        # 公告模块
│  │  │          │  ├─controller       # 控制器
│  │  │          │  ├─dto              # 数据传输对象
│  │  │          │  ├─mapper           # 数据库映射接口
│  │  │          │  ├─po               # 持久化对象
│  │  │          │  ├─service          # 业务逻辑层
│  │  │          │  │  └─impl          # 业务逻辑实现
│  │  │          │  └─vo               # 视图对象
│  │  │          ├─common              # 通用模块
│  │  │          │  ├─config           # 配置类
│  │  │          │  ├─exception        # 异常处理
│  │  │          │  ├─filter           # 过滤器
│  │  │          │  ├─global           # 全局配置
│  │  │          │  ├─handler          # 处理器
│  │  │          │  ├─interceptor      # 拦截器
│  │  │          │  ├─service          # 通用服务
│  │  │          │  └─util             # 工具类
│  │  │          ├─competition         # 比赛模块
│  │  │          │  ├─controller
│  │  │          │  ├─dto
│  │  │          │  ├─mapper
│  │  │          │  ├─po
│  │  │          │  ├─service
│  │  │          │  │  └─impl
│  │  │          │  └─vo
│  │  │          ├─register            # 注册模块
│  │  │          │  ├─controller
│  │  │          │  ├─dto
│  │  │          │  ├─mapper
│  │  │          │  ├─po
│  │  │          │  ├─service
│  │  │          │  │  └─impl
│  │  │          │  └─vo
│  │  │          ├─score               # 评分模块
│  │  │          │  ├─controller
│  │  │          │  ├─dto
│  │  │          │  ├─mapper
│  │  │          │  ├─po
│  │  │          │  ├─service
│  │  │          │  │  └─impl
│  │  │          │  └─vo
│  │  │          └─user                # 用户模块
│  │  │              ├─controller
│  │  │              ├─dto
│  │  │              ├─mapper
│  │  │              ├─po
│  │  │              ├─service
│  │  │              │  └─impl
│  │  │              └─vo
│  │  └─resources
│  │      ├─mapper                     # MyBatis 映射文件
│  │      │  ├─announcement
│  │      │  ├─competition
│  │      │  ├─register
│  │      │  ├─score
│  │      │  └─user
│  │      └─sql                        # SQL 脚本
│  └─test
│      └─java                          # 测试代码
```

## 安装与使用

### 1. 环境要求

- Java 8 或以上版本
- Maven 3.x
- MySQL
- Redis

### 2. 克隆代码

```bash
git clone https://your-repo-url.git
cd your-project-name
```

### 3. 配置数据库

在 `src/main/resources/application.yml` 中配置 MySQL 数据库和 Redis 连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_database_name
    username: your_db_username
    password: your_db_password
  redis:
    host: localhost
    port: 6379
jwt:
  secret: your_jwt_secret_key
```

### 4. 初始化数据库

运行 `src/resources/sql/` 目录下的 SQL 脚本以初始化数据库。

### 5. 启动项目

在项目根目录运行以下命令启动 Spring Boot 应用：

```bash
mvn spring-boot:run
```

服务启动后，访问 [http://localhost:8080](http://localhost:8080)。

### 6. API 文档

你可以使用 Postman 进行 API 测试。部分关键接口包括：

- **登录**: `POST /api/auth/login`
- **获取用户信息**: `GET /api/user/{id}`
- **创建用户**: `POST /api/user`
- **删除用户**: `DELETE /api/user/{id}`

### 7. 测试

运行以下命令执行测试：

```bash
mvn test
```

## API 接口

### 登录

- **URL**: `/api/auth/login`
- **方法**: `POST`
- **参数**:
    - `username`: 用户名
    - `password`: 密码
- **响应**:
    - 成功：返回 JWT Token
    - 失败：返回错误信息

### 获取用户信息

- **URL**: `/api/user/{id}`
- **方法**: `GET`
- **参数**: `id` 用户 ID
- **响应**: 用户的详细信息

### 更多 API 接口

您可以通过 Postman 导入项目中的 API 文档，了解所有可用接口。