package dev.sunriseydy.blog.site.app.service;

import dev.sunriseydy.blog.site.api.dto.SiteInfoDTO;

/**
 * @author SunriseYDY
 * @date 2022-05-12 16:40
 */
public interface SiteInfoService {
    SiteInfoDTO getSiteInfo();

    SiteInfoDTO updateSiteInfo();

    void deleteSiteInfo();
}
