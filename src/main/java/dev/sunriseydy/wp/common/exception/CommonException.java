package dev.sunriseydy.wp.common.exception;

/**
 * @author SunriseYDY
 * @date 2022-03-01 15:25
 */
public class CommonException extends RuntimeException {

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

}
