/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/23 19:07
 */
package com.nemo.framework.core.init;

import com.nemo.framework.common.utils.CollectionUtils;
import com.nemo.framework.common.utils.MapUtils;
import com.nemo.framework.core.NemoFrameworkCore;
import com.nemo.framework.core.utils.NemoFrameworkAnnotationUtils;
import com.nemo.framework.core.utils.NemoFrameworkUrlUtils;
import com.nemo.framework.common.annotation.UrlMapping;
import com.nemo.framework.common.enums.MappingMethod;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 包扫描器，扫描报下的指定注解，存放到核心框架内存中
 * Created by Nemo on 2017/11/23.
 */
public class NemoFrameworkCorePackageScaner {

    /**
     * 开始扫描指定包下面的所有注解为Controller的类及其下面全部注解为UrlMapping的方法
     * @param packageName
     */
    public static void scan(String packageName){
        NemoFrameworkCore core = NemoFrameworkCore.core();
        if(!core.isInited()) {
            //得到所有的controller
            List<Object> classList = NemoFrameworkAnnotationUtils.getAllController(packageName);
            if (CollectionUtils.isNotEmpty(classList)) {
                for (Object clss : classList) {
                    //所有controller下的mapping
                    Map<String, Method> methods = NemoFrameworkAnnotationUtils.getMapMethodsByClass(clss.getClass());
                    if (MapUtils.isNotEmpty(methods)) {
                        for (Map.Entry<String, Method> entries : methods.entrySet()) {

                            Method method = entries.getValue();

                            //根据controller + method上的注解得到完整的访问路径
                            String key = NemoFrameworkUrlUtils.getFullUrlByControllerAndMethodUrl(clss,method);
                            //key = key + NemoFrameworkUrlUtils.getfilterSubffix();

                            MappingMethod mappingMethod = method.getAnnotation(UrlMapping.class).method();

                            //TODO 如果访问路由被重复定义，应该提醒下用户
                            if(core.getRouteByPathAndMappingMethod(key,mappingMethod) != null){

                            }

                            //添加到核心控制器中
                            core.addRoute(key,clss,method);
                        }
                    }
                }
            }
            core.setInited(true);
        }
    }

}
