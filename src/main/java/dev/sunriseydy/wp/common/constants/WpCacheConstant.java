package dev.sunriseydy.wp.common.constants;

import org.springframework.data.redis.cache.CacheKeyPrefix;

/**
 * @author SunriseYDY
 * @date 2022-03-18 15:14
 */
public interface WpCacheConstant {

    String CACHE_NAME_POSTS = "posts";

    String CACHE_KEY_POSTS = WpCacheConstant.CACHE_KEY_PREFIX + WpCacheConstant.CACHE_NAME_POSTS + CacheKeyPrefix.SEPARATOR;

    String CACHE_KEY_PREFIX = "sy-wp:cache:";
}
