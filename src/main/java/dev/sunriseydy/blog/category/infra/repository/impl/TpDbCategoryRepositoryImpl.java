package dev.sunriseydy.blog.category.infra.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import dev.sunriseydy.blog.category.api.dto.CategoryDTO;
import dev.sunriseydy.blog.category.domain.repository.CategoryRepository;
import dev.sunriseydy.blog.category.domain.vo.TpDbCategoryVO;
import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import dev.sunriseydy.blog.common.exception.CommonException;
import dev.sunriseydy.blog.db.typecho.entity.TypechoMetas;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoMetasMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SunriseYDY
 * @date 2022-04-30 13:23
 */
@Repository
@Slf4j
@ConditionalOnProperty(name = BlogSourceTypeConstant.PROPERTY_NAME, havingValue = BlogSourceTypeConstant.SOURCE_TYPE_TP_DB)
public class TpDbCategoryRepositoryImpl implements CategoryRepository {

    @Resource
    private TypechoMetasMapper typechoMetasMapper;

    @Override
    public List<Long> getCategoryIdList() {
        return typechoMetasMapper.selectList(new QueryWrapper<TypechoMetas>().lambda()
                .select(TypechoMetas::getMid)
                .eq(TypechoMetas::getType, TypechoMetas.METAS_TYPE_CATEGORY)
        ).stream().map(TypechoMetas::getMid).map(Long::valueOf).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        log.info("get that category:{}", id);

        TypechoMetas typechoMetas = typechoMetasMapper.selectById(id);
        TpDbCategoryVO tpDbCategoryVO = TpDbCategoryVO.fromTypechoMetas(typechoMetas);
        if (tpDbCategoryVO == null) {
            throw new CommonException("资源不存在");
        }
        return tpDbCategoryVO.toCategoryDTO();
    }

    @Override
    public CategoryDTO updateCategoryById(Long id) {
        log.info("update that category:{}", id);
        return this.getCategoryById(id);
    }

    @Override
    public void deleteCategoryById(Long id) {
        log.info("delete that category:{}", id);
    }
}
