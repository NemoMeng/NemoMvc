/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/27 12:20
 */
package com.nemo.framework.test.controller;

import com.nemo.framework.common.annotation.Controller;
import com.nemo.framework.common.annotation.ReqBody;
import com.nemo.framework.common.annotation.RespBody;
import com.nemo.framework.common.annotation.UrlMapping;
import com.nemo.framework.common.enums.MappingMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nemo on 2017/11/27.
 */
@Controller("user")
public class UserController {

    @UrlMapping(value = "get",method = MappingMethod.POST)
    @RespBody
    public Map<String, Object> get(String name, Integer age, double iq, @ReqBody List<String> friends){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name",name);
        map.put("age",age);
        map.put("IQ",iq);
        return map;
    }

}
