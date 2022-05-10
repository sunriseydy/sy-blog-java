package dev.sunriseydy.blog.post.api.controller;

import dev.sunriseydy.blog.common.Result;
import dev.sunriseydy.blog.common.annotion.BasicAuth;
import dev.sunriseydy.blog.common.vo.PageVO;
import dev.sunriseydy.blog.post.api.dto.PostDTO;
import dev.sunriseydy.blog.post.app.service.PostService;
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
    public Result<PageVO<PostDTO>> getPostPage(@RequestParam(required = false, defaultValue = "0") int page,
                                               @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return Result.ok(postService.getPostPage(page, pageSize));
    }

    @GetMapping("/slug")
    public Result<PostDTO> getPostBySlug(@NotNull @RequestParam String slug) {
        return Result.ok(postService.getPostBySlug(slug));
    }

    @GetMapping("/category")
    public Result<PageVO<PostDTO>> getPostByCategory(@NotNull @RequestParam Long categoryId,
                                                     @RequestParam(required = false, defaultValue = "0") int page,
                                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return Result.ok(postService.getPostsByCategoryId(categoryId, page, pageSize));
    }

    @GetMapping("/tag")
    public Result<PageVO<PostDTO>> getPostByTag(@NotNull @RequestParam Long tagId,
                                                @RequestParam(required = false, defaultValue = "0") int page,
                                                @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return Result.ok(postService.getPostsByTagId(tagId, page, pageSize));
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
