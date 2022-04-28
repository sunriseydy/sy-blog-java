package dev.sunriseydy.blog.db.typecho.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import dev.sunriseydy.blog.common.constants.BlogDataSourceConstant;
import dev.sunriseydy.blog.db.typecho.entity.TypechoContents;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SunriseYDY
 * @since 2022-04-28 13:37:57
 */
@DS(BlogDataSourceConstant.TYPECHO)
public interface TypechoContentsMapper extends BaseMapper<TypechoContents> {

}
