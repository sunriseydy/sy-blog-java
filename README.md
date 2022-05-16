# SY-BLOG-JAVA

`sy-blog-java` 是一个通过 REST API 或者 database 将 WordPress 或 Typecho 数据缓存到 Redis 中然后再通过 API 暴露给前端应用的 Spring Boot 程序，可以实现接口的缓存全覆盖。
对应的前端应用：[sy-blog-vue](https://github.com/sunriseydy/sy-blog-vue)

## 预览

[https://blog.sunriseydy.dev/](https://blog.sunriseydy.dev/)

## 运行

首先克隆本项目：

```bash
git clone https://github.com/sunriseydy/sy-blog-java.git
cd sy-blog-java
```

然后在项目根目录下新建 `.env.local` 文件,内容是自定义的环境变量配置，例如：

```bash
export SPRING_PROFILES_ACTIVE=default

export BLOG_INIT_CACHE=false
export WP_SOURCE_TYPE=tp-db

export WP_PASSWORD=1
export WP_USERNAME=1

export SPRING_TYPECHO_DATASOURCE_URL='jdbc:mysql://localhost:3306/typecho?useUnicode=true&characterEncoding=utf-8&useSSL=false&useTimezone=true&serverTimezone=Asia/Shanghai'
export SPRING_TYPECHO_DATASOURCE_USERNAME=root
export SPRING_TYPECHO_DATASOURCE_PASSWORD=root

export SPRING_REDIS_HOST='localhost'
export SPRING_REDIS_PORT=6379
export SPRING_REDIS_DATABASE=2
```

然后在终端中运行项目根目录下的 `run.sh` 脚本：

```bash
./run.sh
```

执行后，脚本会拉取代码更新，然后使用 `mvn` 构建，然后运行 `target/sy-blog-java-0.0.1-SNAPSHOT.jar`。如果想要直接启动已有的 jar 包，可以加上 `-l` 参数，例如 `./run.sh -l` 即可。
如果 `SPRING_PROFILES_ACTIVE=default` ，应用的日志会直接打印到终端上，否则会输出到 `logs/sy-blog-java/all_sy-blog-java.log` 文件中。

应用监听的端口是 `8080`

swagger ui 地址为 `/swagger-ui/index.html`

## 数据源类型

`sy-blog-java` 目前支持的数据来源类型有：

|类型|含义|描述|
|-|-|-|
|`wp-api`|WordPress API|从 WordPress 的 API 获取数据。|
|`wp-db`|WordPress Database|从 WordPress 的数据库中获取数据。 ***暂未实现， TODO***|
|`tp-api`|Typecho API|从 Typecho 的 API 获取数据。 ***由于 Typecho 暂时没有官方 API ，因此该功能暂未实现***|
|`tp-db`|Typecho Database|从 Typecho 的数据库中获取数据|

目前支持最好的是 `tp-db`，也就是我博客的实现方案。

`wp-api` 在早期开发中有实现，但后期主要精力放到了 `tp-db` 的实现上，因此功能可能有些不完善，之后会完善。

`wp-db` 将会在完善了 `wp-api` 之后实现。

`tp-api` 由于 Typecho 目前官方没有 API ，因此要实现的话可能要自己写一个 typecho 插件，工作量有点大，所以放在最后实现。

## 配置

所有的配置在 `src/main/resources/application.yml` 中。

### 基础配置

```yml
sy:
  blog:
    # 数据来源类型
    source-type: ${BLOG_SOURCE_TYPE:tp-db}
    # API 类型数据源的 API Host
    rest-api-host: ${WP_REST_API_HOST:https://xn--ghq2ewa7745eca.xn--6qq986b3xl}
    # 用户名，用于访问本应用未公开的接口以及未公开的数据源 API
    username: ${WP_USERNAME:}
    # 密码，用于访问本应用未公开的接口以及未公开的数据源 API
    password: ${WP_PASSWORD:}
    # 是否在启动时自动初始化缓存，默认为 true
    init-cache: ${BLOG_INIT_CACHE:true}
```

### `tp-db` 配置

数据源类型为 `tp-db` 的配置如下：

1. 环境变量 `BLOG_SOURCE_TYPE` = `tp-db` 或者 `sy.blog.source-type=tp-db`

2. 启用数据库多数据源： `spring.datasource.dynamic.enabled=true`

3. 配置主数据源为 `typecho` : `spring.datasource.dynamic.primary=typecho`

4. 注释掉 `spring.datasource.dynamic.datasource.wordpress` 数据源：

    ```yml
    spring:
      datasource:
        dynamic:
          datasource:
    #        wordpress:
    #          url: ${SPRING_WORDPRESS_DATASOURCE_URL:jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf-8&useSSL=false&useTimezone=true&serverTimezone=Asia/Shanghai}
    #          username: ${SPRING_WORDPRESS_DATASOURCE_USERNAME:root}
    #          password: ${SPRING_WORDPRESS_DATASOURCE_PASSWORD:root}
    ```

5. 启用并配置 `spring.datasource.dynamic.datasource.typecho` 数据源，例如：

    ```yml
    spring:
      datasource:
        dynamic:
          datasource:
            typecho:
              driver-class-name: com.mysql.cj.jdbc.Driver
              url: ${SPRING_TYPECHO_DATASOURCE_URL:jdbc:mysql://localhost:3306/typecho?useUnicode=true&characterEncoding=utf-8&useSSL=false&useTimezone=true&serverTimezone=Asia/Shanghai}
              username: ${SPRING_TYPECHO_DATASOURCE_USERNAME:root}
              password: ${SPRING_TYPECHO_DATASOURCE_PASSWORD:root}
    ```

    也可以使用以下环境变量来配置 typecho 数据库数据源。

    ```shell
    SPRING_TYPECHO_DATASOURCE_URL
    SPRING_TYPECHO_DATASOURCE_USERNAME
    SPRING_TYPECHO_DATASOURCE_PASSWORD
    ```

### `wp-api` 配置

数据源类型为 `wp-api` 的配置如下：

1. 环境变量 `BLOG_SOURCE_TYPE` = `wp-api` 或者 `sy.blog.source-type=wp-api`

2. 禁用数据库多数据源： `spring.datasource.dynamic.enabled=false`

3. 设置 WordPress API HOST，例如: `sy.blog.rest-api-host=https://xn--ghq2ewa7745eca.xn--6qq986b3xl`。
    可参考 [WordPress REST API Handbook](https://developer.wordpress.org/rest-api/)