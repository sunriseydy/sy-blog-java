package dev.sunriseydy.wp.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-03-21 15:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageVO<T> {
    private Integer page;

    private Integer pageSize;

    private Integer totalPages;

    private Integer total;

    private List<T> content;
}
