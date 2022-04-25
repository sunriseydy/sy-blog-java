package dev.sunriseydy.blog.common.constants;

/**
 * @author SunriseYDY
 * @date 2022-03-09 10:56
 */
public interface BlogSourceTypeConstant {

    /**
     * 配置名
     */
    String PROPERTY_NAME = "sy.blog.source-type";

    /**
     * 数据来源类型 - wp-api
     */
    String SOURCE_TYPE_WP_API = "wp-api";

    /**
     * 数据来源类型 - wp-db
     */
    String SOURCE_TYPE_WP_DB = "wp-db";

    /**
     * 数据来源类型 - tp-api
     */
    String SOURCE_TYPE_TP_API = "tp-api";

    /**
     * 数据来源类型 - tp-db
     */
    String SOURCE_TYPE_TP_DB = "tp-db";
}
