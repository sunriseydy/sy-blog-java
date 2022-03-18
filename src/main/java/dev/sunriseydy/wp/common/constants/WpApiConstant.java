package dev.sunriseydy.wp.common.constants;

/**
 * @author SunriseYDY
 * @date 2022-03-16 10:48
 */
public interface WpApiConstant {
    /**
     * wordpress api route prefix
     */
    String API_PREFIX = "/wp-json";

    /**
     * posts api location
     */
    String API_POST = "/wp/v2/posts";

    String API_DETAIL = "/{id}";
}
