package dev.sunriseydy.wp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sunriseydy.wp.common.properties.SyWpProperties;
import dev.sunriseydy.wp.common.utils.WpApiRequestUtil;
import dev.sunriseydy.wp.common.vo.WpApiGlobalRequestParamVO;
import dev.sunriseydy.wp.common.vo.WpApiPaginationVO;
import dev.sunriseydy.wp.post.domain.entity.Post;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SyWpJavaApplicationTests {

    @Autowired
    private SyWpProperties syWpProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testProperty() {
        System.out.println(syWpProperties.getSourceType());
        System.out.println(syWpProperties.getRestApiHost());
    }

    @SneakyThrows
    @Test
    void testPostJson() {
        Post post = Post.builder().date(new Date()).build();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(post);
        System.out.println(json);
        Post readValue = objectMapper.readValue(json, Post.class);
        System.out.println(readValue);
    }

    @SneakyThrows
    @Test
    void testReadPost() {
        List<Post> readValue = objectMapper.readValue(new File("/home/sunriseydy/Desktop/temp/acgn-post.json"), new TypeReference<List<Post>>() {
        });
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(readValue.get(0)));
    }

    @Test
    void testQueryParam() {
        String queryParma = WpApiRequestUtil.generateQueryParma(
    WpApiPaginationVO.builder()
                    .page(1)
                    .perPage(100)
                .build(),
                WpApiGlobalRequestParamVO.builder()
                    .envelope(Boolean.TRUE)
                    .fieldsList(Arrays.asList("id", "author"))
                .build());
        System.out.println(queryParma);
    }
}
