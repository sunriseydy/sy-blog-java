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
@TableName("wp_commentmeta")
public class WpCommentmeta implements Serializable {

    private static final long serialVersionUID = -4850256249270952285L;

    @TableId(value = "meta_id", type = IdType.AUTO)
    private Long metaId;

    @TableField("comment_id")
    private Long commentId;

    @TableField("meta_key")
    private String metaKey;

    @TableField("meta_value")
    private String metaValue;


    public static final String META_ID = "meta_id";

    public static final String COMMENT_ID = "comment_id";

    public static final String META_KEY = "meta_key";

    public static final String META_VALUE = "meta_value";

}
