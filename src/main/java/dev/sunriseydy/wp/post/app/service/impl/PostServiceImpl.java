package dev.sunriseydy.wp.post.app.service.impl;

import dev.sunriseydy.wp.common.utils.PageUtil;
import dev.sunriseydy.wp.common.vo.PageVO;
import dev.sunriseydy.wp.post.api.dto.PostDTO;
import dev.sunriseydy.wp.post.app.service.PostService;
import dev.sunriseydy.wp.post.domain.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static dev.sunriseydy.wp.common.constants.WpCacheConstant.CACHE_KEY_POSTS;

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
    public List<PostDTO> getPostIdList() {
        return postRepository.getPostIdList();
    }

    @Override
    public List<PostDTO> getPostList() {
        Set<String> keys = redisTemplate.keys(CACHE_KEY_POSTS + "*");
        return keys != null ? keys.stream()
                .map(s -> postRepository.getPostById(Long.parseLong(s.replace(CACHE_KEY_POSTS, ""))).clearContent())
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
