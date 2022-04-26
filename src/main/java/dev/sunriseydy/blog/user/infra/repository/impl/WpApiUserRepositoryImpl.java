package dev.sunriseydy.blog.user.infra.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import dev.sunriseydy.blog.common.constants.WpApiConstant;
import dev.sunriseydy.blog.common.interfaces.ProxySelf;
import dev.sunriseydy.blog.common.properties.SyBlogProperties;
import dev.sunriseydy.blog.common.utils.WpApiRequestUtil;
import dev.sunriseydy.blog.common.vo.WpApiGlobalRequestParamVO;
import dev.sunriseydy.blog.user.api.dto.UserDTO;
import dev.sunriseydy.blog.user.domain.repository.UserRepository;
import dev.sunriseydy.blog.user.domain.vo.WpApiUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-26 17:00
 */
@Slf4j
@Repository
@ConditionalOnProperty(name = BlogSourceTypeConstant.PROPERTY_NAME, havingValue = BlogSourceTypeConstant.SOURCE_TYPE_WP_API)
public class WpApiUserRepositoryImpl implements UserRepository, ProxySelf<UserRepository> {

    private final SyBlogProperties wpProperties;

    private final RestTemplate restTemplate;

    private final String userApiUri;

    private final String userDetailApiUri;

    public WpApiUserRepositoryImpl(SyBlogProperties wpProperties, RestTemplateBuilder restTemplateBuilder) {
        this.wpProperties = wpProperties;
        this.restTemplate = restTemplateBuilder.build();
        this.userApiUri = wpProperties.getRestApiHost() + WpApiConstant.API_PREFIX + WpApiConstant.API_USER;
        this.userDetailApiUri = this.userApiUri + WpApiConstant.API_DETAIL;
    }

    @Override
    public List<Long> getUserIdList() {
        return WpApiRequestUtil.getAllItemsId(userApiUri, new TypeReference<List<WpApiUserVO>>() {}, restTemplate, WpApiUserVO::getId);
    }

    @Override
    public UserDTO getUserById(Long id) {
        log.info("get that user:{}", id);
        WpApiGlobalRequestParamVO globalRequestParam = WpApiGlobalRequestParamVO.builder()
                .envelope(Boolean.TRUE)
                .build();
        return WpApiRequestUtil.getForObjectById(userDetailApiUri + WpApiRequestUtil.generateQueryParma(globalRequestParam),
                id, restTemplate, WpApiUserVO.class).toUserDTO();
    }

    @Override
    public UserDTO updateUserById(Long id) {
        log.info("update that user:{}", id);
        return this.getUserById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("delete that user:{}", id);
    }
}
