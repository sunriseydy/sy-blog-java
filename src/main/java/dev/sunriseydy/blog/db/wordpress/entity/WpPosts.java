package dev.sunriseydy.blog.db.wordpress.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author SunriseYDY
 * @since 2022-04-28 13:51:56
 */
@Getter
@Setter
@TableName("wp_posts")
public class WpPosts implements Serializable {

    private static final long serialVersionUID = 2806935820736750152L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("post_author")
    private Long postAuthor;

    @TableField("post_date")
    private LocalDateTime postDate;

    @TableField("post_date_gmt")
    private LocalDateTime postDateGmt;

    @TableField("post_content")
    private String postContent;

    @TableField("post_title")
    private String postTitle;

    @TableField("post_excerpt")
    private String postExcerpt;

    @TableField("post_status")
    private String postStatus;

    @TableField("comment_status")
    private String commentStatus;

    @TableField("ping_status")
    private String pingStatus;

    @TableField("post_password")
    private String postPassword;

    @TableField("post_name")
    private String postName;

    @TableField("to_ping")
    private String toPing;

    @TableField("pinged")
    private String pinged;

    @TableField("post_modified")
    private LocalDateTime postModified;

    @TableField("post_modified_gmt")
    private LocalDateTime postModifiedGmt;

    @TableField("post_content_filtered")
    private String postContentFiltered;

    @TableField("post_parent")
    private Long postParent;

    @TableField("guid")
    private String guid;

    @TableField("menu_order")
    private Integer menuOrder;

    @TableField("post_type")
    private String postType;

    @TableField("post_mime_type")
    private String postMimeType;

    @TableField("comment_count")
    private Long commentCount;


    public static final String ID = "ID";

    public static final String POST_AUTHOR = "post_author";

    public static final String POST_DATE = "post_date";

    public static final String POST_DATE_GMT = "post_date_gmt";

    public static final String POST_CONTENT = "post_content";

    public static final String POST_TITLE = "post_title";

    public static final String POST_EXCERPT = "post_excerpt";

    public static final String POST_STATUS = "post_status";

    public static final String COMMENT_STATUS = "comment_status";

    public static final String PING_STATUS = "ping_status";

    public static final String POST_PASSWORD = "post_password";

    public static final String POST_NAME = "post_name";

    public static final String TO_PING = "to_ping";

    public static final String PINGED = "pinged";

    public static final String POST_MODIFIED = "post_modified";

    public static final String POST_MODIFIED_GMT = "post_modified_gmt";

    public static final String POST_CONTENT_FILTERED = "post_content_filtered";

    public static final String POST_PARENT = "post_parent";

    public static final String GUID = "guid";

    public static final String MENU_ORDER = "menu_order";

    public static final String POST_TYPE = "post_type";

    public static final String POST_MIME_TYPE = "post_mime_type";

    public static final String COMMENT_COUNT = "comment_count";

}
