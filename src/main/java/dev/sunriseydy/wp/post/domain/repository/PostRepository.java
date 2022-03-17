package dev.sunriseydy.wp.post.domain.repository;

import dev.sunriseydy.wp.post.domain.entity.Post;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-03-17 14:06
 */
public interface PostRepository {

    List<Post> getPostIdList();

    Post getPostById();
}
