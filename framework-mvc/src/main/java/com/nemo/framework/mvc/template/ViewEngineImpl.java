/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/29 17:30
 */
package com.nemo.framework.mvc.template;

import com.nemo.framework.common.enums.NemoFramworkMvcPropertiesNameEnums;
import com.nemo.framework.core.NemoFrameworkCore;
import com.nemo.framework.core.context.NemoContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * TODO 模板引擎实现
 * Created by Nemo on 2017/11/29.
 */
public class ViewEngineImpl implements ViewEngine {

    public void doEngine(String view, Writer writer) {
        String viewPath = this.getViewPath(view);

        HttpServletRequest servletRequest = NemoContext.context().getRequest();
        HttpServletResponse servletResponse = NemoContext.context().getResponse();
        try {
            servletRequest.getRequestDispatcher(viewPath).forward(servletRequest, servletResponse);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getViewPath(String view){
        NemoFrameworkCore core = NemoFrameworkCore.core();
        String viewPrfix = core.getProp(NemoFramworkMvcPropertiesNameEnums.FRAMEWORK_VIEW_PREFFIX.getValue());
        String viewSuffix = core.getProp(NemoFramworkMvcPropertiesNameEnums.FRAMEWORK_VIEW_SUFFIX.getValue());

        if (null == viewSuffix || viewSuffix.equals("")) {
            viewSuffix = NemoFramworkMvcPropertiesNameEnums.FRAMEWORK_VIEW_SUFFIX.getValue();
        }
        if (null == viewPrfix || viewPrfix.equals("")) {
            viewPrfix = NemoFramworkMvcPropertiesNameEnums.FRAMEWORK_VIEW_PREFFIX.getValue();
        }
        String viewPath = viewPrfix + "/" + view;
        if (!view.endsWith(viewSuffix)) {
            viewPath += viewSuffix;
        }
        return viewPath.replaceAll("[/]+", "/");
    }

}
