package dev.sunriseydy.blog.tag.app.service.impl;

import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import dev.sunriseydy.blog.tag.api.dto.TagDTO;
import dev.sunriseydy.blog.tag.app.service.TagService;
import dev.sunriseydy.blog.tag.domain.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author SunriseYDY
 * @date 2022-05-10 22:03
 */
@Slf4j
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private RedisTemplate<String, TagDTO> redisTemplate;

    @Override
    public List<TagDTO> getTagList() {
        return this.redisTemplate.opsForValue()
                .multiGet(Objects.requireNonNull(this.redisTemplate.keys(BlogCacheConstant.getCacheKey(BlogCacheConstant.CACHE_NAME_TAGS) + "*")))
                .stream()
                .sorted(Comparator.comparing(TagDTO::getCount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public TagDTO updateTagById(Long id) {
        return this.tagRepository.updateTagById(id);
    }
}
