/*
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/23 16:39
 */
package com.nemo.framework.mvc.servlet;

import com.nemo.framework.core.NemoFrameworkCore;
import com.nemo.framework.core.enums.NemoFramworkCorePropertiesNameEnums;
import com.nemo.framework.core.init.NemoFrameworkCorePackageScaner;
import com.nemo.framework.core.utils.NemoFrameworkExecuteMethodUtils;
import com.nemo.framework.core.utils.NemoFrameworkPropertiesUtils;
import com.nemo.framework.core.utils.NemoFrameworkUrlUtils;
import com.nemo.framework.common.enums.MappingMethod;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 框架核心控制器
 * Created by Nemo on 2017/11/23.
 */
@WebServlet(name="/",urlPatterns = "/",loadOnStartup=1)
public class NemoMvcFrameworkCoreServlet extends HttpServlet {

    private ServletContext context;

    @Override
    public void init() throws ServletException {
        context = getServletContext();
        NemoFrameworkCore core = NemoFrameworkCore.core();
        //如果还未完成初始化，那么则开始扫描包
        if(!core.isInited()){
            NemoFrameworkPropertiesUtils.loadProperties();
            //开始扫描包
            String basePackage = NemoFrameworkPropertiesUtils.getProp(NemoFramworkCorePropertiesNameEnums.SCAN_BASE_PACKAGE.getValue());
            try {
                NemoFrameworkCorePackageScaner.scan(basePackage);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
            core.setInited(true);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(filterSuffix(request)){
            super.doGet(request,response);
        }else {
            //开始调用方法
            NemoFrameworkExecuteMethodUtils.execute(MappingMethod.GET, request, response,context);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(filterSuffix(request)){
            super.doPost(request,response);
        }else {
            //开始调用方法
            NemoFrameworkExecuteMethodUtils.execute(MappingMethod.POST, request, response,context);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(filterSuffix(request)){
            super.doDelete(request,response);
        }else {
            //开始调用方法
            NemoFrameworkExecuteMethodUtils.execute(MappingMethod.DELETE, request, response,context);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(filterSuffix(request)){
            super.doGet(request,response);
        }else {
            //开始调用方法
            NemoFrameworkExecuteMethodUtils.execute(MappingMethod.PUT, request, response,context);
        }
    }

    /**
     * 拦截，看来路是否是需要拦截的后缀
     * @return
     */
    private boolean filterSuffix(HttpServletRequest request){
        String url = NemoFrameworkUrlUtils.getFullRequestUrl(request);

        String suffix = NemoFrameworkUrlUtils.getfilterSubffix() + "/";

        //TODO 这里用正则更适合，后续完善
        return !url.endsWith(suffix);
    }
}
