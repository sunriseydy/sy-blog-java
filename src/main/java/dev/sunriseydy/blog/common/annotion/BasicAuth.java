package dev.sunriseydy.blog.common.annotion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author SunriseYDY
 * @date 2022-03-24 16:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(security = { @SecurityRequirement(name = "basicScheme") })
public @interface BasicAuth {
    String value() default "";
}
