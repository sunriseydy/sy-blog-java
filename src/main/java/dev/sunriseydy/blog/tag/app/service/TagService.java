package dev.sunriseydy.blog.tag.app.service;

import dev.sunriseydy.blog.tag.api.dto.TagDTO;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-05-10 22:00
 */
public interface TagService {

    List<TagDTO> getTagList();

    TagDTO updateTagById(Long id);
}
