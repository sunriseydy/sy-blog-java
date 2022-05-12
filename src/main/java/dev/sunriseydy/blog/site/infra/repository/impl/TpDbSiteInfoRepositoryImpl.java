package dev.sunriseydy.blog.site.infra.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import dev.sunriseydy.blog.common.constants.BlogSourceTypeConstant;
import dev.sunriseydy.blog.db.typecho.entity.TypechoOptions;
import dev.sunriseydy.blog.db.typecho.mapper.TypechoOptionsMapper;
import dev.sunriseydy.blog.site.api.dto.SiteInfoDTO;
import dev.sunriseydy.blog.site.domain.repository.SiteInfoRepository;
import dev.sunriseydy.blog.site.domain.vo.TpDbSiteInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author SunriseYDY
 * @date 2022-05-12 16:00
 */
@Slf4j
@Repository
@ConditionalOnProperty(name = BlogSourceTypeConstant.PROPERTY_NAME, havingValue = BlogSourceTypeConstant.SOURCE_TYPE_TP_DB)
public class TpDbSiteInfoRepositoryImpl implements SiteInfoRepository {

    @Resource
    private TypechoOptionsMapper optionsMapper;

    @Override
    public SiteInfoDTO getSiteInfo() {
        log.info("get site info");
        Map<String, String> optionMap = optionsMapper.selectList(new QueryWrapper<TypechoOptions>().lambda()
                        .in(TypechoOptions::getName,
                                TpDbSiteInfoVO.OPTION_TITLE,
                                TpDbSiteInfoVO.OPTION_DESCRIPTION,
                                TpDbSiteInfoVO.OPTION_URL,
                                TpDbSiteInfoVO.OPTION_IMAGE)
                ).stream()
                .collect(Collectors.toMap(TypechoOptions::getName, TypechoOptions::getValue));
        return SiteInfoDTO.builder()
                .title(optionMap.get(TpDbSiteInfoVO.OPTION_TITLE))
                .description(optionMap.get(TpDbSiteInfoVO.OPTION_DESCRIPTION))
                .url(optionMap.get(TpDbSiteInfoVO.OPTION_URL))
                .featuredMediaUrl(optionMap.get(TpDbSiteInfoVO.OPTION_IMAGE))
                .build();
    }

    @Override
    public SiteInfoDTO updateSiteInfo() {
        log.info("update site info");
        return this.getSiteInfo();
    }

    @Override
    public void deleteSiteInfo() {
        log.info("delete site info");
    }
}
