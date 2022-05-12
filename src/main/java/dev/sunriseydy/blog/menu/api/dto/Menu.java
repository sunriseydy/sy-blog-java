package dev.sunriseydy.blog.menu.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-05-12 15:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu {

    public static final String MENU_TYPE_CATEGORY = "category";
    public static final String MENU_TYPE_PAGE = "page";
    public static final String MENU_TYPE_CUSTOM = "custom";

    private String name;

    private String type;

    private Long objectId;

    private String slug;

    private String className;

    private String target;

    private String attrTitle;

    List<Menu> children;
}
