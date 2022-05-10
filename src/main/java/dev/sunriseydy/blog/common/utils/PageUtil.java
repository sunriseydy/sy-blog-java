package dev.sunriseydy.blog.common.utils;

import dev.sunriseydy.blog.common.vo.PageVO;
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
                .page((long) page)
                .pageSize((long) pageSize)
                .content(pagedListHolder.getPageList())
                .totalPages((long) pagedListHolder.getPageCount())
                .total((long) pagedListHolder.getNrOfElements())
                .build();
    }
}
