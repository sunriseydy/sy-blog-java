package dev.sunriseydy.wp.category.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author SunriseYDY
 * @date 2022-04-02 11:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = -3499589766108227969L;

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
}
