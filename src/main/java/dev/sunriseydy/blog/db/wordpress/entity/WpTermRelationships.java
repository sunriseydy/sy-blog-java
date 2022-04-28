package dev.sunriseydy.blog.db.wordpress.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("wp_term_relationships")
public class WpTermRelationships implements Serializable {

    private static final long serialVersionUID = 6778887512076008489L;

    private Long objectId;

    private Long termTaxonomyId;

    @TableField("term_order")
    private Integer termOrder;


    public static final String OBJECT_ID = "object_id";

    public static final String TERM_TAXONOMY_ID = "term_taxonomy_id";

    public static final String TERM_ORDER = "term_order";

}
