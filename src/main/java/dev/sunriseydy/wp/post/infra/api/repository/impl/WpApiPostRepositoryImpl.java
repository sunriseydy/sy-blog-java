package dev.sunriseydy.wp.post.infra.api.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.sunriseydy.wp.common.constants.WpApiConstant;
import dev.sunriseydy.wp.common.properties.SyWpProperties;
import dev.sunriseydy.wp.common.utils.WpApiRequestUtil;
import dev.sunriseydy.wp.common.utils.WpApiResponseUtil;
import dev.sunriseydy.wp.common.vo.WpApiGlobalRequestParamVO;
import dev.sunriseydy.wp.common.vo.WpApiPaginationVO;
import dev.sunriseydy.wp.post.domain.entity.Post;
import dev.sunriseydy.wp.post.domain.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-03-17 14:12
 */
@Repository
@Slf4j
public class WpApiPostRepositoryImpl implements PostRepository {

    private final SyWpProperties wpProperties;

    private final RestTemplate restTemplate;

    public WpApiPostRepositoryImpl(SyWpProperties wpProperties, RestTemplateBuilder restTemplateBuilder) {
        this.wpProperties = wpProperties;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<Post> getPostIdList() {
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

        String postApiUri = wpProperties.getRestApiHost() + WpApiConstant.API_PREFIX + WpApiConstant.API_POST;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URI.create(postApiUri + WpApiRequestUtil.generateQueryParma(pagination, globalRequestParam)), String.class);

        String response = WpApiResponseUtil.checkResponseEntityAndReturnBody(responseEntity);

        int total = WpApiResponseUtil.getResponseTotal(response);
        int totalPages = WpApiResponseUtil.getResponseTotalPages(response);
        log.info("page:{}, totalPage:{}", page, totalPages);
        log.info("total:{}", total);

        List<Post> posts = WpApiResponseUtil.getResponseBodyAsObject(response, new TypeReference<List<Post>>() {
        });

        while (page++ < totalPages) {
            log.info("page:{}, totalPage:{}", page, totalPages);
            pagination = WpApiPaginationVO.builder()
                    .page(page)
                    .perPage(perPage)
                    .build();
            responseEntity = restTemplate.getForEntity(URI.create(postApiUri + WpApiRequestUtil.generateQueryParma(pagination, globalRequestParam)), String.class);
            response = WpApiResponseUtil.checkResponseEntityAndReturnBody(responseEntity);
            posts.addAll(WpApiResponseUtil.getResponseBodyAsObject(response, new TypeReference<List<Post>>() {
            }));
        }
        return posts;
    }

    @Override
    public Post getPostById() {
        return null;
    }
}
