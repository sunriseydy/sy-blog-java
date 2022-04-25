package dev.sunriseydy.blog.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SunriseYDY
 * @date 2022-03-15 17:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WpApiRenderedVO {

    private String rendered;

    @JsonProperty("protected")
    private Boolean protect;

    public static String getRenderedString(WpApiRenderedVO renderedVO) {
        return renderedVO == null ? null : renderedVO.getRendered();
    }
}
