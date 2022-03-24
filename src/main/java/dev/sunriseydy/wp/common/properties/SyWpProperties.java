package dev.sunriseydy.wp.common.properties;

import dev.sunriseydy.wp.common.constants.WpSourceTypeConstant;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * @author SunriseYDY
 * @date 2022-03-04 17:34
 */
@ConfigurationProperties(prefix = "sy.wp")
@Validated
@Data
public class SyWpProperties {

    /**
     * 数据来源类型
     * <li>
     *     api:通过 wordpress rest api,{@link dev.sunriseydy.wp.common.constants.WpSourceTypeConstant#SOURCE_TYPE_API}
     * </li>
     * <li>
     *     db:通过 wordpress database,{@link dev.sunriseydy.wp.common.constants.WpSourceTypeConstant#SOURCE_TYPE_DB}
     * </li>
     * <p>
     *     默认是 api
     * </p>
     */
    private String sourceType = WpSourceTypeConstant.SOURCE_TYPE_API;

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
