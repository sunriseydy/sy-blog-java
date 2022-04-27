package dev.sunriseydy.blog.page.domain.repository;

import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import dev.sunriseydy.blog.page.api.dto.PageDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-27 10:11
 */
@CacheConfig(cacheNames = BlogCacheConstant.CACHE_NAME_PAGES)
public interface PageRepository {

    List<Long> getPageIdList();

    @Cacheable(key = "#id")
    PageDTO getPageById(Long id);

    @CachePut(key = "#id")
    PageDTO updatePageById(Long id);

    @CacheEvict(key = "#id")
    void deletePageById(Long id);
}
