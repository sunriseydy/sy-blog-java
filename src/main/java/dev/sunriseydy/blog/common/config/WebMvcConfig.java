package dev.sunriseydy.blog.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author SunriseYDY
 * @date 2022-05-05 15:55
 */
@Configuration(proxyBeanMethods = false)
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-cors-global-java
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "PUT", "POST", "DELETE", "PATCH")
                .allowedHeaders("*")
                .exposedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
    }
}
