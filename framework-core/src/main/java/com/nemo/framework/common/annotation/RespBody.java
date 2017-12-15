/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/27 11:21
 */
package com.nemo.framework.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解使用Json的形式返回数据
 * Created by Nemo on 2017/11/27.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RespBody {
}
