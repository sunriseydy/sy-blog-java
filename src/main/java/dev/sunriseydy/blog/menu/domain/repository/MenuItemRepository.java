package dev.sunriseydy.blog.menu.domain.repository;

import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import dev.sunriseydy.blog.menu.api.dto.MenuItemDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-27 17:09
 */
@CacheConfig(cacheNames = BlogCacheConstant.CACHE_NAME_MENU_ITEMS)
public interface MenuItemRepository {
    List<Long> getMenuItemIdList();

    @Cacheable(key = "#id")
    MenuItemDTO getMenuItemById(Long id);

    @CachePut(key = "#id")
    MenuItemDTO updateMenuItemById(Long id);

    @CacheEvict(key = "#id")
    void deleteMenuItemById(Long id);
}
