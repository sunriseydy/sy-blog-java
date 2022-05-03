package dev.sunriseydy.blog.post.domain.vo;

import dev.sunriseydy.blog.db.typecho.entity.TypechoContents;
import dev.sunriseydy.blog.post.api.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-29 19:04
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TpDbPostVO {

    public static final String READ_MORE = "<!--more-->\n";

    public static final String MARKDOWN_MARK = "<!--markdown-->";

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
     * object	The title for the object. Context: view, edit, embed
     */
    private String titleString;

    /**
     * object	The content for the object. Context: view, edit
     */
    private String contentString;

    /**
     * integer	The ID for the author of the object. Context: view, edit, embed
     *
     */
    private Long author;

    /**
     * 摘要需要手动截取 '<!--more-->'
     */
    private String excerptString;

    /**
     * 需要查field表的‘featured_media_url’
     */
    private String featuredMediaUrl;

    /**
     * string	Whether or not comments are open on the object. Context: view, edit One of: open, closed
     */
    private String commentStatus;

    /**
     * 需要单独查询
     *
     */
    private List<Long> categories;

    /**
     * 需要单独查询
     *
     */
    private List<Long> tags;

    private String renderType;

    public static TpDbPostVO fromTypechoContent(TypechoContents content) {
        if (content == null) {
            return null;
        }
        TpDbPostVO post = new TpDbPostVO();
        post.setId(Long.valueOf(content.getCid()));
        post.setDate(new Date(content.getCreated() * 1000L));
        post.setModified(new Date(content.getModified() * 1000L));
        post.setSlug(content.getSlug());
        post.setStatus(content.getStatus());
        post.setType(content.getType());
        post.setTitleString(content.getTitle());
        post.setContentString(content.getText());
        post.setAuthor(Long.valueOf(content.getAuthorId()));
        post.setCommentStatus(content.getAllowComment());
        return post;
    }

    public PostDTO toPostDto() {
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(this, postDTO);
        return postDTO;
    }
}
