package dev.sunriseydy.blog.tag.api.controller;

import dev.sunriseydy.blog.common.Result;
import dev.sunriseydy.blog.common.annotion.BasicAuth;
import dev.sunriseydy.blog.tag.api.dto.TagDTO;
import dev.sunriseydy.blog.tag.app.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-05-10 22:13
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public Result<List<TagDTO>> getTagList() {
        return Result.ok(this.tagService.getTagList());
    }

    @BasicAuth
    @PutMapping("/{id}")
    public Result<TagDTO> updateTagById(@PathVariable Long id) {
        return Result.ok(this.tagService.updateTagById(id));
    }
}
