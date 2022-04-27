package dev.sunriseydy.blog.menu.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.sunriseydy.blog.common.vo.WpApiRenderedVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author SunriseYDY
 * @date 2022-04-27 16:42
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WpApiMenuItemVO implements Serializable {

    private static final long serialVersionUID = -947105104645445861L;

    private Long id;

    private WpApiRenderedVO title;

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
