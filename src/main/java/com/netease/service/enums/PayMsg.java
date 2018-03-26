package com.netease.service.enums;

/**
 * Created by wanghao on 3/24/18.
 */
public enum PayMsg {
    SUCCESS(0, "支付成功"),
    FAIL_UNCORRECT_USER(1, "无支付权限的用户"),
    FAIL_UNKNOWN_USER(2, "未知身份的用户"),
    FAIL_NO_USER(3, "未登录的用户"),
    FAIL_NULL_ORDER(4, "订单不存在");

    public final int VALUE;
    public final String EXTVALUE;

    private PayMsg(int value, String extValue) {
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
