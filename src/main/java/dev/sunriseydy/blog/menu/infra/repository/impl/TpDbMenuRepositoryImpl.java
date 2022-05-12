package dev.sunriseydy.blog.menu.infra.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import dev.sunriseydy.blog.db.typecho.entity.TypechoOptions;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoOptionsMapper;
import dev.sunriseydy.blog.menu.api.dto.Menu;
import dev.sunriseydy.blog.menu.api.dto.MenuDTO;
import dev.sunriseydy.blog.menu.domain.repository.MenuRepository;
import dev.sunriseydy.blog.menu.domain.vo.TpDbMenuVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-05-12 15:16
 */
@Slf4j
@Repository
@ConditionalOnProperty(name = BlogSourceTypeConstant.PROPERTY_NAME, havingValue = BlogSourceTypeConstant.SOURCE_TYPE_TP_DB)
public class TpDbMenuRepositoryImpl implements MenuRepository {

    @Resource
    private TypechoOptionsMapper typechoOptionsMapper;

    @Override
    public List<Long> getMenuIdList() {
        return null;
    }

    @Override
    public MenuDTO getMenuById(Long id) {
        return null;
    }

    @Override
    public MenuDTO updateMenuById(Long id) {
        return null;
    }

    @Override
    public void deleteMenuById(Long id) {

    }

    @SneakyThrows
    @Override
    public List<Menu> getMenusByName(String name) {
        TypechoOptions typechoOptions = typechoOptionsMapper.selectOne(new QueryWrapper<TypechoOptions>().lambda()
                .eq(TypechoOptions::getName, TpDbMenuVO.MENU_OPTIONS)
        );
        if (typechoOptions == null) {
            return null;
        }
        String value = typechoOptions.getValue();
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TpDbMenuVO tpDbMenuVO = objectMapper.readValue(value, TpDbMenuVO.class);
        return null;
    }
}
