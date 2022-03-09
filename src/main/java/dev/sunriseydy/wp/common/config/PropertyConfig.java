package dev.sunriseydy.wp.common.config;

import dev.sunriseydy.wp.common.properties.SyWpProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author SunriseYDY
 * @date 2022-03-04 17:36
 */
@Configuration
@EnableConfigurationProperties({SyWpProperties.class})
public class PropertyConfig {
}
