package dev.sunriseydy.blog.page.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.sunriseydy.blog.common.constants.DateTimeConstant;
import dev.sunriseydy.blog.common.vo.WpApiRenderedVO;
import dev.sunriseydy.blog.page.api.dto.PageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SunriseYDY
 * @date 2022-04-27 09:07
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WpApiPageVO implements Serializable {

    private static final long serialVersionUID = -5648191392474095298L;

    /**
     * string or null, datetime (details) The date the object was published, in the site's timezone. Context: view, edit, embed
     */
    @JsonFormat(pattern = DateTimeConstant.DATE_TIME_T, timezone = DateTimeConstant.DEFAULT_TIME_ZONE)
    private Date date;

    /**
     * string or null, datetime (details) The date the object was published, as GMT. Context: view, edit
     * <code>
     *     date_gmt
     * </code>
     */
    @JsonFormat(pattern = DateTimeConstant.DATE_TIME_T)
    private Date dateGmt;

    /**
     * object	The globally unique identifier for the object. Read only Context: view, edit
     */
    private WpApiRenderedVO guid;

    /**
     * integer	Unique identifier for the object. Read only Context: view, edit, embed
     */
    private Long id;

    /**
     * string, uri URL to the object. Read only Context: view, edit, embed
     */
    private String link;

    /**
     * string, datetime (details) The date the object was last modified, in the site's timezone. Read only Context: view, edit
     */
    @JsonFormat(pattern = DateTimeConstant.DATE_TIME_T, timezone = DateTimeConstant.DEFAULT_TIME_ZONE)
    private Date modified;

    /**
     * string, datetime (details) The date the object was last modified, as GMT. Read only Context: view, edit
     */
    @JsonFormat(pattern = DateTimeConstant.DATE_TIME_T)
    private Date modifiedGmt;

    /**
     * string	An alphanumeric identifier for the object unique to its type. Context: view, edit, embed
     */
    private String slug;

    /**
     * string	A named status for the object. Context: view, edit One of: publish, future, draft, pending, private
     */
    private String status;

    /**
     * string	Type of Post for the object. Read only Context: view, edit, embed
     */
    private String type;

    /**
     * string	A password to protect access to the content and excerpt. Context: edit
     */
    private String password;

    /**
     * object	The title for the object. Context: view, edit, embed
     */
    private WpApiRenderedVO title;

    /**
     * object	The content for the object. Context: view, edit
     */
    private WpApiRenderedVO content;

    /**
     * integer	The ID for the author of the object. Context: view, edit, embed
     */
    private Long author;

    /**
     * object	The excerpt for the object. Context: view, edit, embed
     */
    private WpApiRenderedVO excerpt;

    /**
     * integer	The ID of the featured media for the object. Context: view, edit, embed
     */
    private Long featuredMedia;

    /**
     * string	Whether or not comments are open on the object. Context: view, edit One of: open, closed
     */
    private String commentStatus;

    /**
     * string	Whether or not the object can be pinged. Context: view, edit One of: open, closed
     */
    private String pingStatus;

    /**
     * string	The theme file to use to display the object. Context: view, edit
     */
    private String template;

    private Long parent;

    private Integer menuOrder;

    @JsonProperty(value = "_embedded")
    private ObjectNode embedded;

    public PageDTO toPageDTO() {
        PageDTO dto = new PageDTO();
        BeanUtils.copyProperties(this, dto);
        dto.setTitleString(WpApiRenderedVO.getRenderedString(this.getTitle()));
        dto.setExcerptString(WpApiRenderedVO.getRenderedString(this.getExcerpt()));
        dto.setContentString(WpApiRenderedVO.getRenderedString(this.getContent()));
        // 设置 featuredMediaUrl
        if (this.embedded != null
                && this.embedded.hasNonNull("wp:featuredmedia")
                && this.embedded.get("wp:featuredmedia").isArray()
                && this.embedded.get("wp:featuredmedia").hasNonNull(0)
                && this.embedded.get("wp:featuredmedia").get(0).hasNonNull("source_url")) {
            JsonNode sourceUrl = this.embedded.get("wp:featuredmedia").get(0).get("source_url");
            dto.setFeaturedMediaUrl(sourceUrl.asText());

        }
        return dto;
    }
}
