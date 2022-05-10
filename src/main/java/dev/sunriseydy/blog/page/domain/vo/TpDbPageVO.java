package dev.sunriseydy.blog.page.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.sunriseydy.blog.db.typecho.entity.TypechoContents;
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
 * @date 2022-05-10 17:12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TpDbPageVO implements Serializable {

    private static final long serialVersionUID = 8272962406989560102L;

    /**
     * string or null, datetime (details) The date the object was published, in the site's timezone. Context: view, edit, embed
     */
    private Date date;

    /**
     * integer	Unique identifier for the object. Read only Context: view, edit, embed
     */
    private Long id;

    /**
     * string, datetime (details) The date the object was last modified, in the site's timezone. Read only Context: view, edit
     */
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
     * string	A password to protect access to the content and excerpt. Context: edit
     */
    private String password;

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

    /**
     * integer	The ID of the featured media for the object. Context: view, edit, embed
     */
    private Long featuredMedia;

    /**
     * 需要查field表的‘featured_media_url’
     */
    private String featuredMediaUrl;

    /**
     * string	Whether or not comments are open on the object. Context: view, edit One of: open, closed
     */
    private String commentStatus;

    private Long parent;

    private Integer menuOrder;

    private String renderType;

    public static TpDbPageVO fromTypechoContent(TypechoContents content) {
        if (content == null) {
            return null;
        }
        TpDbPageVO page = new TpDbPageVO();
        page.setId(Long.valueOf(content.getCid()));
        page.setDate(new Date(content.getCreated() * 1000L));
        page.setModified(new Date(content.getModified() * 1000L));
        page.setSlug(content.getSlug());
        page.setStatus(content.getStatus());
        page.setType(content.getType());
        page.setPassword(content.getPassword());
        page.setTitleString(content.getTitle());
        page.setContentString(content.getText());
        page.setAuthor(Long.valueOf(content.getAuthorId()));
        page.setCommentStatus(content.getAllowComment());
        page.setParent(Long.valueOf(content.getParent()));
        page.setMenuOrder(content.getOrder());
        return page;
    }

    public PageDTO toPageDto() {
        PageDTO dto = new PageDTO();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
