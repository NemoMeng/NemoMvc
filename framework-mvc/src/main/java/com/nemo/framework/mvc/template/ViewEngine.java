/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/29 17:28
 */
package com.nemo.framework.mvc.template;

import java.io.Writer;

/**
 * TODO 模板引擎实现
 * Created by Nemo on 2017/11/29.
 */
public interface ViewEngine {

    /**
     * 渲染到视图
     * @param view		视图名称
     * @param writer	写入对象
     */
    public void doEngine(String view, Writer writer);

}
