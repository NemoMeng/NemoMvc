/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/23 17:11
 */
package com.nemo.framework.test.controller;

import com.nemo.framework.mvc.enums.MappingMethod;
import com.nemo.framework.mvc.annotation.Controller;
import com.nemo.framework.mvc.annotation.UrlMapping;

/**
 * Created by Nemo on 2017/11/23.
 */
@Controller("index")
public class IndexController {

    @UrlMapping(value = "/aaa",method = MappingMethod.GET)
    public void index(String arg,Integer age){
        System.out.println("你得到了一个参数arg:" + arg);
        System.out.println("你得到了一个参数age:" + age);
        System.out.println("you are request index");
    }

}
