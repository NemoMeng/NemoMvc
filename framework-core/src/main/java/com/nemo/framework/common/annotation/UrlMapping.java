/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/23 18:58
 */
package com.nemo.framework.common.annotation;

import com.nemo.framework.common.enums.MappingMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 路径映射注解
 * Created by Nemo on 2017/11/23.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlMapping {

    public String value();

    public MappingMethod method() default MappingMethod.GET;
}
