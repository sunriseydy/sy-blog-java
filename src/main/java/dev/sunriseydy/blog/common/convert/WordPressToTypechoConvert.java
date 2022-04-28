package dev.sunriseydy.blog.common.convert;

import dev.sunriseydy.blog.db.typecho.entity.TypechoContents;
import dev.sunriseydy.blog.db.typecho.entity.TypechoMetas;
import dev.sunriseydy.blog.db.typecho.entity.TypechoRelationships;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoContentsMapper;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoMetasMapper;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoRelationshipsMapper;
import dev.sunriseydy.blog.db.wordpress.mapper.WordPress2TypechoMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *     WordPress to Typecho
 * </p>
 * <p>
 *     目前只实现了 分类、标签、文章、页面 的转换
 * </p>
 *
 * @author SunriseYDY
 * @date 2022-04-28 13:44
 */
@Component
public class WordPressToTypechoConvert {
    @Resource
    private WordPress2TypechoMapper wordPress2TypechoMapper;

    @Resource
    private TypechoMetasMapper typechoMetasMapper;

    @Resource
    private TypechoRelationshipsMapper typechoRelationshipsMapper;

    @Resource
    private TypechoContentsMapper typechoContentsMapper;

    public String convert() {
        StringBuilder message = new StringBuilder();
        // 转换分类和标签
        wordPress2TypechoMapper.truncateMetas();
        List<TypechoMetas> typechoMetas = wordPress2TypechoMapper.selectTerms();
        if (CollectionUtils.isNotEmpty(typechoMetas)) {
            typechoMetas.forEach(typechoMetasMapper::insert);
            message.append("转换的分类和标签数量：" + typechoMetas.size());
            message.append("\n");
        }
        wordPress2TypechoMapper.truncateRelationships();
        List<TypechoRelationships> typechoRelationships = wordPress2TypechoMapper.selectRelationships();
        if (CollectionUtils.isNotEmpty(typechoRelationships)) {
            typechoRelationships.forEach(typechoRelationshipsMapper::insert);
            message.append("转换的分类标签和文章关联关系数量：" + typechoRelationships.size());
            message.append("\n");
        }
        // 转换文章和页面
        wordPress2TypechoMapper.truncateContents();
        List<TypechoContents> typechoContents = wordPress2TypechoMapper.selectContents();
        if (CollectionUtils.isNotEmpty(typechoContents)) {
            typechoContents.forEach(typechoContentsMapper::insert);
            message.append("转换的文章和页面数量：" + typechoContents.size());
            message.append("\n");
        }
        return message.toString();
    }
}
