package dev.sunriseydy.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sunriseydy.blog.common.vo.PageVO;
import dev.sunriseydy.blog.post.api.dto.PostDTO;
import dev.sunriseydy.blog.post.app.service.PostService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author SunriseYDY
 * @date 2022-04-30 16:15
 */
@SpringBootTest
class PostServiceImplTest {

    @Autowired
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    void getPostPage() {
        PageVO<PostDTO> postPage = postService.getPostPage(0, 2);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(postPage));
    }

    @Test
    @SneakyThrows
    void getPostById() {
        PostDTO post = postService.getPostById(1389L);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(post));
    }
}