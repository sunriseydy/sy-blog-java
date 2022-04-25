package dev.sunriseydy.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableCaching
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class SyBlogJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyBlogJavaApplication.class, args);
    }

}
