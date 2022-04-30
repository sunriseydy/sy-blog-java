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
@TableName("typecho_users")
public class TypechoUsers implements Serializable {

    private static final long serialVersionUID = 2526024305124405028L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;

    @TableField("mail")
    private String mail;

    @TableField("url")
    private String url;

    @TableField("screenName")
    private String screenName;

    @TableField("created")
    private Integer created;

    @TableField("activated")
    private Integer activated;

    @TableField("logged")
    private Integer logged;

    @TableField("`group`")
    private String group;

    @TableField("authCode")
    private String authCode;


    public static final String UID = "uid";

    public static final String NAME = "name";

    public static final String PASSWORD = "password";

    public static final String MAIL = "mail";

    public static final String URL = "url";

    public static final String SCREENNAME = "screenName";

    public static final String CREATED = "created";

    public static final String ACTIVATED = "activated";

    public static final String LOGGED = "logged";

    public static final String GROUP = "group";

    public static final String AUTHCODE = "authCode";

}
