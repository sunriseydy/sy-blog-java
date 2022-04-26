package dev.sunriseydy.blog.common.constants;

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

    /**
     * tag api location
     */
    String API_TAG = "/wp/v2/tags";

    /**
     * user api location
     */
    String API_USER = "/wp/v2/users";

    String API_DETAIL = "/{id}";
}
