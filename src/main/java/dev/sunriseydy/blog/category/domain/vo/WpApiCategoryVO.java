package dev.sunriseydy.blog.category.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.sunriseydy.blog.category.api.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author SunriseYDY
 * @date 2022-04-02 10:06
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WpApiCategoryVO implements Serializable {

    private static final long serialVersionUID = 5079400551053937930L;

    public static final String TAXONOMY_TAG = "post_tag";

    /**
     * Unique identifier for the term.
     * Read only
     *
     * Context: view, embed, edit
     */
    private Long id;

    /**
     * Number of published posts for the term.
     * Read only
     *
     * Context: view, edit
     */
    private Long count;

    /**
     * HTML description of the term.
     * Context: view, edit
     */
    private String description;

    /**
     * URL of the term.
     * Read only
     *
     * Context: view, embed, edit
     */
    private String link;

    /**
     * HTML title for the term.
     * Context: view, embed, edit
     */
    private String name;

    /**
     * An alphanumeric identifier for the term unique to its type.
     * Context: view, embed, edit
     */
    private String slug;

    /**
     * Type attribution for the term.
     * Read only
     *
     * Context: view, embed, edit
     *
     * One of: category, post_tag, nav_menu, link_category, post_format
     */
    private String taxonomy;

    /**
     * The parent term ID.
     * Context: view, edit
     */
    private Long parent;

    public CategoryDTO toCategoryDTO() {
        CategoryDTO dto = new CategoryDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
