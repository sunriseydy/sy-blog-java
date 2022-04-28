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
@TableName("wp_terms")
public class WpTerms implements Serializable {

    private static final long serialVersionUID = -369454141694528958L;

    @TableId(value = "term_id", type = IdType.AUTO)
    private Long termId;

    @TableField("name")
    private String name;

    @TableField("slug")
    private String slug;

    @TableField("term_group")
    private Long termGroup;


    public static final String TERM_ID = "term_id";

    public static final String NAME = "name";

    public static final String SLUG = "slug";

    public static final String TERM_GROUP = "term_group";

}
