package dev.sunriseydy.blog.post.infra.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import dev.sunriseydy.blog.common.constants.TypechoFieldName;
import dev.sunriseydy.blog.common.exception.CommonException;
import dev.sunriseydy.blog.db.typecho.entity.TypechoContents;
import dev.sunriseydy.blog.db.typecho.entity.TypechoFields;
import dev.sunriseydy.blog.db.typecho.entity.TypechoMetas;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoContentsMapper;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoFieldsMapper;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoMetasMapper;
import dev.sunriseydy.blog.post.api.dto.PostDTO;
import dev.sunriseydy.blog.post.domain.repository.PostRepository;
import dev.sunriseydy.blog.post.domain.vo.TpDbPostVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SunriseYDY
 * @date 2022-04-29 19:01
 */
@Slf4j
@Repository
@ConditionalOnProperty(name = BlogSourceTypeConstant.PROPERTY_NAME, havingValue = BlogSourceTypeConstant.SOURCE_TYPE_TP_DB)
public class TpDbPostRepositoryImpl implements PostRepository {

    @Resource
    private TypechoContentsMapper typechoContentsMapper;

    @Resource
    private TypechoFieldsMapper typechoFieldsMapper;

    @Resource
    private TypechoMetasMapper typechoMetasMapper;

    @Override
    public List<Long> getPostIdList() {
        // 查询已发布的文章 id
        List<TypechoContents> typechoContents = typechoContentsMapper.selectList(new QueryWrapper<TypechoContents>().lambda()
                .select(TypechoContents::getCid)
                .eq(TypechoContents::getType, TypechoContents.CONTENT_TYPE_POST)
                .eq(TypechoContents::getStatus, TypechoContents.CONTENT_STATUS_PUBLISH));
        if (typechoContents == null) {
            return Collections.emptyList();
        }
        return typechoContents.stream()
                .map(TypechoContents::getCid)
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(Long id) {
        log.info("get this post:{}", id);

        TypechoContents typechoContents = typechoContentsMapper.selectById(id);
        TpDbPostVO postVO = TpDbPostVO.fromTypechoContent(typechoContents);
        if (postVO == null) {
            throw new CommonException("资源不存在");
        }
        // 查询 featuredMediaUrlField
        TypechoFields featuredMediaUrlField = typechoFieldsMapper.selectOne(new QueryWrapper<TypechoFields>().lambda()
                .eq(TypechoFields::getCid, typechoContents.getCid())
                .eq(TypechoFields::getName, TypechoFieldName.FEATURED_MEDIA_URL));
        postVO.setFeaturedMediaUrl(featuredMediaUrlField == null ? null : featuredMediaUrlField.getStrValue());

        // 获取 tags 和 categories
        List<TypechoMetas> postMetas = typechoMetasMapper.getPostMetas(postVO.getId());
        if (CollectionUtils.isNotEmpty(postMetas)) {
            postVO.setCategories(postMetas.stream()
                    .filter(typechoMetas -> TypechoMetas.METAS_TYPE_CATEGORY.equals(typechoMetas.getType()))
                    .map(TypechoMetas::getMid)
                    .map(Long::valueOf)
                    .collect(Collectors.toList()));
            postVO.setTags(postMetas.stream()
                    .filter(typechoMetas -> TypechoMetas.METAS_TYPE_TAG.equals(typechoMetas.getType()))
                    .map(TypechoMetas::getMid)
                    .map(Long::valueOf)
                    .collect(Collectors.toList()));
        }
        // 获取excerpt
        String excerpt = StringUtils.substring(postVO.getContentString(), 0, 120);
        if (postVO.getContentString().contains(TpDbPostVO.READ_MORE)) {
            // 如果含有 read more 标签,则截取标签前的内容
            excerpt = StringUtils.substringBefore(postVO.getContentString(), TpDbPostVO.READ_MORE);
        }
        postVO.setExcerptString(excerpt);
        return postVO.toPostDto();
    }

    @Override
    public PostDTO updatePostById(Long id) {
        log.info("update this post:{}", id);

        return this.getPostById(id);
    }

    @Override
    public void deletePostById(Long id) {
        log.info("delete this post:{}", id);

    }
}
