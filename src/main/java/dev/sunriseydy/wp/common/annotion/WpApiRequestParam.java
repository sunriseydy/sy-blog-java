package dev.sunriseydy.wp.common.annotion;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 指定请求 wordpress api 时的参数
 * @author SunriseYDY
 * @date 2022-03-10 15:47
 */
@Target(ElementType.FIELD)
public @interface WpApiRequestParam {
    /**
     * 指定参数名，为空则取当前字段名
     * @return
     */
    @AliasFor("paramName")
    String value() default "";

    @AliasFor("value")
    String paramName() default "";

    /**
     * 是否忽略该字段
     * @return
     */
    boolean ignore() default false;

}
