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
@TableName("wp_comments")
public class WpComments implements Serializable {

    private static final long serialVersionUID = -5739546821319206958L;

    @TableId(value = "comment_ID", type = IdType.AUTO)
    private Long commentId;

    @TableField("comment_post_ID")
    private Long commentPostId;

    @TableField("comment_author")
    private String commentAuthor;

    @TableField("comment_author_email")
    private String commentAuthorEmail;

    @TableField("comment_author_url")
    private String commentAuthorUrl;

    @TableField("comment_author_IP")
    private String commentAuthorIp;

    @TableField("comment_date")
    private LocalDateTime commentDate;

    @TableField("comment_date_gmt")
    private LocalDateTime commentDateGmt;

    @TableField("comment_content")
    private String commentContent;

    @TableField("comment_karma")
    private Integer commentKarma;

    @TableField("comment_approved")
    private String commentApproved;

    @TableField("comment_agent")
    private String commentAgent;

    @TableField("comment_type")
    private String commentType;

    @TableField("comment_parent")
    private Long commentParent;

    @TableField("user_id")
    private Long userId;

    @TableField("comment_mail_notify")
    private Integer commentMailNotify;


    public static final String COMMENT_ID = "comment_ID";

    public static final String COMMENT_POST_ID = "comment_post_ID";

    public static final String COMMENT_AUTHOR = "comment_author";

    public static final String COMMENT_AUTHOR_EMAIL = "comment_author_email";

    public static final String COMMENT_AUTHOR_URL = "comment_author_url";

    public static final String COMMENT_AUTHOR_IP = "comment_author_IP";

    public static final String COMMENT_DATE = "comment_date";

    public static final String COMMENT_DATE_GMT = "comment_date_gmt";

    public static final String COMMENT_CONTENT = "comment_content";

    public static final String COMMENT_KARMA = "comment_karma";

    public static final String COMMENT_APPROVED = "comment_approved";

    public static final String COMMENT_AGENT = "comment_agent";

    public static final String COMMENT_TYPE = "comment_type";

    public static final String COMMENT_PARENT = "comment_parent";

    public static final String USER_ID = "user_id";

    public static final String COMMENT_MAIL_NOTIFY = "comment_mail_notify";

}
