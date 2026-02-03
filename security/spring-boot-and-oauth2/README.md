# Spring Boot OAuth2 登录示例

基于 Spring 官方教程：https://spring.io/guides/tutorials/spring-boot-oauth2

## 功能特性

✅ GitHub OAuth2 登录  
✅ Google OAuth2 登录（需配置）  
✅ 显示登录用户名  
✅ 登出功能  
✅ CSRF 保护  
✅ 错误处理  
✅ 可选的 GitHub 组织验证

## 快速开始

### 1. 配置 GitHub OAuth App

1. 访问 https://github.com/settings/developers
2. 点击 "New OAuth App"
3. 填写信息：
   - Application name: `My OAuth2 App`
   - Homepage URL: `http://localhost:8080`
   - Authorization callback URL: `http://localhost:8080/login/oauth2/code/github`
4. 获取 Client ID 和 Client Secret
5. 更新 `application.yml` 中的配置

### 2. 配置 Google OAuth（可选）

1. 访问 https://console.developers.google.com/
2. 创建新项目
3. 启用 Google+ API
4. 创建 OAuth 2.0 凭据
5. 添加授权重定向 URI: `http://localhost:8080/login/oauth2/code/google`
6. 获取 Client ID 和 Client Secret
7. 更新 `application.yml` 中的 Google 配置

### 3. 运行应用

```bash
# 构建项目
./gradlew build

# 运行应用
./gradlew bootRun

# 或者运行打包后的 jar
java -jar build/libs/*.jar
```

### 4. 访问应用

打开浏览器访问：http://localhost:8080

## 配置说明

### application.yml

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          github:
            clientId: your-github-client-id
            clientSecret: your-github-client-secret
          google:
            client-id: your-google-client-id
            client-secret: your-google-client-secret
```

### 高级功能：GitHub 组织验证

如果你想限制只有特定 GitHub 组织的成员才能登录，可以启用 `CustomOAuth2UserService`。

在 `OAuth2ClientApplication.java` 中添加以下代码：

```java
@Bean
public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService(WebClient rest) {
    return new CustomOAuth2UserService(rest, "your-organization-name");
}
```

将 `"your-organization-name"` 替换为你的 GitHub 组织名称。

## 项目结构

```
src/
├── main/
│   ├── java/
│   │   └── com/newzhxu/oauth2client/
│   │       ├── OAuth2ClientApplication.java      # 主应用类
│   │       └── CustomOAuth2UserService.java      # 自定义用户服务（可选）
│   └── resources/
│       ├── static/
│       │   └── index.html                        # 前端页面
│       └── application.yml                       # 配置文件
```

## 主要端点

- `/` - 首页（公开访问）
- `/user` - 获取当前登录用户信息（需认证）
- `/error` - 错误信息端点
- `/logout` - 登出端点
- `/oauth2/authorization/github` - GitHub 登录
- `/oauth2/authorization/google` - Google 登录

## 安全注意事项

⚠️ **重要：**
- 不要将 Client ID 和 Client Secret 提交到公开的代码仓库
- 生产环境请使用环境变量或配置服务器来管理敏感信息
- 只在 localhost 开发环境使用当前配置
- 部署到其他域名时，需要在 OAuth 提供商处重新配置回调 URL

## 依赖说明

- Spring Boot Starter Web - Web 应用支持
- Spring Boot Starter Security - 安全框架
- Spring Boot Starter OAuth2 Client - OAuth2 客户端支持
- Spring Boot Starter WebFlux - WebClient 支持（用于调用 API）
- Webjars (jQuery, Bootstrap, js-cookie) - 前端依赖

## 参考资料

- [Spring Boot OAuth2 官方教程](https://spring.io/guides/tutorials/spring-boot-oauth2)
- [Spring Security OAuth2 文档](https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html)
- [GitHub OAuth Apps](https://docs.github.com/en/developers/apps/building-oauth-apps)
- [Google OAuth 2.0](https://developers.google.com/identity/protocols/oauth2)
