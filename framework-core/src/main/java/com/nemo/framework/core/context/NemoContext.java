/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/29 19:00
 */
package com.nemo.framework.core.context;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nemo on 2017/11/29.
 */
public class NemoContext {

    private static final ThreadLocal<NemoContext> CONTEXT = new ThreadLocal<NemoContext>();

    private ServletContext context;
    private HttpServletRequest request;
    private HttpServletResponse response;

    private NemoContext() {
    }

    public static NemoContext context(){
        return CONTEXT.get();
    }

    public static void initContext(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        NemoContext nemoContext = new NemoContext();
        nemoContext.context = context;
        nemoContext.request = request;
        nemoContext.response = response;
        CONTEXT.set(nemoContext);
    }

    public static void remove(){
        CONTEXT.remove();
    }

    public ServletContext getContext() {
        return context;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
