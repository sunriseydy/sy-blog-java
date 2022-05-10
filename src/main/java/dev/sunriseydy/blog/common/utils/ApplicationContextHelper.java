package dev.sunriseydy.blog.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractRefreshableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

/**
 * see io.choerodon.core.convertor.ApplicationContextHelper
 *
 * @author SunriseYDY
 * @date 2022-04-24 14:51
 */
@Slf4j
@Component
public class ApplicationContextHelper implements ApplicationContextAware {
    private static DefaultListableBeanFactory springFactory;
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setContext(applicationContext);
        if (applicationContext instanceof AbstractRefreshableApplicationContext) {
            AbstractRefreshableApplicationContext springContext = (AbstractRefreshableApplicationContext)applicationContext;
            setFactory((DefaultListableBeanFactory)springContext.getBeanFactory());
        } else if (applicationContext instanceof GenericApplicationContext) {
            GenericApplicationContext springContext = (GenericApplicationContext)applicationContext;
            setFactory(springContext.getDefaultListableBeanFactory());
        }
    }

    private static void setContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    private static void setFactory(DefaultListableBeanFactory springFactory) {
        ApplicationContextHelper.springFactory = springFactory;
    }

    public static DefaultListableBeanFactory getSpringFactory() {
        return springFactory;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(Class<T> type, String beanName) {
        T obj;
        if (beanName != null) {
            try {
                obj = getContext().getBean(beanName, type);
            } catch (NoSuchBeanDefinitionException var4) {
                obj = getContext().getBean(type);
                log.warn("getBean by beanName [{}] not found, then getBean by type.", beanName);
            }
        } else {
            obj = getContext().getBean(type);
        }

        return obj;
    }

    public static <T> T getBean(Class<T> type) {
        return getBean(type, null);
    }
}
