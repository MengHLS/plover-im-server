package com.ruoyi.common.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 对应用上下文进行操作
 * @author: plover
 * @date: 2024/8/8 16:48
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        assertApplicationContext();
        return applicationContext;
    }

    public static void removeBean(String beanName) {
        assertApplicationContext();
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) applicationContext
                .getAutowireCapableBeanFactory();
        beanDefinitionRegistry.getBeanDefinition(beanName);
        beanDefinitionRegistry.removeBeanDefinition(beanName);
    }

    public static void sendEvent(Object obj) {
        applicationContext.publishEvent(obj);
    }

    public static <T> T getBean(String beanName) {
        assertApplicationContext();
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requiredType) {
        assertApplicationContext();
        return applicationContext.getBean(requiredType);
    }

    public static <T> Collection<T> getBeans(Class<T> requiredType) {
        assertApplicationContext();
        Map<String, T> beansOfTypes = applicationContext.getBeansOfType(requiredType);
        return beansOfTypes.values();
    }

    /**
     * 根据注解获取bean
     * @param annotationType 注解类型
     * @return 注解与bean的map
     */
    public static  Map<?, Object> getBeansOfAnnotation(Class<? extends Annotation> annotationType) {
        return  applicationContext.getBeansWithAnnotation(annotationType)
                .values().stream()
                .collect(Collectors.toMap(
                        t -> AnnotationUtils.findAnnotation(t.getClass(),annotationType),
                        t -> t));
    }

    private static void assertApplicationContext() {
        if (SpringContextHolder.applicationContext == null) {
            throw new RuntimeException(
                    "applicationContext属性为null,请检查是否注入了SpringContextHolder!");
        }
    }
}
