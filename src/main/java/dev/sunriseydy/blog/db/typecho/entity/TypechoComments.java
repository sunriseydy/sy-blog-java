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
@TableName("typecho_comments")
public class TypechoComments implements Serializable {

    private static final long serialVersionUID = -8916114595488850355L;

    @TableId(value = "coid", type = IdType.AUTO)
    private Integer coid;

    @TableField("cid")
    private Integer cid;

    @TableField("created")
    private Integer created;

    @TableField("author")
    private String author;

    @TableField("authorId")
    private Integer authorId;

    @TableField("ownerId")
    private Integer ownerId;

    @TableField("mail")
    private String mail;

    @TableField("url")
    private String url;

    @TableField("ip")
    private String ip;

    @TableField("agent")
    private String agent;

    @TableField("text")
    private String text;

    @TableField("type")
    private String type;

    @TableField("status")
    private String status;

    @TableField("parent")
    private Integer parent;


    public static final String COID = "coid";

    public static final String CID = "cid";

    public static final String CREATED = "created";

    public static final String AUTHOR = "author";

    public static final String AUTHORID = "authorId";

    public static final String OWNERID = "ownerId";

    public static final String MAIL = "mail";

    public static final String URL = "url";

    public static final String IP = "ip";

    public static final String AGENT = "agent";

    public static final String TEXT = "text";

    public static final String TYPE = "type";

    public static final String STATUS = "status";

    public static final String PARENT = "parent";

}
