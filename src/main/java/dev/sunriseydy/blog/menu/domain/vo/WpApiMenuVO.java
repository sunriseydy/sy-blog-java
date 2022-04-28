package dev.sunriseydy.blog.menu.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.sunriseydy.blog.menu.api.dto.MenuDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-27 11:10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WpApiMenuVO implements Serializable {

    private static final long serialVersionUID = 8695296984016023208L;

    private Long id;

    private String description;

    private String name;

    private String slug;

    private List<String> locations;

    public MenuDTO toMenuDTO() {
        MenuDTO dto = new MenuDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
