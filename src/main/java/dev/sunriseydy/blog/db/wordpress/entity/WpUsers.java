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
@TableName("wp_users")
public class WpUsers implements Serializable {

    private static final long serialVersionUID = -9053063501376974347L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("user_login")
    private String userLogin;

    @TableField("user_pass")
    private String userPass;

    @TableField("user_nicename")
    private String userNicename;

    @TableField("user_email")
    private String userEmail;

    @TableField("user_url")
    private String userUrl;

    @TableField("user_registered")
    private LocalDateTime userRegistered;

    @TableField("user_activation_key")
    private String userActivationKey;

    @TableField("user_status")
    private Integer userStatus;

    @TableField("display_name")
    private String displayName;


    public static final String ID = "ID";

    public static final String USER_LOGIN = "user_login";

    public static final String USER_PASS = "user_pass";

    public static final String USER_NICENAME = "user_nicename";

    public static final String USER_EMAIL = "user_email";

    public static final String USER_URL = "user_url";

    public static final String USER_REGISTERED = "user_registered";

    public static final String USER_ACTIVATION_KEY = "user_activation_key";

    public static final String USER_STATUS = "user_status";

    public static final String DISPLAY_NAME = "display_name";

}
