package dev.sunriseydy.wp.common.utils;

import dev.sunriseydy.wp.common.annotion.WpApiRequestParam;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author SunriseYDY
 * @date 2022-03-16 12:37
 */
@UtilityClass
@Slf4j
public class WpApiRequestUtil {

    public String generateQueryParma(Object... queryParams) {
        StringBuilder queryStringBuilder = new StringBuilder("?");
        // 字符串查询汇总
        Map<String, String> stringQueryMap = new HashMap<>();
        // 数组字段查询汇总
        Map<String, Set<String>> arrayQueryMap = new HashMap<>();
        // 布尔字段查询
        Set<String> booleanQuerySet = new HashSet<>();

        for (Object queryParam : queryParams) {
            // 获取对象中的属性
            if (queryParam == null) {
                continue;
            }
            Class<?> aClass = queryParam.getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            // 遍历对象中的字段
            // 1.若字段没有 WpApiRequestParam 注解，则查询参数中字段名是对象字段名
            // 2.若字段有 WpApiRequestParam 注解，则判断注解的ignore属性是否为true
            // 2.1 若ignore为true,则不处理该字段
            // 2.2 若ignore不为true,则查询参数中字段名取注解的paramName属性值或value属性值，若没有指定这些值则去对象属性名
            for (Field field : declaredFields) {
                field.setAccessible(true);
                boolean annotationPresent = field.isAnnotationPresent(WpApiRequestParam.class);
                if (annotationPresent) {
                    WpApiRequestParam annotation = field.getAnnotation(WpApiRequestParam.class);
                    if (annotation.ignore()) {
                        continue;
                    }
                }
                try {
                    Object o = field.get(queryParam);
                    if (o == null) {
                        continue;
                    }
                    if (o instanceof Collection) {
                        Set<String> set = ((Collection<?>) o).stream()
                                .map(String::valueOf)
                                .collect(Collectors.toSet());
                        if (CollectionUtils.isNotEmpty(set)) {
                            arrayQueryMap.put(getFieldName(field), set);
                        }
                    } else if (o instanceof String) {
                        if (StringUtils.isNotBlank((String) o)) {
                            stringQueryMap.put(getFieldName(field), (String) o);
                        }
                    } else if (o instanceof Boolean) {
                        if (Boolean.TRUE.equals(o)) {
                            booleanQuerySet.add(getFieldName(field));
                        }
                    } else {
                        String string = String.valueOf(o);
                        if (StringUtils.isNotBlank(string)) {
                            stringQueryMap.put(getFieldName(field), string);
                        }
                    }
                } catch (IllegalAccessException e) {
                    log.error("", e);
                }
            }
        }

        // 拼接各个类型参数
        stringQueryMap.forEach((key, value) -> {
            queryStringBuilder.append("&");
            queryStringBuilder.append(urlEncode(key)).append("=").append(urlEncode(value));

        });
        booleanQuerySet.forEach(key -> {
            queryStringBuilder.append("&");
            queryStringBuilder.append(urlEncode(key));
        });
        arrayQueryMap.forEach((key, values) -> {
            values.forEach(value -> {
                queryStringBuilder.append("&");
                queryStringBuilder.append(urlEncode(key)).append("=").append(urlEncode(value));
            });
        });

        return queryStringBuilder.toString().replace("?&", "?");
    }

    public String getFieldName(Field field) {
        if (field.isAnnotationPresent(WpApiRequestParam.class)) {
            WpApiRequestParam annotation = field.getAnnotation(WpApiRequestParam.class);
            if (StringUtils.isNotBlank(annotation.paramName())) {
                return annotation.paramName();
            } else {
                return field.getName();
            }
        } else {
            return field.getName();
        }
    }

    @SneakyThrows
    public String urlEncode(String origin) {
        return URLEncoder.encode(origin, StandardCharsets.UTF_8.name());
    }
}
