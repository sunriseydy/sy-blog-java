package dev.sunriseydy.blog.category.domain.repository;

import dev.sunriseydy.blog.category.api.dto.CategoryDTO;
import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-02 10:58
 */
@CacheConfig(cacheNames = BlogCacheConstant.CACHE_NAME_CATEGORIES)
public interface CategoryRepository {
    List<Long> getCategoryIdList();

    @Cacheable(key = "#id")
    CategoryDTO getCategoryById(Long id);

    @CachePut(key = "#id")
    CategoryDTO updateCategoryById(Long id);

    @CacheEvict(key = "#id")
    void deleteCategoryById(Long id);
}
