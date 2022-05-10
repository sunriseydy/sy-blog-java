package dev.sunriseydy.blog.page.api.controller;

import dev.sunriseydy.blog.common.Result;
import dev.sunriseydy.blog.page.api.dto.PageDTO;
import dev.sunriseydy.blog.page.app.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SunriseYDY
 * @date 2022-05-10 19:29
 */
@RestController
@RequestMapping("/page")
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping("/{id}")
    public Result<PageDTO> getPageById(@PathVariable Long id) {
        return Result.ok(pageService.getPageById(id));
    }

    @GetMapping("/slug")
    public Result<PageDTO> getPageBySlug(@RequestParam String slug) {
        return Result.ok(pageService.getPageBySlug(slug));
    }
}
