package dev.sunriseydy.blog.tag.infra.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import dev.sunriseydy.blog.common.constants.WpApiConstant;
import dev.sunriseydy.blog.common.properties.SyBlogProperties;
import dev.sunriseydy.blog.common.utils.WpApiRequestUtil;
import dev.sunriseydy.blog.common.vo.WpApiGlobalRequestParamVO;
import dev.sunriseydy.blog.tag.api.dto.TagDTO;
import dev.sunriseydy.blog.tag.domain.repository.TagRepository;
import dev.sunriseydy.blog.tag.domain.vo.WpApiTagVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-25 17:35
 */
@Slf4j
@Repository
@ConditionalOnProperty(name = BlogSourceTypeConstant.PROPERTY_NAME, havingValue = BlogSourceTypeConstant.SOURCE_TYPE_WP_API)
public class WpApiTagRepositoryImpl implements TagRepository {

    private final SyBlogProperties wpProperties;

    private final RestTemplate restTemplate;

    private final String tagApiUri;

    private final String tagDetailApiUri;

    public WpApiTagRepositoryImpl(SyBlogProperties wpProperties, RestTemplateBuilder restTemplateBuilder) {
        this.wpProperties = wpProperties;
        this.restTemplate = restTemplateBuilder.build();
        this.tagApiUri = wpProperties.getRestApiHost() + WpApiConstant.API_PREFIX + WpApiConstant.API_TAG;
        this.tagDetailApiUri = this.tagApiUri + WpApiConstant.API_DETAIL;
    }

    @Override
    public List<Long> getTagIdList() {
        return WpApiRequestUtil.getAllItemsId(tagApiUri, new TypeReference<List<WpApiTagVO>>() {}, restTemplate, WpApiTagVO::getId);
    }

    @Override
    public TagDTO getTagById(Long id) {
        log.info("get that tag:{}", id);
        WpApiGlobalRequestParamVO globalRequestParam = WpApiGlobalRequestParamVO.builder()
                .envelope(Boolean.TRUE)
                .build();
        return WpApiRequestUtil.getForObjectById(tagDetailApiUri + WpApiRequestUtil.generateQueryParma(globalRequestParam),
                id, restTemplate, WpApiTagVO.class).toTagDTO();
    }

    @Override
    public TagDTO updateTagById(Long id) {
        log.info("update that tag:{}", id);
        return this.getTagById(id);
    }

    @Override
    public void deleteTagById(Long id) {
        log.info("delete that tag:{}", id);
    }
}
