package dev.sunriseydy.blog.post.app.service;

import dev.sunriseydy.blog.common.vo.PageVO;
import dev.sunriseydy.blog.post.api.dto.PostMeta;

import java.util.Set;

/**
 * @author SunriseYDY
 * @date 2022-05-10 15:13
 */
public interface PostMetaService {

    void generatePostMetaCache();

    Set<PostMeta> getAllPostMetas();

    Long getPostIdBySlug(String slug);

    PageVO<PostMeta> getPostMetaByPage(long page, long pageSize);
}
