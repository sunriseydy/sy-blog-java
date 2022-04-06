package dev.sunriseydy.wp;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sunriseydy.wp.category.api.dto.CategoryDTO;
import dev.sunriseydy.wp.category.domain.repository.CategoryRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-06 16:11
 */
@SpringBootTest
@Slf4j
public class CategoryTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testGetCategoryIdList() {
        List<Long> idList = categoryRepository.getCategoryIdList();
        log.debug("id list:{}", idList);
    }

    @SneakyThrows
    @Test
    void testGetCategoryById() {
        CategoryDTO category = categoryRepository.getCategoryById(1L);
        log.info("category: {}", objectMapper.writeValueAsString(category));
    }

    @SneakyThrows
    @Test
    void testUpdateCategoryById() {
        CategoryDTO category = categoryRepository.updateCategoryById(2L);
        log.info("category: {}", objectMapper.writeValueAsString(category));
    }

    @SneakyThrows
    @Test
    void testDeleteCategoryById() {
        categoryRepository.deleteCategoryById(1L);
    }
}
