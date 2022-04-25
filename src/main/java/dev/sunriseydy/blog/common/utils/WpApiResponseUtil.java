package dev.sunriseydy.blog.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sunriseydy.blog.common.exception.CommonException;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

/**
 * @author SunriseYDY
 * @date 2022-03-17 14:40
 */
@UtilityClass
@Slf4j
public class WpApiResponseUtil {

    public String RESPONSE_STATUS = "status";

    public String RESPONSE_BODY = "body";

    public String RESPONSE_HEADERS = "headers";

    public String RESPONSE_MESSAGE = "message";

    public String RESPONSE_HEADER_TOTAL = "X-WP-Total";

    public String RESPONSE_HEADER_TOTAL_PAGES = "X-WP-TotalPages";

    @SneakyThrows
    public void checkResponseIsOk(String response) {
        Assert.hasText(response, "WordPress API 响应为空");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = objectMapper.readTree(response);
        JsonNode status = jsonNode.get(RESPONSE_STATUS);
        if (status == null) {
            log.error("wordpress api 无响应状态码:{}", response);
            throw new CommonException("wordpress api 无响应状态码");
        }
        if (HttpStatus.OK.value() != status.intValue()) {
            log.error("wordpress api 返回失败:{}", response);
            throw new CommonException("wordpress api 返回失败：" + status + ";" + getResponseErrorMessage(response));
        }
        log.debug("response:{}", response);
    }

    public String checkResponseEntityAndReturnBody(ResponseEntity<String> responseEntity) {
        if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            log.error("请求 WordPress API 失败：{};{}", responseEntity.getStatusCodeValue(), responseEntity.getBody());
            throw new CommonException("请求 WordPress API 失败：" + responseEntity.getStatusCodeValue());
        }
        if (!responseEntity.hasBody()) {
            log.error("请求 WordPress API 失败：返回body为空;{};{}", responseEntity.getStatusCodeValue(), responseEntity.getBody());
            throw new CommonException("请求 WordPress API 失败:返回body为空");
        }
        return responseEntity.getBody();
    }

    @SneakyThrows
    public String getResponseErrorMessage(String response) {
        Assert.hasText(response, "WordPress API 响应为空");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = objectMapper.readTree(response);
        JsonNode body = jsonNode.get(RESPONSE_BODY);
        if (body != null && body.isObject() && body.has(RESPONSE_MESSAGE)) {
            return body.get(RESPONSE_MESSAGE).toString();
        } else {
            return response;
        }
    }

    @SneakyThrows
    public <T> T getResponseBodyAsObject(String response, Class<T> type) {
        checkResponseIsOk(response);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = objectMapper.readTree(response);
        JsonNode body = jsonNode.get(RESPONSE_BODY);
        return objectMapper.readValue(body.traverse(), type);
    }

    @SneakyThrows
    public <T> T getResponseBodyAsObject(String response, TypeReference<T> valueTypeRef) {
        checkResponseIsOk(response);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = objectMapper.readTree(response);
        JsonNode body = jsonNode.get(RESPONSE_BODY);
        return objectMapper.readValue(body.traverse(), valueTypeRef);
    }

    @SneakyThrows
    public int getResponseTotal(String response) {
        checkResponseIsOk(response);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = objectMapper.readTree(response);
        JsonNode headers = jsonNode.get(RESPONSE_HEADERS);
        if (headers == null) {
            log.error("WordPress API response headers is null:{}", response);
            throw new CommonException("WordPress API response headers is null");
        }
        JsonNode total = headers.get(RESPONSE_HEADER_TOTAL);
        if (total != null) {
            return total.asInt();
        } else {
            log.error("WordPress API response headers total is null:{}", response);
            throw new CommonException("WordPress API response headers total is null");
        }
    }

    @SneakyThrows
    public int getResponseTotalPages(String response) {
        checkResponseIsOk(response);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = objectMapper.readTree(response);
        JsonNode headers = jsonNode.get(RESPONSE_HEADERS);
        if (headers == null) {
            log.error("WordPress API response headers is null:{}", response);
            throw new CommonException("WordPress API response headers is null");
        }
        JsonNode totalPages = headers.get(RESPONSE_HEADER_TOTAL_PAGES);
        if (totalPages != null) {
            return totalPages.asInt();
        } else {
            log.error("WordPress API response headers totalPages is null:{}", response);
            throw new CommonException("WordPress API response headers totalPages is null");
        }
    }

}
