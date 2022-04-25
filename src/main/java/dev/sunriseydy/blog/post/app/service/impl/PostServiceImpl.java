package dev.sunriseydy.blog.post.app.service.impl;

import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import dev.sunriseydy.blog.common.utils.PageUtil;
import dev.sunriseydy.blog.common.vo.PageVO;
import dev.sunriseydy.blog.post.api.dto.PostDTO;
import dev.sunriseydy.blog.post.app.service.PostService;
import dev.sunriseydy.blog.post.domain.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author SunriseYDY
 * @date 2022-03-21 11:46
 */
@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Long> getPostIdList() {
        return postRepository.getPostIdList();
    }

    @Override
    public List<PostDTO> getPostList() {
        Set<String> keys = redisTemplate.keys(BlogCacheConstant.getCacheKey(BlogCacheConstant.CACHE_NAME_POSTS) + "*");
        return keys != null ? keys.stream()
                .map(s -> postRepository.getPostById(Long.parseLong(s.replace(BlogCacheConstant.getCacheKey(BlogCacheConstant.CACHE_NAME_POSTS), ""))).clearContent())
                .sorted(Comparator.comparing(PostDTO::getDate).reversed())
                .collect(Collectors.toList()) : Collections.emptyList();
    }

    @Override
    public PageVO<PostDTO> getPostPage(int page, int pageSize) {
        return PageUtil.doPage(page, pageSize, this.getPostList());
    }

    @Override
    public PostDTO getPostById(Long id) {
        return postRepository.getPostById(id);
    }

    @Override
    public PostDTO updatePostById(Long id) {
        return postRepository.updatePostById(id);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deletePostById(id);
    }
}
