package dev.sunriseydy.blog.page.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.sunriseydy.blog.common.constants.DateTimeConstant;
import dev.sunriseydy.blog.user.api.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author SunriseYDY
 * @date 2022-04-27 10:02
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageDTO implements Serializable {

    private static final long serialVersionUID = 2550744688972628554L;

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
     */
    private Long author;

    private UserDTO authorDto;

    /**
     * object	The excerpt for the object. Context: view, edit, embed
     */
    private String excerptString;

    /**
     * integer	The ID of the featured media for the object. Context: view, edit, embed
     */
    private Long featuredMedia;

    private String featuredMediaUrl;

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

    private String renderType;
}
