/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/24 16:01
 */
package com.nemo.framework.core.utils;

import com.nemo.framework.common.utils.ReflectUtils;
import com.nemo.framework.core.NemoFrameworkCore;
import com.nemo.framework.core.route.bean.RouteBean;
import com.nemo.framework.mvc.enums.MappingMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 执行方法工具类
 * Created by Nemo on 2017/11/24.
 */
public class NemoFrameworkExecuteMethodUtils {

    /**
     * 执行方法
     * @param obj
     * @param method
     * @return
     */
    public static Object execute(Object obj, Method method, HttpServletRequest request, HttpServletResponse response){
        try {
            //方法的参数类型列表
            Class<?>[] parameterTypes = method.getParameterTypes();
            if(parameterTypes == null || parameterTypes.length<=0){
                return method.invoke(obj);
            }else {
                Object[] parameters = NemoFrameworkParameterUtils.getParameters(request, response, obj , method);
                //return method.invoke(obj, parameters);
                return ReflectUtils.invokeMehod(obj,method,parameters);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行方法
     * @param route
     * @param request
     * @param response
     * @return
     */
    public static Object execute(RouteBean route,HttpServletRequest request, HttpServletResponse response){
        //开始调用方法
        Object obj = route.getController();
        Method method = route.getMethod();
        return execute(obj,method,request,response);
    }

    /**
     * 执行方法
     * @param mappingMethod
     * @param request
     * @param response
     * @return
     */
    public static Object execute(MappingMethod mappingMethod, HttpServletRequest request, HttpServletResponse response){

        NemoFrameworkCore core = NemoFrameworkCore.core();

        //得到路由
        RouteBean route = core.getRoute(request,mappingMethod);
        if(route == null){
            throw new RuntimeException("Sorry,I can't find the method.");
        }

        //开始调用方法
        return NemoFrameworkExecuteMethodUtils.execute(route,request,response);
    }

}
