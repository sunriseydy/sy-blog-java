package dev.sunriseydy.wp.post.app.service;

import dev.sunriseydy.wp.post.api.dto.PostDTO;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-03-21 11:45
 */
public interface PostService {

    List<PostDTO> getPostIdList();

    List<PostDTO> getPostList();

    PostDTO getPostById(Long id);
}
