package com.ruoyi.common.core.context;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @description: 分页
 * @author: plover
 * @date: 2024/10/31 10:35
 */
public class PageInfoContextHolder {

    private static final ThreadLocal<Page<Object>> pageInfoThreadLocal = new ThreadLocal<>();

    public static Page<Object> getPage() {
        return pageInfoThreadLocal.get();
    }

    public static void setPage(Page<Object> page) {
        pageInfoThreadLocal.set(page);
    }
}
