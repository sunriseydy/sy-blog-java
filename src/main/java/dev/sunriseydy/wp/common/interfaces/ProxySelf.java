package dev.sunriseydy.wp.common.interfaces;

import org.springframework.aop.framework.AopContext;

/**
 * @author SunriseYDY
 * @date 2022-03-18 18:35
 */
public interface ProxySelf<T> {
    default T self() {
        return (T) AopContext.currentProxy();
    }
}