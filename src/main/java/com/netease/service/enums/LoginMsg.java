package com.netease.service.enums;

/**
 * Created by wanghao on 2/9/18.
 */
public enum LoginMsg {
    SUCCESS(0, "登录成功"),
    FAIL_NO_USER(1, "用户不存在"),
    FAIL_WRONG_PWD(2, "密码不正确");

    public final int VALUE;
    public final String EXTVALUE;

    private LoginMsg(int value, String extValue) {
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

