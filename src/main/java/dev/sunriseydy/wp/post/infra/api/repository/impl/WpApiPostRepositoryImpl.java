package dev.sunriseydy.wp.post.infra.api.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sunriseydy.wp.common.constants.WpApiConstant;
import dev.sunriseydy.wp.common.constants.WpCacheConstant;
import dev.sunriseydy.wp.common.constants.WpSourceTypeConstant;
import dev.sunriseydy.wp.common.interfaces.ProxySelf;
import dev.sunriseydy.wp.common.properties.SyWpProperties;
import dev.sunriseydy.wp.common.utils.WpApiRequestUtil;
import dev.sunriseydy.wp.common.utils.WpApiResponseUtil;
import dev.sunriseydy.wp.common.vo.WpApiGlobalRequestParamVO;
import dev.sunriseydy.wp.common.vo.WpApiPaginationVO;
import dev.sunriseydy.wp.post.api.dto.PostDTO;
import dev.sunriseydy.wp.post.domain.repository.PostRepository;
import dev.sunriseydy.wp.post.domain.vo.WpApiPostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SunriseYDY
 * @date 2022-03-17 14:12
 */
@Repository
@ConditionalOnProperty(name = WpSourceTypeConstant.PROPERTY_NAME, havingValue = WpSourceTypeConstant.SOURCE_TYPE_API)
@Slf4j
public class WpApiPostRepositoryImpl implements PostRepository, ProxySelf<PostRepository> {

    private final SyWpProperties wpProperties;

    private final RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final String postApiUri;

    private final String postDetailApiUri;

    public WpApiPostRepositoryImpl(SyWpProperties wpProperties, RestTemplateBuilder restTemplateBuilder) {
        this.wpProperties = wpProperties;
        this.restTemplate = restTemplateBuilder.build();
        this.postApiUri = wpProperties.getRestApiHost() + WpApiConstant.API_PREFIX + WpApiConstant.API_POST;
        this.postDetailApiUri = this.postApiUri + WpApiConstant.API_DETAIL;
    }

    @Override
    public List<PostDTO> getPostIdList() {
        int page = 1;
        int perPage = 10;
        WpApiPaginationVO pagination = WpApiPaginationVO.builder()
                .page(page)
                .perPage(perPage)
                .build();
        WpApiGlobalRequestParamVO globalRequestParam = WpApiGlobalRequestParamVO.builder()
                .envelope(Boolean.TRUE)
                .fieldsList(Collections.singletonList("id"))
                .build();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URI.create(postApiUri + WpApiRequestUtil.generateQueryParma(pagination, globalRequestParam)), String.class);

        String response = WpApiResponseUtil.checkResponseEntityAndReturnBody(responseEntity);

        int total = WpApiResponseUtil.getResponseTotal(response);
        int totalPages = WpApiResponseUtil.getResponseTotalPages(response);
        log.info("page:{}, totalPage:{}", page, totalPages);
        log.info("total:{}", total);

        List<WpApiPostVO> posts = WpApiResponseUtil.getResponseBodyAsObject(response, new TypeReference<List<WpApiPostVO>>() {
        });

        while (page++ < totalPages) {
            log.info("page:{}, totalPage:{}", page, totalPages);
            pagination = WpApiPaginationVO.builder()
                    .page(page)
                    .perPage(perPage)
                    .build();
            responseEntity = restTemplate.getForEntity(URI.create(postApiUri + WpApiRequestUtil.generateQueryParma(pagination, globalRequestParam)), String.class);
            response = WpApiResponseUtil.checkResponseEntityAndReturnBody(responseEntity);
            posts.addAll(WpApiResponseUtil.getResponseBodyAsObject(response, new TypeReference<List<WpApiPostVO>>() {
            }));
        }
        return posts.stream().map(WpApiPostVO::toPostDto).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Long id) {
        log.info("get this post:{}", id);
        WpApiGlobalRequestParamVO globalRequestParam = WpApiGlobalRequestParamVO.builder()
                .envelope(Boolean.TRUE)
                .embed("wp:featuredmedia,wp:term,author")
                .build();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(postDetailApiUri + WpApiRequestUtil.generateQueryParma(globalRequestParam), String.class, id);
        String response = WpApiResponseUtil.checkResponseEntityAndReturnBody(responseEntity);
        WpApiPostVO postVO = WpApiResponseUtil.getResponseBodyAsObject(response, WpApiPostVO.class);
        return postVO.toPostDto();
    }

    @Override
    public PostDTO updatePostById(Long id) {
        log.info("update this post:{}", id);
        return this.getPostById(id);
    }

    @Override
    public void deletePostById(Long id) {
        String key = WpCacheConstant.CACHE_KEY_POSTS + String.valueOf(id);
        Object post = redisTemplate.opsForValue().get(key);
        log.info("delete this post:{},{}", id, post );
    }
}
