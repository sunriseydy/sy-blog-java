package dev.sunriseydy.wp.post.api.controller;

import dev.sunriseydy.wp.common.Result;
import dev.sunriseydy.wp.common.annotion.BasicAuth;
import dev.sunriseydy.wp.common.vo.PageVO;
import dev.sunriseydy.wp.post.api.dto.PostDTO;
import dev.sunriseydy.wp.post.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author SunriseYDY
 * @date 2022-03-21 11:54
 */
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public Result<PostDTO> getPostById(@NotNull @PathVariable Long id) {
        return Result.ok(postService.getPostById(id));
    }

    @GetMapping()
    public Result<PageVO<PostDTO>> getPostPage(@RequestParam(required = false, defaultValue = "1") int page,
                                               @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return Result.ok(postService.getPostPage(page, pageSize));
    }

    @BasicAuth
    @PutMapping("/{id}")
    public Result<PostDTO> updatePostById(@NotNull @PathVariable Long id) {
        return Result.ok(postService.updatePostById(id));
    }

    @BasicAuth
    @DeleteMapping("/{id}")
    public Result<?> deletePostById(@NotNull @PathVariable Long id) {
        postService.deletePostById(id);
        return Result.ok();
    }
}
