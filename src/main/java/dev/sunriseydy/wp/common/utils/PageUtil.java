package dev.sunriseydy.wp.common.utils;

import dev.sunriseydy.wp.common.vo.PageVO;
import lombok.experimental.UtilityClass;
import org.springframework.beans.support.PagedListHolder;

import java.util.List;

/**
 * @author SunriseYDY
 * @date 2022-03-21 13:39
 */
@UtilityClass
public class PageUtil {
    public <T> PageVO<T> doPage(int page, int pageSize, List<T> content) {
        PagedListHolder<T> pagedListHolder = new PagedListHolder<>(content);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(pageSize);
        return PageVO.<T>builder()
                .page(page)
                .pageSize(pageSize)
                .content(pagedListHolder.getPageList())
                .totalPages(pagedListHolder.getPageCount())
                .total(pagedListHolder.getNrOfElements())
                .build();
    }
}
