package dev.sunriseydy.blog.common.init;

import dev.sunriseydy.blog.category.domain.repository.CategoryRepository;
import dev.sunriseydy.blog.common.properties.SyBlogProperties;
import dev.sunriseydy.blog.menu.domain.repository.MenuItemRepository;
import dev.sunriseydy.blog.menu.domain.repository.MenuRepository;
import dev.sunriseydy.blog.page.domain.repository.PageRepository;
import dev.sunriseydy.blog.post.domain.repository.PostRepository;
import dev.sunriseydy.blog.tag.domain.repository.TagRepository;
import dev.sunriseydy.blog.user.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author SunriseYDY
 * @date 2022-04-26 15:50
 */
@Slf4j
@Component
public class CacheInit implements CommandLineRunner {

    @Autowired
    private SyBlogProperties syBlogProperties;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        if (syBlogProperties == null || Boolean.FALSE.equals(syBlogProperties.getInitCache())) {
            log.info("启动时不初始化缓存");
            return;
        }

        log.info("开始初始化缓存...");
        // 分类 category
        log.info("开始缓存分类");
        this.initCache(categoryRepository::getCategoryIdList, categoryRepository::updateCategoryById);
        log.info("结束缓存分类");
        // 标签 tag
        log.info("开始缓存标签");
        this.initCache(tagRepository::getTagIdList, tagRepository::updateTagById);
        log.info("结束缓存标签");
        // 作者 author
        log.info("开始缓存作者");
        this.initCache(userRepository::getUserIdList, userRepository::updateUserById);
        log.info("结束缓存作者");
        // 菜单 menu
        log.info("开始缓存菜单");
        this.initCache(menuItemRepository::getMenuItemIdList, menuItemRepository::updateMenuItemById);
        this.initCache(menuRepository::getMenuIdList, menuRepository::updateMenuById);
        log.info("结束缓存菜单");
        // 页面 page
        log.info("开始缓存页面");
        this.initCache(pageRepository::getPageIdList, pageRepository::updatePageById);
        log.info("结束缓存页面");
        // 文章 post
        log.info("开始缓存文章");
        this.initCache(postRepository::getPostIdList, postRepository::updatePostById);
        log.info("结束缓存文章");
    }

    private void initCache(Supplier<List<Long>> getObjectIds, Consumer<Long> cacheObjectById) {
        List<Long> ids = getObjectIds.get();
        if (CollectionUtils.isNotEmpty(ids)) {
            log.info("要缓存的数量：{}", ids.size());
            ids.forEach(cacheObjectById);
        } else {
            log.info("没有要缓存的对象");
        }
    }
}
