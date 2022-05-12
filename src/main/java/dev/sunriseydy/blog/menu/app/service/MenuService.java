package dev.sunriseydy.blog.menu.app.service;

import dev.sunriseydy.blog.menu.api.dto.Menu;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-05-12 15:04
 */
public interface MenuService {
    List<Menu> getMenusByName(String name);
}
