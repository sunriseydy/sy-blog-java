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
@TableName("typecho_metas")
public class TypechoMetas implements Serializable {

    private static final long serialVersionUID = -3061151868110063728L;

    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;

    @TableField("name")
    private String name;

    @TableField("slug")
    private String slug;

    @TableField("type")
    private String type;

    @TableField("description")
    private String description;

    @TableField("count")
    private Integer count;

    @TableField("`order`")
    private Integer order;

    @TableField("parent")
    private Integer parent;


    public static final String MID = "mid";

    public static final String NAME = "name";

    public static final String SLUG = "slug";

    public static final String TYPE = "type";

    public static final String DESCRIPTION = "description";

    public static final String COUNT = "count";

    public static final String ORDER = "`order`";

    public static final String PARENT = "parent";

}
