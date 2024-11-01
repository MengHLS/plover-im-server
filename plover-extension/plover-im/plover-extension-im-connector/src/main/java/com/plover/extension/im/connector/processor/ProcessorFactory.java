package com.plover.extension.im.connector.processor;

import com.plover.extension.im.core.enums.IMActionType;
import com.ruoyi.common.core.utils.SpringContextHolder;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 消息处理工厂类
 * @author: plover
 * @date: 2024/8/8 2:53
 */
public class ProcessorFactory {

    private static Map<Processor, Object> processorMap = null;

    private static Map<IMActionType, Object> actionProcessorMap = null;

    public static MessageProcessor createProcessor(IMActionType actionType) {
        if (processorMap == null){
            processorMap = (Map<Processor, Object>) SpringContextHolder.getBeansOfAnnotation(Processor.class);
        }
        if (actionProcessorMap == null){
            actionProcessorMap = processorMap.keySet().stream().collect(
                    Collectors.toMap(Processor::value,p->processorMap.get(p))
            );
        }
        return  (MessageProcessor) actionProcessorMap.get(actionType);

    }
}
