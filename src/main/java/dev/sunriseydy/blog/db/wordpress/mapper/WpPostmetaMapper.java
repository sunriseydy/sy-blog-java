package dev.sunriseydy.blog.db.wordpress.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import dev.sunriseydy.blog.common.constants.BlogDataSourceConstant;
import dev.sunriseydy.blog.db.wordpress.entity.WpPostmeta;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SunriseYDY
 * @since 2022-04-28 13:51:56
 */
@DS(BlogDataSourceConstant.WORDPRESS)
public interface WpPostmetaMapper extends BaseMapper<WpPostmeta> {

}
