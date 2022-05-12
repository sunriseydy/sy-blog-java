package dev.sunriseydy.blog.site.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SunriseYDY
 * @date 2022-05-12 15:46
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WpApiSiteInfoVO {
    private String title;

    private String description;

    private String url;

    private String email;

    private String featuredMediaUrl;

}
