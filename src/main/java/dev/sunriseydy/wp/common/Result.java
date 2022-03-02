package dev.sunriseydy.wp.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author SunriseYDY
 * @date 2022-03-01 15:59
 */
@Data
@Builder
public class Result<T> {

    private boolean success;

    private String message;

    private T data;

    public boolean isSuccess() {
        return this.success;
    }

    public static <R> Result<R> ok(R data) {
        return Result.<R>builder().success(true).data(data).build();
    }

    public static <R> Result<R> ok() {
        return Result.ok(null);
    }

    public static <R> Result<R> error(String message) {
        return Result.<R>builder().success(false).message(message).build();
    }

    public static <R> Result<R> error(Exception e) {
        return Result.<R>builder().success(false).message(e.getMessage()).build();
    }
}
