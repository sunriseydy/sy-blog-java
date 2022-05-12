package dev.sunriseydy.blog.menu.domain.repository;

import dev.sunriseydy.blog.menu.api.dto.Menu;
import dev.sunriseydy.blog.menu.api.dto.MenuDTO;
import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-27 17:09
 */
@CacheConfig(cacheNames = BlogCacheConstant.CACHE_NAME_MENUS)
public interface MenuRepository {
    List<Long> getMenuIdList();

    @Cacheable(key = "#id")
    MenuDTO getMenuById(Long id);

    @CachePut(key = "#id")
    MenuDTO updateMenuById(Long id);

    @CacheEvict(key = "#id")
    void deleteMenuById(Long id);

    @Cacheable(key = "#name")
    List<Menu> getMenusByName(String name);
}
