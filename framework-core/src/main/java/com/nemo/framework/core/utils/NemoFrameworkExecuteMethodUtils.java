/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/24 16:01
 */
package com.nemo.framework.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.nemo.framework.common.annotation.RespBody;
import com.nemo.framework.common.enums.NemoFramworkMvcPropertiesNameEnums;
import com.nemo.framework.common.utils.ReflectUtils;
import com.nemo.framework.core.NemoFrameworkCore;
import com.nemo.framework.core.context.NemoContext;
import com.nemo.framework.core.route.bean.RouteBean;
import com.nemo.framework.common.enums.MappingMethod;
import jdk.nashorn.internal.runtime.regexp.joni.Config;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

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

                //获取参数
                Object[] parameters = NemoFrameworkParameterUtils.getParameters(request, response, obj , method);
                //return method.invoke(obj, parameters);

                //调用方法
                Object object = ReflectUtils.invokeMehod(obj,method,parameters);

                //如果注解了RespBody，那么就直接把结果返回生成json返回
                RespBody respBody = method.getAnnotation(RespBody.class);
                if(respBody!=null){
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json; charset=utf-8");
                    response.getWriter().write(JSONObject.toJSONString(object));
                    return object;
                }

                //如果是字符串的返回，那么就把它当作一个jsp的映射路径
                if(object instanceof String) {
                    String path = "/" + NemoFrameworkPropertiesUtils.getProp(NemoFramworkMvcPropertiesNameEnums.FRAMEWORK_VIEW_PREFFIX.getValue())
                            + File.separator + object.toString()
                            + NemoFrameworkPropertiesUtils.getProp(NemoFramworkMvcPropertiesNameEnums.FRAMEWORK_VIEW_SUFFIX.getValue());
                    request.getRequestDispatcher(path).forward(request, response);
                    return object;
                }
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
        return execute(route,request,response);
    }

    /**
     * 执行方法
     * @param mappingMethod
     * @param request
     * @param response
     * @param context
     * @return
     */
    public static Object execute(MappingMethod mappingMethod, HttpServletRequest request, HttpServletResponse response, ServletContext context){
        NemoContext.initContext(context,request,response);
        return execute(mappingMethod,request,response);
    }

}
