package com.szc.constant;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szc.util.CollectionUtil;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 页码对象
 *
 * @author Muzh
 */
@Getter
@Setter
@ApiModel("分页参数")
public class Pages {

    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;

    /**
     * 当前页
     */
    private long current = 1;

    /**
     * as size
     */
    private long pageSize = 10;
    /**
     * as current
     */
    private long currentPage = 1;


    /**
     * 排序字段信息
     */
    private List<OrderItem> orders = new ArrayList<>();

    /**
     * 构建mybatis plus分页
     *
     * @param <T> T
     * @return 分页
     */
    public <T> IPage<T> build() {
        final Page<T> page = new Page<>(this.current, this.size);
        if (CollectionUtil.isNotEmpty(orders)) {
            page.setOrders(orders);
        }
        return page;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.size = pageSize;
        this.pageSize = pageSize;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.current = currentPage;
        this.currentPage = currentPage;
    }
}
