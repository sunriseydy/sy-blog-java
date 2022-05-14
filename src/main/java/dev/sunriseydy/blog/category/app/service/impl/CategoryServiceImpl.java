package dev.sunriseydy.blog.category.app.service.impl;

import dev.sunriseydy.blog.category.api.dto.CategoryDTO;
import dev.sunriseydy.blog.category.app.service.CategoryService;
import dev.sunriseydy.blog.category.domain.repository.CategoryRepository;
import dev.sunriseydy.blog.common.constants.BlogCacheConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author SunriseYDY
 * @date 2022-05-10 20:53
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final RedisTemplate<String, CategoryDTO> redisTemplate;

    public CategoryServiceImpl(CategoryRepository categoryRepository, RedisTemplate<String, CategoryDTO> redisTemplate) {
        this.categoryRepository = categoryRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<CategoryDTO> categories = this.redisTemplate.opsForValue().multiGet(
                Objects.requireNonNull(this.redisTemplate.keys(BlogCacheConstant.getCacheKey(BlogCacheConstant.CACHE_NAME_CATEGORIES) + "*"))
        );
        return categories.stream()
                .sorted(Comparator.comparing(CategoryDTO::getCount).reversed())
                .peek(categoryDTO -> categoryDTO.setFullSlug(this.getParentSlug(categories, categoryDTO.getParent()) + categoryDTO.getSlug()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getCategoriesTree() {
        List<CategoryDTO> categories = this.getCategories();
        return this.getChildrenCategories(categories, 0L)
                .stream()
                .sorted(Comparator.comparing(CategoryDTO::getId))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategoryById(Long id) {
        return this.categoryRepository.updateCategoryById(id);
    }

    @Override
    public void deleteCategoryById(Long id) {
        this.categoryRepository.deleteCategoryById(id);
    }

    private List<CategoryDTO> getChildrenCategories(List<CategoryDTO> categories, Long parentId) {
        return categories.stream()
                .filter(categoryDTO -> parentId.equals(categoryDTO.getParent()))
                .sorted(Comparator.comparing(CategoryDTO::getCount).reversed())
                .peek(categoryDTO -> categoryDTO.setChildren(this.getChildrenCategories(categories, categoryDTO.getId())))
                .collect(Collectors.toList());
    }

    private String getParentSlug(List<CategoryDTO> categories, Long parentId) {
        if (parentId == 0) {
            return "";
        }
        CategoryDTO parent = categories.stream()
                .filter(categoryDTO -> parentId.equals(categoryDTO.getId()))
                .findFirst().get();
        return this.getParentSlug(categories, parent.getParent()) + parent.getSlug() + "/";
    }
}
