package dev.sunriseydy.blog.site.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.sunriseydy.blog.site.api.dto.SiteInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @author SunriseYDY
 * @date 2022-05-12 15:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TpDbSiteInfoVO {

    public static final String OPTION_TITLE = "title";
    public static final String OPTION_DESCRIPTION = "description";
    public static final String OPTION_URL = "siteUrl";
    public static final String OPTION_IMAGE = "siteImage";

    private String title;

    private String description;

    private String url;

    private String email;

    private String featuredMediaUrl;

    public SiteInfoDTO toDto() {
        SiteInfoDTO dto = new SiteInfoDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
