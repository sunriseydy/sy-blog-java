package dev.sunriseydy.blog.site.domain.repository;

import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import dev.sunriseydy.blog.site.api.dto.SiteInfoDTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author SunriseYDY
 * @date 2022-05-12 15:56
 */
@CacheConfig(cacheNames = BlogCacheConstant.CACHE_NAME_SITE_INFO)
public interface SiteInfoRepository {

    @Cacheable
    SiteInfoDTO getSiteInfo();

    @CachePut
    SiteInfoDTO updateSiteInfo();

    @CacheEvict
    void deleteSiteInfo();
}
