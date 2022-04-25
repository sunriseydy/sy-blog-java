package dev.sunriseydy.blog.common.utils;

import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

/**
 * @author SunriseYDY
 * @date 2022-03-24 15:30
 */
@UtilityClass
public class BasicAuthUtil {

    public boolean checkAuthorization(HttpServletRequest request, String name, String password) {
        String basicString = "Basic " + new String(Base64.getEncoder().encode((name + ":" + password).getBytes()));
        String authorization = request.getHeader("Authorization");
        return basicString.equals(authorization);
    }
}
