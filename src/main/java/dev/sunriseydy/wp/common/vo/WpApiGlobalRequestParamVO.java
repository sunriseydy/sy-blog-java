package dev.sunriseydy.wp.common.vo;

import dev.sunriseydy.wp.common.annotion.WpApiRequestParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * wp rest api 全局请求参数
 * @author SunriseYDY
 * @date 2022-03-09 16:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WpApiGlobalRequestParamVO {
    /**
     * <p>
     *     指定返回的字段,逗号分割
     * </p>
     * <p>
     *     例如：/wp/v2/posts?_fields=author,id,excerpt,title,link
     * </p>
     */
    @WpApiRequestParam("_fields")
    private String fields;

    /**
     * <p>
     *     指定返回的字段,数组形式
     * </p>
     * <p>
     *     例如：/wp/v2/posts?_fields[]=author&_fields[]=id&_fields[]=excerpt&_fields[]=title&_fields[]=link
     * </p>
     */
    @WpApiRequestParam("_fields[]")
    private List<String> fieldsList;

    /**
     * <p>
     *     指定 wordpress 在返回中直接内嵌关联的资源
     * </p>
     * <p>
     *     例如 /wp/v2/posts?_embed=author,wp:term
     * </p>
     */
    @WpApiRequestParam("_embed")
    private String embed;

    /**
     * <p>
     *     告诉 wordpress 重写请求类型
     * </p>
     * <p>
     *     例如：A POST to /wp-json/wp/v2/posts/42?_method=DELETE would be translated to a DELETE to the wp/v2/posts/42 route.
     * </p>
     */
    @WpApiRequestParam("_method")
    private String method;

    /**
     * <p>
     *     告诉 wordpress 将响应的 header 和 status code 都放到 body 中
     * </p>
     */
    @WpApiRequestParam("envelope")
    private Boolean envelope = Boolean.TRUE;

}
