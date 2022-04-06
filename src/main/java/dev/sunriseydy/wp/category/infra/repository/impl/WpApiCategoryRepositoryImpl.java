package dev.sunriseydy.wp.category.infra.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sunriseydy.wp.category.api.dto.CategoryDTO;
import dev.sunriseydy.wp.category.domain.repository.CategoryRepository;
import dev.sunriseydy.wp.category.domain.vo.WpApiCategoryVO;
import dev.sunriseydy.wp.common.constants.WpApiConstant;
import dev.sunriseydy.wp.common.constants.WpSourceTypeConstant;
import dev.sunriseydy.wp.common.interfaces.ProxySelf;
import dev.sunriseydy.wp.common.properties.SyWpProperties;
import dev.sunriseydy.wp.common.utils.WpApiRequestUtil;
import dev.sunriseydy.wp.common.vo.WpApiGlobalRequestParamVO;
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
@ConditionalOnProperty(name = WpSourceTypeConstant.PROPERTY_NAME, havingValue = WpSourceTypeConstant.SOURCE_TYPE_API)
public class WpApiCategoryRepositoryImpl implements CategoryRepository, ProxySelf<CategoryRepository> {

    private final SyWpProperties wpProperties;

    private final RestTemplate restTemplate;

    private final RedisTemplate<String, CategoryDTO> redisTemplate;

    private final String categoryApiUri;

    private final String categoryDetailApiUri;

    public WpApiCategoryRepositoryImpl(SyWpProperties wpProperties, RestTemplateBuilder restTemplateBuilder, RedisTemplate<String, CategoryDTO> redisTemplate) {
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
