package dev.sunriseydy.blog.post.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.sunriseydy.blog.common.constants.DateTimeConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-05-10 14:46
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostMeta implements Serializable {

    private static final long serialVersionUID = 2872763307910530119L;

    @JsonFormat(pattern = DateTimeConstant.DATE_TIME, timezone = DateTimeConstant.DEFAULT_TIME_ZONE)
    private Date date;

    private Long id;

    @JsonFormat(pattern = DateTimeConstant.DATE_TIME, timezone = DateTimeConstant.DEFAULT_TIME_ZONE)
    private Date modified;

    private String slug;

    private String titleString;

    private Long author;

    private String excerptString;

    private Long featuredMedia;

    private String featuredMediaUrl;

    private List<Long> categories;

    private List<Long> tags;

    private String renderType;

    private Boolean hasReadMore;
}
