package dev.sunriseydy.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableCaching
@MapperScan("dev.sunriseydy.blog.**.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SyBlogJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyBlogJavaApplication.class, args);
    }

}
