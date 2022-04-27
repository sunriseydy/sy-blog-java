package dev.sunriseydy.blog.menu.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author SunriseYDY
 * @date 2022-04-27 17:07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuItemDTO implements Serializable {

    private static final long serialVersionUID = -5569581691557408510L;

    private Long id;

    private String titleString;

    private String status;

    private String url;

    private String attrTitle;

    private String description;

    private String type;

    private String typeLabel;

    private String object;

    private Long objectId;

    private Long parent;

    private Long menuOrder;

    /**
     * _blank
     */
    private String target;

    private Long menus;
}
