package dev.sunriseydy.blog.site.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author SunriseYDY
 * @date 2022-05-12 15:54
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SiteInfoDTO implements Serializable {

    private static final long serialVersionUID = 4723936622680754997L;

    private String title;

    private String description;

    private String url;

    private String email;

    private String featuredMediaUrl;
}
