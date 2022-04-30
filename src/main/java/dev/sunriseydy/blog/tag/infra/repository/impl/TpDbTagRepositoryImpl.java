package dev.sunriseydy.blog.tag.infra.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import dev.sunriseydy.blog.common.exception.CommonException;
import dev.sunriseydy.blog.db.typecho.entity.TypechoMetas;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoMetasMapper;
import dev.sunriseydy.blog.tag.api.dto.TagDTO;
import dev.sunriseydy.blog.tag.domain.repository.TagRepository;
import dev.sunriseydy.blog.tag.domain.vo.TpDbTagVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SunriseYDY
 * @date 2022-04-30 12:25
 */
@Slf4j
@Repository
@ConditionalOnProperty(name = BlogSourceTypeConstant.PROPERTY_NAME, havingValue = BlogSourceTypeConstant.SOURCE_TYPE_TP_DB)
public class TpDbTagRepositoryImpl implements TagRepository {

    @Resource
    private TypechoMetasMapper typechoMetasMapper;

    @Override
    public List<Long> getTagIdList() {
        return typechoMetasMapper.selectList(new QueryWrapper<TypechoMetas>().lambda()
                .select(TypechoMetas::getMid)
                .eq(TypechoMetas::getType, TypechoMetas.METAS_TYPE_TAG)
        ).stream().map(TypechoMetas::getMid).map(Long::valueOf).collect(Collectors.toList());
    }

    @Override
    public TagDTO getTagById(Long id) {
        log.info("get that tag:{}", id);

        TypechoMetas typechoMetas = typechoMetasMapper.selectById(id);
        TpDbTagVO tpDbTagVO = TpDbTagVO.fromTypechoMetas(typechoMetas);
        if (tpDbTagVO == null) {
            throw new CommonException("资源不存在");
        }
        return tpDbTagVO.toTagDTO();
    }

    @Override
    public TagDTO updateTagById(Long id) {
        log.info("update that tag:{}", id);
        return this.getTagById(id);
    }

    @Override
    public void deleteTagById(Long id) {
        log.info("delete that tag:{}", id);
    }
}
