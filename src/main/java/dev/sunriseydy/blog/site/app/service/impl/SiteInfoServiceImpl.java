package dev.sunriseydy.blog.site.app.service.impl;

import dev.sunriseydy.blog.site.api.dto.SiteInfoDTO;
import dev.sunriseydy.blog.site.app.service.SiteInfoService;
import dev.sunriseydy.blog.site.domain.repository.SiteInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author SunriseYDY
 * @date 2022-05-12 16:41
 */
@Slf4j
@Service
public class SiteInfoServiceImpl implements SiteInfoService {

    private final SiteInfoRepository siteInfoRepository;

    public SiteInfoServiceImpl(SiteInfoRepository siteInfoRepository) {
        this.siteInfoRepository = siteInfoRepository;
    }

    @Override
    public SiteInfoDTO getSiteInfo() {
        return this.siteInfoRepository.getSiteInfo();
    }

    @Override
    public SiteInfoDTO updateSiteInfo() {
        return this.siteInfoRepository.updateSiteInfo();
    }

    @Override
    public void deleteSiteInfo() {
        this.siteInfoRepository.deleteSiteInfo();
    }
}
