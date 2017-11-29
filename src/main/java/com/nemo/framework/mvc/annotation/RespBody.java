/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/27 11:21
 */
package com.nemo.framework.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Nemo on 2017/11/27.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RespBody {
}
