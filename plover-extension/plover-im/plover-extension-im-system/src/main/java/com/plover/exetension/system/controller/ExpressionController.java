package com.plover.exetension.system.controller;

import com.plover.exetension.system.domain.Expression;
import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 表情相关
 * @author: plover
 * @date: 2024/10/26 19:03
 */
@RestController
@RequestMapping("/expression")
public class ExpressionController {
    @GetMapping("/list")
    public AjaxResult listConversation() {
        List<Expression> expressionList = new ArrayList<>();
        Expression expression = Expression.builder()
                .id("1").content("\uD83D\uDE03").type(0).build();

        expressionList.add(expression);
        expression = Expression.builder()
                .id("2").content("\uD83D\uDE04").type(0).build();
        expressionList.add(expression);
        return AjaxResult.success(expressionList);
    }
}
