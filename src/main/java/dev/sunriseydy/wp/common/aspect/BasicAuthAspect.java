package dev.sunriseydy.wp.common.aspect;

import dev.sunriseydy.wp.common.exception.CommonException;
import dev.sunriseydy.wp.common.properties.SyWpProperties;
import dev.sunriseydy.wp.common.utils.BasicAuthUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author SunriseYDY
 * @date 2022-03-24 16:11
 */
@Aspect
@Component
public class BasicAuthAspect {

    @Autowired
    private SyWpProperties properties;

    @Pointcut("@annotation(dev.sunriseydy.wp.common.annotion.BasicAuth)")
    public void pointCut() {};

    @Before("pointCut()")
    public void before() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new CommonException("requestAttributes is null");
        }
        HttpServletRequest request =
                requestAttributes.getRequest();

        String username = properties.getUsername();
        String password = properties.getPassword();
        Assert.hasText(username, "username in the property is empty");
        Assert.hasText(password, "password in the property is empty");
        if (!BasicAuthUtil.checkAuthorization(request, username,password)) {
            throw new CommonException("user authorize failed");
        }
    }
}
