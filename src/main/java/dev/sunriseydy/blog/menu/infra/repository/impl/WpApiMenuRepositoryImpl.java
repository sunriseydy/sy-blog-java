package dev.sunriseydy.blog.menu.infra.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import dev.sunriseydy.blog.common.constants.WpApiConstant;
import dev.sunriseydy.blog.common.properties.SyBlogProperties;
import dev.sunriseydy.blog.common.utils.WpApiRequestUtil;
import dev.sunriseydy.blog.common.vo.WpApiGlobalRequestParamVO;
import dev.sunriseydy.blog.menu.api.dto.MenuDTO;
import dev.sunriseydy.blog.menu.domain.repository.MenuRepository;
import dev.sunriseydy.blog.menu.domain.vo.WpApiMenuVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-28 11:10
 */
@Slf4j
@Repository
@ConditionalOnProperty(name = BlogSourceTypeConstant.PROPERTY_NAME, havingValue = BlogSourceTypeConstant.SOURCE_TYPE_WP_API)
public class WpApiMenuRepositoryImpl implements MenuRepository {

    private final SyBlogProperties wpProperties;

    private final RestTemplate restTemplate;

    private final String menuApiUri;

    private final String menuDetailApiUri;

    public WpApiMenuRepositoryImpl(SyBlogProperties wpProperties, RestTemplateBuilder restTemplateBuilder) {
        this.wpProperties = wpProperties;
        this.restTemplate = restTemplateBuilder.build();
        this.menuApiUri = wpProperties.getRestApiHost() + WpApiConstant.API_PREFIX + WpApiConstant.API_MENU;
        this.menuDetailApiUri = this.menuApiUri + WpApiConstant.API_DETAIL;
    }

    @Override
    public List<Long> getMenuIdList() {
        return WpApiRequestUtil.getAllItemsId(menuApiUri, new TypeReference<List<WpApiMenuVO>>() {}, restTemplate, WpApiMenuVO::getId);
    }

    @Override
    public MenuDTO getMenuById(Long id) {
        log.info("get that menu:{}", id);
        WpApiGlobalRequestParamVO globalRequestParam = WpApiGlobalRequestParamVO.builder()
                .envelope(Boolean.TRUE)
                .build();
        WpApiMenuVO menuVO = WpApiRequestUtil.getForObjectById(menuDetailApiUri + WpApiRequestUtil.generateQueryParma(globalRequestParam),
                id, restTemplate, WpApiMenuVO.class);
        return menuVO.toMenuDTO();
    }

    @Override
    public MenuDTO updateMenuById(Long id) {
        log.info("update that menu:{}", id);
        return this.getMenuById(id);
    }

    @Override
    public void deleteMenuById(Long id) {
        log.info("delete that menu:{}", id);
    }
}
