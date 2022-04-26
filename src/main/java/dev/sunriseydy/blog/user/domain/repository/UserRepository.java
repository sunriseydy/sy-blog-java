package dev.sunriseydy.blog.user.domain.repository;

import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import dev.sunriseydy.blog.user.api.dto.UserDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-26 16:51
 */
@CacheConfig(cacheNames = BlogCacheConstant.CACHE_NAME_USERS)
public interface UserRepository {

    List<Long> getUserIdList();

    @Cacheable(key = "#id")
    UserDTO getUserById(Long id);

    @CachePut(key = "#id")
    UserDTO updateUserById(Long id);

    @CacheEvict(key = "#id")
    void deleteUserById(Long id);
}
