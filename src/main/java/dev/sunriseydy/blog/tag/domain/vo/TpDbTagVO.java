package dev.sunriseydy.blog.tag.domain.vo;

import dev.sunriseydy.blog.db.typecho.entity.TypechoMetas;
import dev.sunriseydy.blog.tag.api.dto.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author SunriseYDY
 * @date 2022-04-30 12:20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TpDbTagVO {
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
     * Type attribution for the term.
     * Read only
     *
     * Context: view, embed, edit
     *
     * One of: category, post_tag, nav_menu, link_category, post_format
     */
    private String taxonomy;

    public static TpDbTagVO fromTypechoMetas(TypechoMetas meta) {
        if (meta == null) {
            return null;
        }
        return TpDbTagVO.builder()
                .id(Long.valueOf(meta.getMid()))
                .count(Long.valueOf(meta.getCount()))
                .description(meta.getDescription())
                .name(meta.getName())
                .slug(meta.getSlug())
                .build();
    }

    public TagDTO toTagDTO() {
        TagDTO dto = new TagDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
