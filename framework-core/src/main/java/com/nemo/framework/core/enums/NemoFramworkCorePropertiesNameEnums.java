/* 
 * All rights Reserved, Designed By 微迈科技
 * 2017/11/27 14:11
 */
package com.nemo.framework.core.enums;

/**
 * 系统参数基本配置Key相关
 * Created by Nemo on 2017/11/27.
 */
public enum  NemoFramworkCorePropertiesNameEnums {

    SCAN_BASE_PACKAGE("com.nemo.framework.base.package","框架扫描的基本包名"),
    ;

    private String value;
    private String remark;

    NemoFramworkCorePropertiesNameEnums(String value, String remark) {
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
