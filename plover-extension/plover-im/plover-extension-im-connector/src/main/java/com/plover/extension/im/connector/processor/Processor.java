package com.plover.extension.im.connector.processor;

import com.plover.extension.im.core.enums.IMActionType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author: plover
 * @date: 2024/10/26 23:04
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Processor {

    @AliasFor("value")
    IMActionType type() default IMActionType.HEART_BEAT;

    @AliasFor("type")
    IMActionType value() default IMActionType.HEART_BEAT;
}
