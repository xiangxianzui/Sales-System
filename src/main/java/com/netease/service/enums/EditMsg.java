package com.netease.service.enums;

/**
 * Created by wanghao on 3/22/18.
 */
public enum EditMsg {
    SUCCESS(0, "编辑成功"),
    FAIL_UNCORRECT_USER(1, "无编辑权限的用户"),
    FAIL_UNKNOWN_USER(2, "未知身份的用户"),
    FAIL_NO_USER(3, "未登录的用户");

    public final int VALUE;
    public final String EXTVALUE;

    private EditMsg(int value, String extValue) {
        VALUE = value;
        EXTVALUE = extValue;
    }

    public int value() {
        return this.VALUE;
    }

    public String extValue() {
        return this.EXTVALUE;
    }
}
