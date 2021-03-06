/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/23 16:04
 */
package com.nemo.framework.core.route.manager;

import com.nemo.framework.core.route.bean.RouteBean;

import java.util.HashMap;
import java.util.Map;

/**
 * 所有Post方法的路由管理器
 * Created by Nemo on 2017/11/23.
 */
public class PostRouters extends Routers {

    /**
     * 路由列表，key为路由访问路径，val为路由本身
     */
    private Map<String,RouteBean> routes = new HashMap<String,RouteBean>();

    public Map<String, RouteBean> getRoutes() {
        return routes;
    }

    public void setRoutes(Map<String, RouteBean> routes) {
        this.routes = routes;
    }
}
