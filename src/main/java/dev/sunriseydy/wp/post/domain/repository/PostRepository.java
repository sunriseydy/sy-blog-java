package dev.sunriseydy.wp.post.domain.repository;

import dev.sunriseydy.wp.post.api.dto.PostDTO;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-03-17 14:06
 */
public interface PostRepository {

    List<PostDTO> getPostIdList();

    PostDTO getPostById(Long id);
}
