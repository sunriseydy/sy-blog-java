package dev.sunriseydy.blog.page.app.service.impl;

import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import dev.sunriseydy.blog.page.api.dto.PageDTO;
import dev.sunriseydy.blog.page.app.service.PageService;
import dev.sunriseydy.blog.page.domain.repository.PageRepository;
import dev.sunriseydy.blog.user.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author SunriseYDY
 * @date 2022-05-10 17:31
 */
@Slf4j
@Service
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;

    private final RedisTemplate<String, PageDTO> redisTemplate;

    private final UserRepository userRepository;

    public PageServiceImpl(PageRepository pageRepository, RedisTemplate<String, PageDTO> redisTemplate, UserRepository userRepository) {
        this.pageRepository = pageRepository;
        this.redisTemplate = redisTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public List<PageDTO> getPageList() {
        return this.redisTemplate.opsForValue().multiGet(
                Objects.requireNonNull(this.redisTemplate.keys(BlogCacheConstant.getCacheKey(BlogCacheConstant.CACHE_NAME_PAGES) + "*"))
        );
    }

    @Override
    public PageDTO getPageById(Long id) {
        PageDTO page = this.pageRepository.getPageById(id);
        // 获取 user
        if (page.getAuthor() != null) {
            page.setAuthorDto(this.userRepository.getUserById(page.getAuthor()));
        }
        return page;
    }

    @Override
    public PageDTO getPageBySlug(String slug) {
        return this.getPageList()
                .stream()
                .filter(page -> slug.equals(page.getSlug()))
                .map(PageDTO::getId)
                .map(this::getPageById)
                .findFirst().get();
    }

    @Override
    public PageDTO updatePageById(Long id) {
        return this.pageRepository.updatePageById(id);
    }

    @Override
    public void deletePageById(Long id) {
        this.pageRepository.deletePageById(id);
    }
}
