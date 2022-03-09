package dev.sunriseydy.wp;

import dev.sunriseydy.wp.common.properties.SyWpProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SyWpJavaApplicationTests {

    @Autowired
    private SyWpProperties syWpProperties;

    @Test
    void contextLoads() {
    }

    @Test
    void testProperty() {
        System.out.println(syWpProperties.getSourceType());
        System.out.println(syWpProperties.getRestApiHost());
    }
}
