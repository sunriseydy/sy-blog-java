package dev.sunriseydy.blog.tag.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author SunriseYDY
 * @date 2022-04-07 10:50
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagDTO implements Serializable {

    private static final long serialVersionUID = 4790212549890198562L;

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

}
