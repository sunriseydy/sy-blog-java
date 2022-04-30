package dev.sunriseydy.blog.category.domain.vo;

import dev.sunriseydy.blog.category.api.dto.CategoryDTO;
import dev.sunriseydy.blog.db.typecho.entity.TypechoMetas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author SunriseYDY
 * @date 2022-04-30 13:04
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TpDbCategoryVO {
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
     * The parent term ID.
     * Context: view, edit
     */
    private Long parent;

    public static TpDbCategoryVO fromTypechoMetas(TypechoMetas meta) {
        if (meta == null) {
            return null;
        }
        return TpDbCategoryVO.builder()
                .id(Long.valueOf(meta.getMid()))
                .count(Long.valueOf(meta.getCount()))
                .description(meta.getDescription())
                .name(meta.getName())
                .slug(meta.getSlug())
                .parent(Long.valueOf(meta.getParent()))
                .build();
    }

    public CategoryDTO toCategoryDTO() {
        CategoryDTO dto = new CategoryDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
