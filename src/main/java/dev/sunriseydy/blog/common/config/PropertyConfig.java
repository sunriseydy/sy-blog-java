package dev.sunriseydy.blog.common.config;

import dev.sunriseydy.blog.common.properties.SyBlogProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author SunriseYDY
 * @date 2022-03-04 17:36
 */
@Configuration
@EnableConfigurationProperties({SyBlogProperties.class})
public class PropertyConfig {
}
