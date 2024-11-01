package com.ruoyi.common.core.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.web.page.PageDomain;
import com.ruoyi.common.core.web.page.TableSupport;

import java.util.ArrayList;
import java.util.List;

import static com.ruoyi.common.core.web.page.TableSupport.IS_ASC;
import static com.ruoyi.common.core.web.page.TableSupport.ORDER_BY_COLUMN;


/**
 * 分页工具类
 * 
 * @author ruoyi
 */
public class PageUtils
{
    /**
     * 设置请求分页数据
     */
    public static Page<Object> startPage()
    {
        Page<Object> page = new Page<>();
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
        orderItem.setAsc(getIsAsc(ServletUtils.getParameter(IS_ASC)));
        List<OrderItem> list = new ArrayList<>();
        list.add(orderItem);
        page.setCurrent(pageNum).setSize(pageSize).setOrders(list);
        return page;
    }

    public static boolean getIsAsc(String isAsc) {
        if (StringUtils.isNotEmpty(isAsc)) {
            // 兼容前端排序类型
            if ("ascending".equals(isAsc)) {
                return true;
            } else if ("descending".equals(isAsc)) {
                return false;
            }
            return false;
        }
        return true;
    }
}
