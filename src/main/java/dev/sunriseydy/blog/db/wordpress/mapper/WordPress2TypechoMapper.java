package dev.sunriseydy.blog.db.wordpress.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import dev.sunriseydy.blog.common.constants.BlogDataSourceConstant;
import dev.sunriseydy.blog.db.typecho.entity.TypechoContents;
import dev.sunriseydy.blog.db.typecho.entity.TypechoMetas;
import dev.sunriseydy.blog.db.typecho.entity.TypechoRelationships;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-28 14:17
 */
@DS(BlogDataSourceConstant.WORDPRESS)
public interface WordPress2TypechoMapper {
    /**
     * 分类和标签
     * @return
     */
    List<TypechoMetas> selectTerms();

    /**
     * 关系
     * @return
     */
    List<TypechoRelationships> selectRelationships();

    /**
     * 文章和页面
     * @return
     */
    List<TypechoContents> selectContents();

    @DS(BlogDataSourceConstant.TYPECHO)
    @Delete("truncate typecho_metas")
    int truncateMetas();

    @DS(BlogDataSourceConstant.TYPECHO)
    @Delete("truncate typecho_relationships")
    int truncateRelationships();

    @DS(BlogDataSourceConstant.TYPECHO)
    @Delete("truncate typecho_contents")
    int truncateContents();
}
