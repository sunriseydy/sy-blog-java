package dev.sunriseydy.blog.site.api.controller;

import dev.sunriseydy.blog.common.Result;
import dev.sunriseydy.blog.common.annotion.BasicAuth;
import dev.sunriseydy.blog.site.api.dto.SiteInfoDTO;
import dev.sunriseydy.blog.site.app.service.SiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SunriseYDY
 * @date 2022-05-12 16:42
 */
@RestController
@RequestMapping("/site-info")
public class SiteInfoController {

    @Autowired
    private SiteInfoService service;

    @GetMapping
    public Result<SiteInfoDTO> getSiteInfo() {
        return Result.ok(service.getSiteInfo());
    }

    @BasicAuth
    @PutMapping
    public Result<SiteInfoDTO>  updateSiteInfo() {
        return Result.ok(service.updateSiteInfo());
    }

}
