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
 * @date 2022-03-18 09:17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO implements Serializable {

    private static final long serialVersionUID = -5634128565094551430L;

    /**
     * string or null, datetime (details) The date the object was published, in the site's timezone. Context: view, edit, embed
     */
    @JsonFormat(pattern = DateTimeConstant.DATE_TIME, timezone = DateTimeConstant.DEFAULT_TIME_ZONE)
    private Date date;

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
    @JsonFormat(pattern = DateTimeConstant.DATE_TIME, timezone = DateTimeConstant.DEFAULT_TIME_ZONE)
    private Date modified;

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
     * object	The title for the object. Context: view, edit, embed
     */
    private String titleString;

    /**
     * object	The content for the object. Context: view, edit
     */
    private String contentString;

    /**
     * integer	The ID for the author of the object. Context: view, edit, embed
     * todo
     */
    private Long author;

    /**
     * object	The excerpt for the object. Context: view, edit, embed
     */
    private String excerptString;

    /**
     * integer	The ID of the featured media for the object. Context: view, edit, embed
     * todo
     */
    private Long featuredMedia;

    /**
     * string	Whether or not comments are open on the object. Context: view, edit One of: open, closed
     */
    private String commentStatus;

    /**
     * string	The format for the object. Context: view, edit One of: standard, aside, chat, gallery, link, image, quote, status, video, audio
     */
    private String format;

    /**
     * array	The terms assigned to the object in the category taxonomy. Context: view, edit
     * todo
     */
    private List<Long> categories;

    /**
     * array	The terms assigned to the object in the post_tag taxonomy. Context: view, edit
     * todo
     */
    private List<Long> tags;

    public PostDTO clearContent() {
        this.setContentString(null);
        return this;
    }
}
