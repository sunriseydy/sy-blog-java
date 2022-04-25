package dev.sunriseydy.blog.common.properties;

import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * @author SunriseYDY
 * @date 2022-03-04 17:34
 */
@ConfigurationProperties(prefix = "sy.blog")
@Validated
@Data
public class SyBlogProperties {

    /**
     * 数据来源类型
     * <li>
     *     wp-api:通过 wordpress rest api,{@link BlogSourceTypeConstant#SOURCE_TYPE_WP_API}
     * </li>
     * <li>
     *     wp-db:通过 wordpress database,{@link BlogSourceTypeConstant#SOURCE_TYPE_WP_DB}
     * </li>
     * <li>
     *     tp-api:通过 typecho rest api,{@link BlogSourceTypeConstant#SOURCE_TYPE_TP_API}
     * </li>
     * <li>
     *     tp-db:通过 typecho database,{@link BlogSourceTypeConstant#SOURCE_TYPE_TP_DB}
     * </li>
     * <p>
     *     默认是 wp-api
     * </p>
     */
    private String sourceType = BlogSourceTypeConstant.SOURCE_TYPE_WP_API;

    /**
     * rest api host, see <a href="https://developer.wordpress.org/rest-api/">WordPress REST API Handbook</a>
     */
    @URL
    private String restApiHost;

    /**
     * wordpress username
     */
    private String username;

    /**
     * wordpress password
     */
    private String password;
}
