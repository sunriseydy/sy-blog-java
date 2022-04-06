package dev.sunriseydy.wp.post.app.service;

import dev.sunriseydy.wp.common.vo.PageVO;
import dev.sunriseydy.wp.post.api.dto.PostDTO;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-03-21 11:45
 */
public interface PostService {

    List<Long> getPostIdList();

    List<PostDTO> getPostList();

    PageVO<PostDTO> getPostPage(int page, int pageSize);

    PostDTO getPostById(Long id);

    PostDTO updatePostById(Long id);

    void deletePostById(Long id);

}
