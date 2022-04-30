package dev.sunriseydy.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sunriseydy.blog.post.api.dto.PostDTO;
import dev.sunriseydy.blog.post.domain.repository.PostRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-30 12:07
 */
@Slf4j
@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    void getPostIdList() {
        List<Long> posts = postRepository.getPostIdList();
        log.debug("posts:{}", objectMapper.writeValueAsString(posts));
    }

    @SneakyThrows
    @Test
    void getPostById() {
        PostDTO post = postRepository.getPostById(1389L);
        log.debug("post:{}", objectMapper.writeValueAsString(post));
    }

    @Test
    void updatePostById() {
        PostDTO postDTO = postRepository.updatePostById(1389L);
        log.debug("post:{}", postDTO);
    }

    @Test
    void deletePostById() {
        postRepository.deletePostById(379L);
    }
}