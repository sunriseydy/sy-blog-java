package dev.sunriseydy.wp.post.domain.repository;

import dev.sunriseydy.wp.common.constants.WpCacheConstant;
import dev.sunriseydy.wp.post.api.dto.PostDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-03-17 14:06
 */
@CacheConfig(cacheNames = WpCacheConstant.CACHE_NAME_POSTS)
public interface PostRepository {

    List<Long> getPostIdList();

    @Cacheable(key = "#id")
    PostDTO getPostById(Long id);

    @CachePut(key = "#id")
    PostDTO updatePostById(Long id);

    @CacheEvict(key = "#id")
    void deletePostById(Long id);
}
