package dev.sunriseydy.blog.common.exception.handler;

import dev.sunriseydy.blog.common.Result;
import dev.sunriseydy.blog.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

/**
 * @author SunriseYDY
 * @date 2022-03-02 10:28
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseStatus(HttpStatus.OK)
public class CustomExceptionHandler {
    @ExceptionHandler({CommonException.class})
    public Result<?> commonExceptionHandler(final CommonException e) {
        log.error("-----> 系统异常:", e);
        return Result.error(e);
    }

    /**
     * IllegalArgumentException 异常处理，通常由 Spring 的 {@link org.springframework.util.Assert} 抛出
     *
     * @param e IllegalArgumentException
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<?> illegalArgumentExceptionHandler(final IllegalArgumentException e) {
        log.error("-----> 参数异常：", e);
        return Result.error(e);
    }

    @ExceptionHandler(ValidationException.class)
    public Result<?> badArgumentExceptionHandler(ValidationException e) {
        log.error("-----> 参数异常：", e);
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) e;
            String message = exs.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(","));
            return Result.error(message);
        }
        return Result.error(e);
    }

    @ExceptionHandler({Exception.class})
    public Result<?> globalExceptionHandler(final Exception e) {
        log.error("-----> 全局异常:", e);
        return Result.error(e);
    }
}
