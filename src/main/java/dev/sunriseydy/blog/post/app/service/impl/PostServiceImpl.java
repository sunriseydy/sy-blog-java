package dev.sunriseydy.blog.post.app.service.impl;

import dev.sunriseydy.blog.category.api.dto.CategoryDTO;
import dev.sunriseydy.blog.category.domain.repository.CategoryRepository;
import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import dev.sunriseydy.blog.common.utils.PageUtil;
import dev.sunriseydy.blog.common.vo.PageVO;
import dev.sunriseydy.blog.post.api.dto.PostDTO;
import dev.sunriseydy.blog.post.app.service.PostService;
import dev.sunriseydy.blog.post.domain.repository.PostRepository;
import dev.sunriseydy.blog.tag.api.dto.TagDTO;
import dev.sunriseydy.blog.tag.domain.repository.TagRepository;
import dev.sunriseydy.blog.user.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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

    private final PostRepository postRepository;

    private final RedisTemplate<String, Object> redisTemplate;

    private final CategoryRepository categoryRepository;

    private final TagRepository tagRepository;

    private final UserRepository userRepository;

    public PostServiceImpl(CategoryRepository categoryRepository, TagRepository tagRepository, UserRepository userRepository, PostRepository postRepository, RedisTemplate<String, Object> redisTemplate) {
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.redisTemplate = redisTemplate;
    }

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
        PostDTO postDTO = postRepository.getPostById(id);
        // 获取 category
        if (CollectionUtils.isNotEmpty(postDTO.getCategories())) {
            List<CategoryDTO> categories = postDTO.getCategories()
                    .stream()
                    .map(categoryRepository::getCategoryById)
                    .collect(Collectors.toList());
            postDTO.setCategoriesList(categories);
        }
        // 获取 tag
        if (CollectionUtils.isNotEmpty(postDTO.getTags())) {
            List<TagDTO> tags = postDTO.getTags()
                    .stream()
                    .map(tagRepository::getTagById)
                    .collect(Collectors.toList());
            postDTO.setTagsList(tags);
        }
        // 获取 user
        if (postDTO.getAuthor() != null) {
            postDTO.setAuthorDto(this.userRepository.getUserById(postDTO.getAuthor()));
        }
        return postDTO;
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
