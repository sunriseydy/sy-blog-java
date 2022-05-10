package dev.sunriseydy.blog.page.app.service;

import dev.sunriseydy.blog.page.api.dto.PageDTO;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-05-10 17:31
 */
public interface PageService {

    List<PageDTO> getPageList();

    PageDTO getPageById(Long id);

    PageDTO getPageBySlug(String slug);

    PageDTO updatePageById(Long id);

    void deletePageById(Long id);
}
