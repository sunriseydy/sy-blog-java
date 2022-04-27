package dev.sunriseydy.blog.post.infra.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sunriseydy.blog.category.domain.repository.CategoryRepository;
import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import dev.sunriseydy.blog.common.constants.WpApiConstant;
import dev.sunriseydy.blog.common.properties.SyBlogProperties;
import dev.sunriseydy.blog.common.utils.WpApiRequestUtil;
import dev.sunriseydy.blog.common.vo.WpApiGlobalRequestParamVO;
import dev.sunriseydy.blog.post.api.dto.PostDTO;
import dev.sunriseydy.blog.post.domain.repository.PostRepository;
import dev.sunriseydy.blog.post.domain.vo.WpApiPostVO;
import dev.sunriseydy.blog.tag.domain.repository.TagRepository;
import dev.sunriseydy.blog.user.domain.repository.UserRepository;
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
@ConditionalOnProperty(name = BlogSourceTypeConstant.PROPERTY_NAME, havingValue = BlogSourceTypeConstant.SOURCE_TYPE_WP_API)
@Slf4j
public class WpApiPostRepositoryImpl implements PostRepository {

    private final SyBlogProperties wpProperties;

    private final RestTemplate restTemplate;

    private final RedisTemplate<String, Object> redisTemplate;

    private final CategoryRepository categoryRepository;

    private final TagRepository tagRepository;

    private final UserRepository userRepository;

    private final String postApiUri;

    private final String postDetailApiUri;

    public WpApiPostRepositoryImpl(SyBlogProperties wpProperties, RestTemplateBuilder restTemplateBuilder, RedisTemplate<String, Object> redisTemplate, CategoryRepository categoryRepository, TagRepository tagRepository, UserRepository userRepository) {
        this.wpProperties = wpProperties;
        this.restTemplate = restTemplateBuilder.build();
        this.postApiUri = wpProperties.getRestApiHost() + WpApiConstant.API_PREFIX + WpApiConstant.API_POST;
        this.postDetailApiUri = this.postApiUri + WpApiConstant.API_DETAIL;
        this.redisTemplate = redisTemplate;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
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
                .embed("wp:featuredmedia")
                .build();
        WpApiPostVO postVO = WpApiRequestUtil.getForObjectById(postDetailApiUri + WpApiRequestUtil.generateQueryParma(globalRequestParam),
                id,
                restTemplate,
                WpApiPostVO.class);

        return postVO.toPostDto();
    }

    @Override
    public PostDTO updatePostById(Long id) {
        log.info("update this post:{}", id);
        return this.getPostById(id);
    }

    @Override
    public void deletePostById(Long id) {
        String key = BlogCacheConstant.getCacheKey(BlogCacheConstant.CACHE_NAME_POSTS) + id;
        Object post = redisTemplate.opsForValue().get(key);
        log.info("delete this post:{},{}", id, post );
    }
}
