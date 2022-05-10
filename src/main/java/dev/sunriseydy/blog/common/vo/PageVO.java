package dev.sunriseydy.blog.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * @author SunriseYDY
 * @date 2022-03-21 15:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageVO<T> {
    private Long page;

    private Long pageSize;

    private Long totalPages;

    private Long total;

    private Collection<T> content;
}
