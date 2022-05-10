package dev.sunriseydy.blog.category.api.controller;

import dev.sunriseydy.blog.category.api.dto.CategoryDTO;
import dev.sunriseydy.blog.category.app.service.CategoryService;
import dev.sunriseydy.blog.common.Result;
import dev.sunriseydy.blog.common.annotion.BasicAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-05-10 21:22
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result<List<CategoryDTO>> getCategories() {
        return Result.ok(this.categoryService.getCategories());
    }

    @GetMapping("/tree")
    public Result<List<CategoryDTO>> getCategoriesTree() {
        return Result.ok(this.categoryService.getCategoriesTree());
    }

    @BasicAuth
    @PutMapping("/{id}")
    public Result<CategoryDTO> updateCategoryById(@PathVariable Long id) {
        return Result.ok(this.categoryService.updateCategoryById(id));
    }
}
