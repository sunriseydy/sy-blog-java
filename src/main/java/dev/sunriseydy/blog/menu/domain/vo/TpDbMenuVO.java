package dev.sunriseydy.blog.menu.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.sunriseydy.blog.menu.api.dto.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-05-12 15:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TpDbMenuVO {

    public static final String MENU_TYPE_CATEGORY = "cat";
    public static final String MENU_TYPE_PAGE = "page";
    public static final String MENU_TYPE_CUSTOM = "custom";

    public static final String MENU_OPTIONS = "navMenuOrder";

    private String name;

    private String id;

    private String type;

    @JsonProperty("class")
    private String className;

    private String target;

    private String attrTitle;

    List<Menu> children;

}
