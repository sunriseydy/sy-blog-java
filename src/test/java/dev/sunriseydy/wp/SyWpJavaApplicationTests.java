package dev.sunriseydy.wp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sunriseydy.wp.common.properties.SyWpProperties;
import dev.sunriseydy.wp.common.utils.WpApiRequestUtil;
import dev.sunriseydy.wp.common.utils.WpApiResponseUtil;
import dev.sunriseydy.wp.common.vo.WpApiGlobalRequestParamVO;
import dev.sunriseydy.wp.common.vo.WpApiPaginationVO;
import dev.sunriseydy.wp.post.api.dto.PostDTO;
import dev.sunriseydy.wp.post.domain.repository.PostRepository;
import dev.sunriseydy.wp.post.domain.vo.WpApiPostVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
class SyWpJavaApplicationTests {

    @Autowired
    private SyWpProperties syWpProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

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
        WpApiPostVO post = WpApiPostVO.builder().date(new Date()).build();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(post);
        System.out.println(json);
        WpApiPostVO readValue = objectMapper.readValue(json, WpApiPostVO.class);
        System.out.println(readValue);
    }

    @SneakyThrows
    @Test
    void testReadPost() {
        List<WpApiPostVO> readValue = objectMapper.readValue(new File("/home/sunriseydy/Desktop/temp/acgn-post.json"), new TypeReference<List<WpApiPostVO>>() {
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

    @SneakyThrows
    @Test
    void testResponseOne() {
        JsonNode jsonNode = objectMapper.readTree(new File("/home/sunriseydy/Desktop/temp/correct.json"));
        JsonNode body = jsonNode.get(WpApiResponseUtil.RESPONSE_BODY);
        List<WpApiPostVO> posts = objectMapper.readValue(body.traverse(), new TypeReference<List<WpApiPostVO>>() {
        });
        System.out.println(posts.get(0));
    }

    @SneakyThrows
    @Test
    void testGetPostIdList() {
        List<PostDTO> posts = postRepository.getPostIdList();
        log.debug("posts:{}", objectMapper.writeValueAsString(posts));
    }

    @SneakyThrows
    @Test
    void testGetPostById() {
        PostDTO post = postRepository.getPostById(379L);
        log.debug("post:{}", objectMapper.writeValueAsString(post));
    }
}
