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

    /**
     * category api location
     */
    String API_CATEGORY = "/wp/v2/categories";

    String API_DETAIL = "/{id}";
}
