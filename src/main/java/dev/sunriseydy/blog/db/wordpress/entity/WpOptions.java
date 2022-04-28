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
@TableName("wp_options")
public class WpOptions implements Serializable {

    private static final long serialVersionUID = 2787974327705850930L;

    @TableId(value = "option_id", type = IdType.AUTO)
    private Long optionId;

    @TableField("option_name")
    private String optionName;

    @TableField("option_value")
    private String optionValue;

    @TableField("autoload")
    private String autoload;


    public static final String OPTION_ID = "option_id";

    public static final String OPTION_NAME = "option_name";

    public static final String OPTION_VALUE = "option_value";

    public static final String AUTOLOAD = "autoload";

}
