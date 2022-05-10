package dev.sunriseydy.blog.post.app.service.impl;

import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import dev.sunriseydy.blog.post.api.dto.PostDTO;
import dev.sunriseydy.blog.post.api.dto.PostMeta;
import dev.sunriseydy.blog.post.app.service.PostMetaService;
import dev.sunriseydy.blog.post.domain.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author SunriseYDY
 * @date 2022-05-10 15:13
 */
@Slf4j
@Service
public class PostMetaServiceImpl implements PostMetaService {
    private final PostRepository postRepository;

    private final RedisTemplate<String, PostMeta> redisTemplate;

    public PostMetaServiceImpl(PostRepository postRepository, RedisTemplate<String, PostMeta> redisTemplate) {
        this.postRepository = postRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void generatePostMetaCache() {
        // 先删掉已有的
        this.redisTemplate.delete(BlogCacheConstant.CACHE_KEY_POST_META);

        Set<String> keys = redisTemplate.keys(BlogCacheConstant.getCacheKey(BlogCacheConstant.CACHE_NAME_POSTS) + "*");
        if (CollectionUtils.isNotEmpty(keys)) {
            Set<ZSetOperations.TypedTuple<PostMeta>> collect = keys.stream()
                    .map(id ->
                            postRepository.getPostById(Long.parseLong(StringUtils.remove(id,
                                    BlogCacheConstant.getCacheKey(BlogCacheConstant.CACHE_NAME_POSTS)))))
                    .map(PostDTO::toPostMeta)
                    .map(postMeta -> ZSetOperations.TypedTuple.of(postMeta, (double) postMeta.getDate().getTime()))
                    .collect(Collectors.toSet());
            this.redisTemplate.boundZSetOps(BlogCacheConstant.CACHE_KEY_POST_META).add(collect);
        }
    }
}
