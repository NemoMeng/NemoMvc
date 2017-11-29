/*
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/23 16:27
 */
package com.nemo.framework.core;

import com.nemo.framework.core.utils.NemoFrameworkUrlUtils;
import com.nemo.framework.core.route.bean.RouteBean;
import com.nemo.framework.core.route.manager.*;
import com.nemo.framework.mvc.annotation.UrlMapping;
import com.nemo.framework.mvc.enums.MappingMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 框架核心处理类，用来加载路由 + 配置 + 资源文件等一系列的处理
 * note:目前先处理路由
 * TODO 配置 + 资源文件等处理
 * Created by Nemo on 2017/11/23.
 */
public class NemoFrameworkCore {
    /**
     * 路由管理器
     */
    private GetRouters getRouters;
    private PutRouters putRouters;
    private PostRouters postRouters;
    private DeleteRouters deleteRouters;

    /**
     * 是否已经初始化的标志
     */
    private boolean isInited = false;

    private static NemoFrameworkCore nemoFrameworkCore = null;

    private NemoFrameworkCore(){
        getRouters = new GetRouters();
        putRouters = new PutRouters();
        deleteRouters = new DeleteRouters();
        postRouters = new PostRouters();
    }

    public static NemoFrameworkCore core(){
        if(nemoFrameworkCore == null){
            nemoFrameworkCore = new NemoFrameworkCore();
        }
        else{
            nemoFrameworkCore = new NemoFrameworkCore();
        }
        return nemoFrameworkCore;
    }

    /**
     * 添加路由
     * @param path 访问路径
     * @param controller 控制器对象
     * @param method 方法
     * @return
     */
    public NemoFrameworkCore addRoute(String path, Object controller, Method method){
        try {
            UrlMapping urlMapping = method.getAnnotation(UrlMapping.class);
            MappingMethod mappingMethod = urlMapping.method();
            if(mappingMethod == null || mappingMethod.name().equals(MappingMethod.GET.name())){
                this.getRouters.addRoute(path, controller , method);
            }else if(mappingMethod.name().equals(MappingMethod.POST.name())){
                this.postRouters.addRoute(path, controller , method);
            }else if(mappingMethod.name().equals(MappingMethod.DELETE.name())){
                this.deleteRouters.addRoute(path, controller , method);
            }else if(mappingMethod.name().equals(MappingMethod.PUT.name())){
                this.putRouters.addRoute(path, controller , method);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 根据路径得到路由
     * @param path
     * @return
     */
    public RouteBean getRouteByPath(String path){
        return this.getRouters.getRouteByPath(path);
    }

    /**
     * 根据访问地址+访问方法获取路由
     * @param path
     * @param mappingMethod
     * @return
     */
    public RouteBean getRouteByPathAndMappingMethod(String path,MappingMethod mappingMethod){
        if(mappingMethod == null || mappingMethod.name().equals(MappingMethod.GET.name())){
            return this.getRouters.getRouteByPath(path);
        }else if(mappingMethod.name().equals(MappingMethod.POST.name())){
            return this.postRouters.getRouteByPath(path);
        }else if(mappingMethod.name().equals(MappingMethod.DELETE.name())){
            return this.deleteRouters.getRouteByPath(path);
        }else if(mappingMethod.name().equals(MappingMethod.PUT.name())){
            return this.putRouters.getRouteByPath(path);
        }
        return null;
    }

    /**
     * 从请求中获取需要访问的路由
     * @param request
     * @return
     */
    public RouteBean getRoute(HttpServletRequest request,MappingMethod mappingMethod){
        //用户访问的路径
        String path = NemoFrameworkUrlUtils.getFullRequestUrl(request);

        //得到路由
        return this.getRouteByPathAndMappingMethod(path, mappingMethod);
    }

    public boolean isInited() {
        return isInited;
    }

    public void setInited(boolean inited) {
        isInited = inited;
    }
}
