package dev.sunriseydy.blog.menu.infra.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import dev.sunriseydy.blog.common.constants.WpApiConstant;
import dev.sunriseydy.blog.common.properties.SyBlogProperties;
import dev.sunriseydy.blog.common.utils.WpApiRequestUtil;
import dev.sunriseydy.blog.common.vo.WpApiGlobalRequestParamVO;
import dev.sunriseydy.blog.menu.api.dto.MenuItemDTO;
import dev.sunriseydy.blog.menu.domain.repository.MenuItemRepository;
import dev.sunriseydy.blog.menu.domain.vo.WpApiMenuItemVO;
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
public class WpApiMenuItemRepositoryImpl implements MenuItemRepository {

    private final SyBlogProperties wpProperties;

    private final RestTemplate restTemplate;

    private final String menuItemApiUri;

    private final String menuItemDetailApiUri;

    public WpApiMenuItemRepositoryImpl(SyBlogProperties wpProperties, RestTemplateBuilder restTemplateBuilder) {
        this.wpProperties = wpProperties;
        this.restTemplate = restTemplateBuilder.build();
        this.menuItemApiUri = wpProperties.getRestApiHost() + WpApiConstant.API_PREFIX + WpApiConstant.API_MENU_ITEM;
        this.menuItemDetailApiUri = this.menuItemApiUri + WpApiConstant.API_DETAIL;
    }

    @Override
    public List<Long> getMenuItemIdList() {
        return WpApiRequestUtil.getAllItemsId(menuItemApiUri, new TypeReference<List<WpApiMenuItemVO>>() {}, restTemplate, WpApiMenuItemVO::getId);
    }

    @Override
    public MenuItemDTO getMenuItemById(Long id) {
        log.info("get that menu item:{}", id);
        WpApiGlobalRequestParamVO globalRequestParam = WpApiGlobalRequestParamVO.builder()
                .envelope(Boolean.TRUE)
                .build();
        WpApiMenuItemVO menuItemVO = WpApiRequestUtil.getForObjectById(menuItemDetailApiUri + WpApiRequestUtil.generateQueryParma(globalRequestParam),
                id, restTemplate, WpApiMenuItemVO.class);
        return menuItemVO.toMenuItemDTO();
    }

    @Override
    public MenuItemDTO updateMenuItemById(Long id) {
        log.info("update that menu item:{}", id);
        return this.getMenuItemById(id);
    }

    @Override
    public void deleteMenuItemById(Long id) {
        log.info("delete that menu item:{}", id);
    }
}
