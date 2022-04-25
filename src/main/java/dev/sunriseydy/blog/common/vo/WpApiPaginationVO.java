package dev.sunriseydy.blog.common.vo;

import dev.sunriseydy.blog.common.annotion.WpApiRequestParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * wordpress api 分页参数
 * <p>
 *     To determine how many pages of data are available, the API returns two header fields with every paginated response:
 *         X-WP-Total: the total number of records in the collection
 *         X-WP-TotalPages: the total number of pages encompassing all available records
 *      By inspecting these header fields you can determine how much more data is available within the API.
 * </p>
 * @author SunriseYDY
 * @date 2022-03-10 11:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WpApiPaginationVO {
    /**
     * 指定页数
     */
    private Integer page;

    /**
     * 每页个数,最大100
     */
    @WpApiRequestParam(paramName = "per_page")
    private Integer perPage;

    /**
     * 偏移量 从第几个开始分页
     * <p>
     *     ?per_page=5&page=4 is equivalent to ?per_page=5&offset=15
     * </p>
     */
    private Integer offset;
}
