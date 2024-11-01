package com.plover.exetension.system.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: plover
 * @date: 2024/8/24 5:39
 */
@RestController
@RequestMapping("/group/user")
public class GroupUserController {

    @GetMapping("/count/{groupId}")
    public AjaxResult count(@PathVariable String groupId){
        Map<String,Object> map = new HashMap<>();
        map.put("totalCount",100);
        map.put("onlineCount", 2);
        return AjaxResult.success(map);
    }
}
