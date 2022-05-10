package dev.sunriseydy.blog.common.constants;

import org.springframework.data.redis.cache.CacheKeyPrefix;

/**
 * @author SunriseYDY
 * @date 2022-03-18 15:14
 */
public interface BlogCacheConstant {

    String CACHE_NAME_POSTS = "posts";

    String CACHE_NAME_CATEGORIES = "categories";

    String CACHE_NAME_TAGS = "tags";

    String CACHE_NAME_USERS = "users";

    String CACHE_NAME_PAGES = "pages";

    String CACHE_NAME_MENUS = "menus";

    String CACHE_NAME_MENU_ITEMS = "menu-items";

    String CACHE_KEY_POST_META = BlogCacheConstant.CACHE_KEY_PREFIX + "post-meta";

    String CACHE_KEY_PREFIX = "sy-blog:cache:";

    /**
     * 通过 cacheName 获取 cacheKey
     * @param cacheName
     * @return cacheKey
     */
    static String getCacheKey(String cacheName) {
        return BlogCacheConstant.CACHE_KEY_PREFIX + cacheName + CacheKeyPrefix.SEPARATOR;
    }
}
