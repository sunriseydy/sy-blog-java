package dev.sunriseydy.blog.user.infra.repository.impl;

import dev.sunriseydy.blog.user.api.dto.UserDTO;
import dev.sunriseydy.blog.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-04-26 17:52
 */
@SpringBootTest
class WpApiUserRepositoryImplTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void getUserIdList() {
        List<Long> userIdList = userRepository.getUserIdList();
        System.out.println(userIdList);
    }

    @Test
    void getUserById() {
        UserDTO user = userRepository.getUserById(1L);
        System.out.println(user);
    }

    @Test
    void updateUserById() {
        userRepository.updateUserById(1L);
    }

    @Test
    void deleteUserById() {
        userRepository.deleteUserById(1L);
    }
}