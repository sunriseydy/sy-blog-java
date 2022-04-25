package dev.sunriseydy.blog.category.infra.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sunriseydy.blog.category.api.dto.CategoryDTO;
import dev.sunriseydy.blog.category.domain.repository.CategoryRepository;
import dev.sunriseydy.blog.category.domain.vo.WpApiCategoryVO;
import dev.sunriseydy.blog.common.constants.WpApiConstant;
import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import dev.sunriseydy.blog.common.interfaces.ProxySelf;
import dev.sunriseydy.blog.common.properties.SyBlogProperties;
import dev.sunriseydy.blog.common.utils.WpApiRequestUtil;
import dev.sunriseydy.blog.common.vo.WpApiGlobalRequestParamVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-02 11:58
 */
@Repository
@Slf4j
@ConditionalOnProperty(name = BlogSourceTypeConstant.PROPERTY_NAME, havingValue = BlogSourceTypeConstant.SOURCE_TYPE_WP_API)
public class WpApiCategoryRepositoryImpl implements CategoryRepository, ProxySelf<CategoryRepository> {

    private final SyBlogProperties wpProperties;

    private final RestTemplate restTemplate;

    private final RedisTemplate<String, CategoryDTO> redisTemplate;

    private final String categoryApiUri;

    private final String categoryDetailApiUri;

    public WpApiCategoryRepositoryImpl(SyBlogProperties wpProperties, RestTemplateBuilder restTemplateBuilder, RedisTemplate<String, CategoryDTO> redisTemplate) {
        this.wpProperties = wpProperties;
        this.restTemplate = restTemplateBuilder.build();
        this.categoryApiUri = wpProperties.getRestApiHost() + WpApiConstant.API_PREFIX + WpApiConstant.API_CATEGORY;
        this.categoryDetailApiUri = this.categoryApiUri + WpApiConstant.API_DETAIL;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Long> getCategoryIdList() {
        return WpApiRequestUtil.getAllItemsId(categoryApiUri, new TypeReference<List<WpApiCategoryVO>>() {
        }, restTemplate, WpApiCategoryVO::getId);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        log.info("get that category:{}", id);
        WpApiGlobalRequestParamVO globalRequestParam = WpApiGlobalRequestParamVO.builder()
                .envelope(Boolean.TRUE)
                .build();
        return WpApiRequestUtil.getForObjectById(categoryDetailApiUri + WpApiRequestUtil.generateQueryParma(globalRequestParam),
                id, restTemplate, WpApiCategoryVO.class).toCategoryDTO();
    }

    @Override
    public CategoryDTO updateCategoryById(Long id) {
        log.info("update that category:{}", id);
        return this.getCategoryById(id);
    }

    @Override
    public void deleteCategoryById(Long id) {
        log.info("delete that category:{}", id);
    }
}
