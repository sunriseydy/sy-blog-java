package dev.sunriseydy.blog.menu.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-27 17:07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDTO implements Serializable {

    private static final long serialVersionUID = 6323299273841439349L;

    private Long id;

    private String description;

    private String name;

    private String slug;

    private List<String> locations;
}
