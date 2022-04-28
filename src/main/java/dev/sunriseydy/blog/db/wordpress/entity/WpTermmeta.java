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
@TableName("wp_termmeta")
public class WpTermmeta implements Serializable {

    private static final long serialVersionUID = -3366832071559778954L;

    @TableId(value = "meta_id", type = IdType.AUTO)
    private Long metaId;

    @TableField("term_id")
    private Long termId;

    @TableField("meta_key")
    private String metaKey;

    @TableField("meta_value")
    private String metaValue;


    public static final String META_ID = "meta_id";

    public static final String TERM_ID = "term_id";

    public static final String META_KEY = "meta_key";

    public static final String META_VALUE = "meta_value";

}
