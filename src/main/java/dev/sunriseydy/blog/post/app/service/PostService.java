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

    /**
     * 从 postMeta 缓存中获取全部文章数组
     * <p>
     *     仅包含基本的 post 数据，即缓存中的post数据，不包括详细的用户、分类、标签等信息
     * </p>
     * @return
     */
    List<PostDTO> getPostList();

    PageVO<PostDTO> getPostPage(int page, int pageSize);

    PostDTO getPostById(Long id);

    PostDTO getPostBySlug(String slug);

    PageVO<PostDTO> getPostsByCategoryId(Long id, int page, int pageSize);

    PageVO<PostDTO> getPostsByTagId(Long id, int page, int pageSize);

    /**
     * 搜索文章内容，不区分大小写
     * @param search
     * @param page
     * @param pageSize
     * @return
     */
    PageVO<PostDTO> searchPosts(String search, int page, int pageSize);

    PostDTO updatePostById(Long id);

    void deletePostById(Long id);

}
