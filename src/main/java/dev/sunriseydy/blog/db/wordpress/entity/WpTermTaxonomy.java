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
@TableName("wp_term_taxonomy")
public class WpTermTaxonomy implements Serializable {

    private static final long serialVersionUID = 4035651844687717867L;

    @TableId(value = "term_taxonomy_id", type = IdType.AUTO)
    private Long termTaxonomyId;

    @TableField("term_id")
    private Long termId;

    @TableField("taxonomy")
    private String taxonomy;

    @TableField("description")
    private String description;

    @TableField("parent")
    private Long parent;

    @TableField("count")
    private Long count;


    public static final String TERM_TAXONOMY_ID = "term_taxonomy_id";

    public static final String TERM_ID = "term_id";

    public static final String TAXONOMY = "taxonomy";

    public static final String DESCRIPTION = "description";

    public static final String PARENT = "parent";

    public static final String COUNT = "count";

}
