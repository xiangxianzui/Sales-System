package com.netease.service.enums;

/**
 * Created by wanghao on 2/10/18.
 */
public enum PublishMsg {
    SUCCESS(0, "发布成功"),
    FAIL_UNCORRECT_USER(1, "无发布权限的用户"),
    FAIL_UNKNOWN_USER(2, "未知身份的用户"),
    FAIL_NO_USER(3, "未登录的用户"),
    FAIL_NULL_GOODS(4, "商品不存在"),
    FAIL_TOO_MANY_GOODS(5, "商品个数超限");

    public final int VALUE;
    public final String EXTVALUE;

    private PublishMsg(int value, String extValue) {
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
