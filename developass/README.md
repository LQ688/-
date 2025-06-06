# 客户联系人管理系统

## 项目简介
本系统是一个基于 Spring Boot、Thymeleaf、MyBatis-Plus 和 Bootstrap 开发的客户联系人管理平台。支持联系人信息的增删改查、分组管理、分页、搜索、导出Excel、登录与记住密码等功能，界面美观，操作便捷，适合学习和演示企业级Web开发流程。

## 如何运行
1. **准备数据库**：
   - 创建名为 `contact_db` 的数据库，并执行 `create_db.sql` 脚本初始化表结构。
2. **配置数据库连接**：
   - 在 `src/main/resources/application.yml` 中确认数据库用户名和密码配置正确。
3. **启动项目**：
   - 在项目根目录下执行：
     ```bash
     mvn spring-boot:run
     ```
   - 启动后访问 [http://localhost:8080](http://localhost:8080) 即可使用系统。

如有问题请联系开发者或查阅项目文档。

## 2. 系统截图

### 联系人列表页面
![list](screenshots/list.png)

### 添加联系人页面
![add](screenshots/add.png)

> 注：请将实际运行时的页面截图保存到 `screenshots` 文件夹，并命名为 `list.png` 和 `add.png`。

## 3. 技术栈说明

- **Spring Boot**：后端开发框架，简化Java Web开发
- **Thymeleaf**：前端模板引擎，页面渲染
- **MyBatis-Plus**：数据库操作框架，简化CRUD
- **MySQL**：关系型数据库，存储联系人数据
- **Bootstrap**：前端UI框架，美化页面
- **Lombok**：简化Java实体类代码

## 4. 国内访问慢的解决办法

如果你在国内，访问CDN加载Bootstrap或FontAwesome样式很慢，可以将页面中的CDN链接替换为BootCDN：

```html
<!-- Bootstrap CSS -->
<link href="https://cdn.bootcdn.net/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
<!-- FontAwesome -->
<link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/font-awesome/6.4.0/css/all.min.css">
```

将上述代码替换到 `index.html`、`list.html` 等页面的 `<head>` 部分即可。

---
如有问题，欢迎随时提问！ 