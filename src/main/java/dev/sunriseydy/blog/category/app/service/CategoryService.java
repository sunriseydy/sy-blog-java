package dev.sunriseydy.blog.category.app.service;

import dev.sunriseydy.blog.category.api.dto.CategoryDTO;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-05-10 20:47
 */
public interface CategoryService {

    List<CategoryDTO> getCategories();

    List<CategoryDTO> getCategoriesTree();

    CategoryDTO updateCategoryById(Long id);

    void deleteCategoryById(Long id);
}
