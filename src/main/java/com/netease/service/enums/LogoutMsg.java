package com.netease.service.enums;

/**
 * Created by wanghao on 2/9/18.
 */
public enum LogoutMsg {
    SUCCESS(0, "登出成功"),
    FAIL(1, "登出失败");

    public final int VALUE;
    public final String EXTVALUE;

    private LogoutMsg(int value, String extValue) {
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
