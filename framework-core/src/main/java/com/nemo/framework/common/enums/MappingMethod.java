/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/27 11:11
 */
package com.nemo.framework.common.enums;

/**
 * 请求方法枚举定义
 * Created by Nemo on 2017/11/27.
 */
public enum MappingMethod {

    GET("GET METHOD"),
    POST("POST METHOD"),
    DELETE("DELETE METHOD"),
    PUT("PUT METHOD")
    ;

    private String remark;

    MappingMethod(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
