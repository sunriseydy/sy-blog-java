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
@TableName("typecho_options")
public class TypechoOptions implements Serializable {

    private static final long serialVersionUID = -6676372683950003279L;

    private String name;

    private Integer user;

    @TableField("value")
    private String value;


    public static final String NAME = "name";

    public static final String USER = "user";

    public static final String VALUE = "value";

}
