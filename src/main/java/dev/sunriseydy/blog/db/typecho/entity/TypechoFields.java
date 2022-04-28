package dev.sunriseydy.blog.db.typecho.entity;

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
 * @since 2022-04-28 13:37:57
 */
@Getter
@Setter
@TableName("typecho_fields")
public class TypechoFields implements Serializable {

    private static final long serialVersionUID = 2839546825621440507L;

    private Integer cid;

    private String name;

    @TableField("type")
    private String type;

    @TableField("str_value")
    private String strValue;

    @TableField("int_value")
    private Integer intValue;

    @TableField("float_value")
    private Float floatValue;


    public static final String CID = "cid";

    public static final String NAME = "name";

    public static final String TYPE = "type";

    public static final String STR_VALUE = "str_value";

    public static final String INT_VALUE = "int_value";

    public static final String FLOAT_VALUE = "float_value";

}
