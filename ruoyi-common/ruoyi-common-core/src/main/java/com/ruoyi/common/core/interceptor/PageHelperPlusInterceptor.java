package com.ruoyi.common.core.interceptor;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.context.PageInfoContextHolder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.Map;

/**
 * @description:
 * @author: plover
 * @date: 2024/10/31 10:19
 */
public class PageHelperPlusInterceptor implements InnerInterceptor {

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        Page page = PageInfoContextHolder.getPage();
        if (page != null) {
            if (parameter == null){
                parameter = PageInfoContextHolder.getPage();
            } else if (parameter instanceof Map) {
                ((Map) parameter).put("page", PageInfoContextHolder.getPage());
            }
        }
        InnerInterceptor.super.beforeQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql);
    }
}
