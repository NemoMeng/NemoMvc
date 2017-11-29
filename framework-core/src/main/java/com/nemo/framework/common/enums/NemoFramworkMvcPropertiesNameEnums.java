/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/27 14:03
 */
package com.nemo.framework.common.enums;

/**
 * 系统mvc相关配置文件key定义枚举
 * Created by Nemo on 2017/11/27.
 */
public enum NemoFramworkMvcPropertiesNameEnums {

    FRAMEWORK_FILTER_SUFFIX("com.nemo.framework.filter.suffix","框架拦截的后缀名称"),
    FRAMEWORK_VIEW_SUFFIX("com.nemo.framework.view.suffix","框架使用的视图的后缀"),
    FRAMEWORK_VIEW_PREFFIX("com.nemo.framework.view.preffix","框架使用的视图的位置"),
    ;
    private String value;
    private String remark;

    NemoFramworkMvcPropertiesNameEnums(String value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
