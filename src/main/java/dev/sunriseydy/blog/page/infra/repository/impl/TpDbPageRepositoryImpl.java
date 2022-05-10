package dev.sunriseydy.blog.page.infra.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import dev.sunriseydy.blog.common.constants.PostRenderTypeConstant;
import dev.sunriseydy.blog.common.constants.TypechoFieldName;
import dev.sunriseydy.blog.common.exception.CommonException;
import dev.sunriseydy.blog.db.typecho.entity.TypechoContents;
import dev.sunriseydy.blog.db.typecho.entity.TypechoFields;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoContentsMapper;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoFieldsMapper;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoMetasMapper;
import dev.sunriseydy.blog.page.api.dto.PageDTO;
import dev.sunriseydy.blog.page.domain.repository.PageRepository;
import dev.sunriseydy.blog.page.domain.vo.TpDbPageVO;
import dev.sunriseydy.blog.post.domain.vo.TpDbPostVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RegExUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SunriseYDY
 * @date 2022-05-10 17:09
 */
@Slf4j
@Repository
@ConditionalOnProperty(name = BlogSourceTypeConstant.PROPERTY_NAME, havingValue = BlogSourceTypeConstant.SOURCE_TYPE_TP_DB)
public class TpDbPageRepositoryImpl implements PageRepository {

    @Resource
    private TypechoContentsMapper typechoContentsMapper;

    @Resource
    private TypechoFieldsMapper typechoFieldsMapper;

    @Resource
    private TypechoMetasMapper typechoMetasMapper;

    @Override
    public List<Long> getPageIdList() {
        // 查询已发布的页面 id
        List<TypechoContents> typechoContents = typechoContentsMapper.selectList(new QueryWrapper<TypechoContents>().lambda()
                .select(TypechoContents::getCid)
                .eq(TypechoContents::getType, TypechoContents.CONTENT_TYPE_PAGE)
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
    public PageDTO getPageById(Long id) {
        log.info("get this page:{}", id);

        TypechoContents typechoContents = typechoContentsMapper.selectById(id);
        TpDbPageVO pageVO = TpDbPageVO.fromTypechoContent(typechoContents);
        if (pageVO == null) {
            throw new CommonException("资源不存在");
        }
        // 查询 featuredMediaUrlField
        TypechoFields featuredMediaUrlField = typechoFieldsMapper.selectOne(new QueryWrapper<TypechoFields>().lambda()
                .eq(TypechoFields::getCid, typechoContents.getCid())
                .eq(TypechoFields::getName, TypechoFieldName.FEATURED_MEDIA_URL));
        pageVO.setFeaturedMediaUrl(featuredMediaUrlField == null ? null : featuredMediaUrlField.getStrValue());

        // 设置渲染类型
        pageVO.setRenderType(pageVO.getContentString().startsWith(TpDbPostVO.MARKDOWN_MARK) ? PostRenderTypeConstant.MARKDOWN : PostRenderTypeConstant.HTML);
        // 去掉 markdown 标记
        pageVO.setContentString(RegExUtils.removeFirst(pageVO.getContentString(), TpDbPostVO.MARKDOWN_MARK));
        return pageVO.toPageDto();
    }

    @Override
    public PageDTO updatePageById(Long id) {
        log.info("update that page:{}", id);
        return this.getPageById(id);
    }

    @Override
    public void deletePageById(Long id) {
        log.info("delete that page:{}", id);
    }
}
