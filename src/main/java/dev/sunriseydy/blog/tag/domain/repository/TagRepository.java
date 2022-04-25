package dev.sunriseydy.blog.tag.domain.repository;

import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import dev.sunriseydy.blog.tag.api.dto.TagDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-07 15:23
 */
@CacheConfig(cacheNames = BlogCacheConstant.CACHE_NAME_TAGS)
public interface TagRepository {
    List<Long> getTagIdList();

    @Cacheable(key = "#id")
    TagDTO getTagById(Long id);

    @CachePut(key = "#id")
    TagDTO updateTagById(Long id);

    @CacheEvict(key = "#id")
    void deleteTagById(Long id);
}
