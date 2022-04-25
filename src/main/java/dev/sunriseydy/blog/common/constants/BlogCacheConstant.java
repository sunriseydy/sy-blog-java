package dev.sunriseydy.blog.common.constants;

import org.springframework.data.redis.cache.CacheKeyPrefix;

/**
 * @author SunriseYDY
 * @date 2022-03-18 15:14
 */
public interface BlogCacheConstant {

    String CACHE_NAME_POSTS = "posts";

    String CACHE_NAME_CATEGORIES = "categories";

    String CACHE_KEY_PREFIX = "sy-wp:cache:";

    /**
     * 通过 cacheName 获取 cacheKey
     * @param cacheName
     * @return cacheKey
     */
    static String getCacheKey(String cacheName) {
        return BlogCacheConstant.CACHE_KEY_PREFIX + cacheName + CacheKeyPrefix.SEPARATOR;
    }
}
