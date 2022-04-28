package dev.sunriseydy.blog.db.wordpress.entity;

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
 * @since 2022-04-28 13:51:56
 */
@Getter
@Setter
@TableName("wp_usermeta")
public class WpUsermeta implements Serializable {

    private static final long serialVersionUID = -6759422821232494077L;

    @TableId(value = "umeta_id", type = IdType.AUTO)
    private Long umetaId;

    @TableField("user_id")
    private Long userId;

    @TableField("meta_key")
    private String metaKey;

    @TableField("meta_value")
    private String metaValue;


    public static final String UMETA_ID = "umeta_id";

    public static final String USER_ID = "user_id";

    public static final String META_KEY = "meta_key";

    public static final String META_VALUE = "meta_value";

}
