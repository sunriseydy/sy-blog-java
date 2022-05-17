package dev.sunriseydy.blog.post.app.service.impl;

import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import dev.sunriseydy.blog.common.exception.CommonException;
import dev.sunriseydy.blog.common.vo.PageVO;
import dev.sunriseydy.blog.post.api.dto.PostDTO;
import dev.sunriseydy.blog.post.api.dto.PostMeta;
import dev.sunriseydy.blog.post.app.service.PostMetaService;
import dev.sunriseydy.blog.post.domain.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
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

    private final BoundZSetOperations<String, PostMeta> zSetOperations;

    public PostMetaServiceImpl(PostRepository postRepository, RedisTemplate<String, PostMeta> redisTemplate) {
        this.postRepository = postRepository;
        this.redisTemplate = redisTemplate;
        zSetOperations = redisTemplate.boundZSetOps(BlogCacheConstant.CACHE_KEY_POST_META);
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
            this.zSetOperations.add(collect);
        }
    }

    @Override
    public void updatePostMetaCache(PostDTO postDTO) {
        PostMeta postMeta = postDTO.toPostMeta();
        this.zSetOperations.add(postMeta, postMeta.getDate().getTime());
    }

    @Override
    public Set<PostMeta> getAllPostMetas() {
        return this.zSetOperations.reverseRangeByScore(0, System.currentTimeMillis());
    }

    @Override
    public Long getPostIdBySlug(String slug) {
        return this.getAllPostMetas()
                .stream()
                .filter(postMeta -> slug.equals(postMeta.getSlug()))
                .map(PostMeta::getId)
                .findFirst()
                .orElseThrow(() -> new CommonException("资源不存在"));
    }

    @Override
    public PageVO<PostMeta> getPostMetasByPage(long page, long pageSize) {
        Assert.isTrue(pageSize > 0, "pageSize 必须大于 0");
        Assert.isTrue(page >= 0, "page 必须大于等于 0");

        long total = this.zSetOperations.size();
        long totalPages = (total % pageSize) == 0 ? (total / pageSize) : (total / pageSize + 1);
        long from = page * pageSize;
        long to = (page + 1) * pageSize - 1;
        if (to >= total) {
            to = (total - 1);
        }

        return PageVO.<PostMeta>builder()
                .page(page)
                .pageSize(pageSize)
                .total(total)
                .totalPages(totalPages)
                .content(this.zSetOperations.reverseRange(from, to))
                .build();
    }

    @Override
    public List<PostMeta> getPostMetasByCategoryId(Long id) {
        return this.getAllPostMetas().stream()
                .filter(postMeta -> postMeta.getCategories().contains(id))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostMeta> getPostMetasByTagId(Long id) {
        return this.getAllPostMetas().stream()
                .filter(postMeta -> postMeta.getTags().contains(id))
                .collect(Collectors.toList());
    }
}
