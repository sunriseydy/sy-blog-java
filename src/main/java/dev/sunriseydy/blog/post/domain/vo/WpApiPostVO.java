package dev.sunriseydy.blog.post.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.sunriseydy.blog.common.constants.DateTimeConstant;
import dev.sunriseydy.blog.common.vo.WpApiRenderedVO;
import dev.sunriseydy.blog.post.api.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-03-09 13:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WpApiPostVO implements Serializable {

    private static final long serialVersionUID = -8279905008557721846L;

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
     * string	Permalink template for the object. Read only Context: edit
     */
    private String permalinkTemplate;

    /**
     * string	Slug automatically generated from the object title. Read only Context: edit
     */
    private String generatedSlug;

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
     * string	The format for the object. Context: view, edit One of: standard, aside, chat, gallery, link, image, quote, status, video, audio
     */
    private String format;

    /**
     * object	Meta fields. Context: view, edit
     * todo 暂时未知数据格式
     */
    @JsonIgnore
    private List<String> meta;

    /**
     * boolean	Whether or not the object should be treated as sticky. Context: view, edit
     */
    private Boolean sticky;

    /**
     * string	The theme file to use to display the object. Context: view, edit
     */
    private String template;

    /**
     * array	The terms assigned to the object in the category taxonomy. Context: view, edit
     */
    private List<Long> categories;

    /**
     * array	The terms assigned to the object in the post_tag taxonomy. Context: view, edit
     */
    private List<Long> tags;

    @JsonProperty(value = "_embedded")
    private ObjectNode embedded;

    public PostDTO toPostDto() {
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(this, postDTO);
        postDTO.setTitleString(WpApiRenderedVO.getRenderedString(this.getTitle()));
        postDTO.setExcerptString(WpApiRenderedVO.getRenderedString(this.getExcerpt()));
        postDTO.setContentString(WpApiRenderedVO.getRenderedString(this.getContent()));
        return postDTO;
    }

}
