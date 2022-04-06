package dev.sunriseydy.wp.post.infra.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sunriseydy.wp.common.constants.WpApiConstant;
import dev.sunriseydy.wp.common.constants.WpCacheConstant;
import dev.sunriseydy.wp.common.constants.WpSourceTypeConstant;
import dev.sunriseydy.wp.common.interfaces.ProxySelf;
import dev.sunriseydy.wp.common.properties.SyWpProperties;
import dev.sunriseydy.wp.common.utils.WpApiRequestUtil;
import dev.sunriseydy.wp.common.vo.WpApiGlobalRequestParamVO;
import dev.sunriseydy.wp.post.api.dto.PostDTO;
import dev.sunriseydy.wp.post.domain.repository.PostRepository;
import dev.sunriseydy.wp.post.domain.vo.WpApiPostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-03-17 14:12
 */
@Repository
@ConditionalOnProperty(name = WpSourceTypeConstant.PROPERTY_NAME, havingValue = WpSourceTypeConstant.SOURCE_TYPE_API)
@Slf4j
public class WpApiPostRepositoryImpl implements PostRepository, ProxySelf<PostRepository> {

    private final SyWpProperties wpProperties;

    private final RestTemplate restTemplate;

    private final RedisTemplate<String, Object> redisTemplate;

    private final String postApiUri;

    private final String postDetailApiUri;

    public WpApiPostRepositoryImpl(SyWpProperties wpProperties, RestTemplateBuilder restTemplateBuilder, RedisTemplate<String, Object> redisTemplate) {
        this.wpProperties = wpProperties;
        this.restTemplate = restTemplateBuilder.build();
        this.postApiUri = wpProperties.getRestApiHost() + WpApiConstant.API_PREFIX + WpApiConstant.API_POST;
        this.postDetailApiUri = this.postApiUri + WpApiConstant.API_DETAIL;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Long> getPostIdList() {
        return WpApiRequestUtil.getAllItemsId(postApiUri, new TypeReference<List<WpApiPostVO>>() {}, restTemplate, WpApiPostVO::getId);
    }

    @Override
    public PostDTO getPostById(Long id) {
        log.info("get this post:{}", id);
        WpApiGlobalRequestParamVO globalRequestParam = WpApiGlobalRequestParamVO.builder()
                .envelope(Boolean.TRUE)
                .embed("wp:featuredmedia,wp:term,author")
                .build();
        return WpApiRequestUtil.getForObjectById(postDetailApiUri + WpApiRequestUtil.generateQueryParma(globalRequestParam),
                id,
                restTemplate,
                WpApiPostVO.class).toPostDto();
    }

    @Override
    public PostDTO updatePostById(Long id) {
        log.info("update this post:{}", id);
        return this.getPostById(id);
    }

    @Override
    public void deletePostById(Long id) {
        String key = WpCacheConstant.getCacheKey(WpCacheConstant.CACHE_NAME_POSTS) + String.valueOf(id);
        Object post = redisTemplate.opsForValue().get(key);
        log.info("delete this post:{},{}", id, post );
    }
}
