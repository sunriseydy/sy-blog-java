package dev.sunriseydy.blog.page.infra.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import dev.sunriseydy.blog.common.constants.WpApiConstant;
import dev.sunriseydy.blog.common.properties.SyBlogProperties;
import dev.sunriseydy.blog.common.utils.WpApiRequestUtil;
import dev.sunriseydy.blog.common.vo.WpApiGlobalRequestParamVO;
import dev.sunriseydy.blog.page.api.dto.PageDTO;
import dev.sunriseydy.blog.page.domain.repository.PageRepository;
import dev.sunriseydy.blog.page.domain.vo.WpApiPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-27 10:14
 */
@Slf4j
@Repository
@ConditionalOnProperty(name = BlogSourceTypeConstant.PROPERTY_NAME, havingValue = BlogSourceTypeConstant.SOURCE_TYPE_WP_API)
public class WpApiPageRepositoryImpl implements PageRepository {

    private final SyBlogProperties wpProperties;

    private final RestTemplate restTemplate;

    private final String pageApiUri;

    private final String pageDetailApiUri;

    public WpApiPageRepositoryImpl(SyBlogProperties wpProperties, RestTemplateBuilder restTemplateBuilder) {
        this.wpProperties = wpProperties;
        this.restTemplate = restTemplateBuilder.build();
        this.pageApiUri = wpProperties.getRestApiHost() + WpApiConstant.API_PREFIX + WpApiConstant.API_PAGE;
        this.pageDetailApiUri = this.pageApiUri + WpApiConstant.API_DETAIL;
    }

    @Override
    public List<Long> getPageIdList() {
        return WpApiRequestUtil.getAllItemsId(pageApiUri, new TypeReference<List<WpApiPageVO>>() {}, restTemplate, WpApiPageVO::getId);
    }

    @Override
    public PageDTO getPageById(Long id) {
        log.info("get that page:{}", id);
        WpApiGlobalRequestParamVO globalRequestParam = WpApiGlobalRequestParamVO.builder()
                .envelope(Boolean.TRUE)
                .build();
        WpApiPageVO pageVO = WpApiRequestUtil.getForObjectById(pageDetailApiUri + WpApiRequestUtil.generateQueryParma(globalRequestParam),
                id, restTemplate, WpApiPageVO.class);
        return pageVO.toPageDTO();
    }

    @Override
    public PageDTO updatePageById(Long id) {
        log.info("update that page:{}", id);
        return this.getPageById(id);
    }

    @Override
    public void deletePageById(Long id) {
        log.info("delete that page:{}", id);
    }
}
