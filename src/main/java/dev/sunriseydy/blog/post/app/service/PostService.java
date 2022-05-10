package dev.sunriseydy.blog.post.app.service;

import dev.sunriseydy.blog.common.vo.PageVO;
import dev.sunriseydy.blog.post.api.dto.PostDTO;

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

    void generatePostMetaCache();

}
