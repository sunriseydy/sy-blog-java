package dev.sunriseydy.wp.common.exception.controller;

import dev.sunriseydy.wp.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 自定义的全局异常处理器，处理未进入Controller层的异常
 *
 * @author SunriseYDY
 * @date 2022-03-02 10:45
 * @see org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
 */
@Slf4j
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController extends AbstractErrorController {

    public CustomErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @ResponseBody
    @RequestMapping
    public Result<?> error(HttpServletRequest request) {
        Map<String, Object> body = this.getErrorAttributes(request,
                this.getErrorAttributeOptions());
        log.error("异常:{}", body);
        return Result.builder().success(false).message(String.valueOf(body.get("message"))).data(body).build();
    }

    private ErrorAttributeOptions getErrorAttributeOptions() {
        ErrorAttributeOptions options = ErrorAttributeOptions.defaults();
        options = options.including(ErrorAttributeOptions.Include.EXCEPTION,
                ErrorAttributeOptions.Include.STACK_TRACE,
                ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.BINDING_ERRORS);
        return options;
    }
}
