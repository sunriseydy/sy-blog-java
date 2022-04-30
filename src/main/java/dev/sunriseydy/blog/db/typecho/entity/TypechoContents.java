package dev.sunriseydy.blog.db.typecho.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author SunriseYDY
 * @since 2022-04-28 13:37:57
 */
@Getter
@Setter
@TableName("typecho_contents")
public class TypechoContents implements Serializable {

    private static final long serialVersionUID = -38006243728566920L;

    public static final String CONTENT_STATUS_PUBLISH = "publish";

    public static final String CONTENT_TYPE_POST = "post";

    public static final String CONTENT_TYPE_PAGE = "page";

    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    @TableField("title")
    private String title;

    @TableField("slug")
    private String slug;

    @TableField("created")
    private Integer created;

    @TableField("modified")
    private Integer modified;

    @TableField("text")
    private String text;

    @TableField("`order`")
    private Integer order;

    @TableField("authorId")
    private Integer authorId;

    @TableField("template")
    private String template;

    @TableField("type")
    private String type;

    @TableField("status")
    private String status;

    @TableField("password")
    private String password;

    @TableField("commentsNum")
    private Integer commentsNum;

    @TableField("allowComment")
    private String allowComment;

    @TableField("allowPing")
    private String allowPing;

    @TableField("allowFeed")
    private String allowFeed;

    @TableField("parent")
    private Integer parent;


    public static final String CID = "cid";

    public static final String TITLE = "title";

    public static final String SLUG = "slug";

    public static final String CREATED = "created";

    public static final String MODIFIED = "modified";

    public static final String TEXT = "text";

    public static final String ORDER = "`order`";

    public static final String AUTHORID = "authorId";

    public static final String TEMPLATE = "template";

    public static final String TYPE = "type";

    public static final String STATUS = "status";

    public static final String PASSWORD = "password";

    public static final String COMMENTSNUM = "commentsNum";

    public static final String ALLOWCOMMENT = "allowComment";

    public static final String ALLOWPING = "allowPing";

    public static final String ALLOWFEED = "allowFeed";

    public static final String PARENT = "parent";

}
