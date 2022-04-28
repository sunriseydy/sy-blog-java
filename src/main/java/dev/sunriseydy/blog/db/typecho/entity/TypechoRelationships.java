package dev.sunriseydy.blog.db.typecho.entity;

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
@TableName("typecho_relationships")
public class TypechoRelationships implements Serializable {

    private static final long serialVersionUID = 8475912467997071928L;

    private Integer cid;

    private Integer mid;


    public static final String CID = "cid";

    public static final String MID = "mid";

}
