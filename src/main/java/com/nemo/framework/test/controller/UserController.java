/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/27 12:20
 */
package com.nemo.framework.test.controller;

import com.nemo.framework.mvc.annotation.Controller;
import com.nemo.framework.mvc.annotation.UrlMapping;

/**
 * Created by Nemo on 2017/11/27.
 */
@Controller("user")
public class UserController {

    @UrlMapping("get")
    public void get(String name){
        System.out.println("你正在请求："+name);
    }

}
