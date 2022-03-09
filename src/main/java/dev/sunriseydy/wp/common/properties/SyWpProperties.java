package dev.sunriseydy.wp.common.properties;

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
     * 数据来源类型 - api
     */
    public static final String SOURCE_TYPE_API = "api";

    /**
     * 数据来源类型 - db
     */
    public static final String SOURCE_TYPE_DB = "db";

    /**
     * 数据来源类型
     * <li>
     *     api:通过 wordpress rest api
     * </li>
     * <li>
     *     db:通过 wordpress database
     * </li>
     * <p>
     *     默认是 api
     * </p>
     */
    private String sourceType = SOURCE_TYPE_API;

    /**
     * resp
     */
    @URL
    private String restApiHost;
}
