package com.netease.service.enums;

/**
 * Created by wanghao on 3/23/18.
 */
public enum PurchaseMsg {
    SUCCESS(0, "购买成功"),
    FAIL_UNCORRECT_USER(1, "无购买权限的用户"),
    FAIL_UNKNOWN_USER(2, "未知身份的用户"),
    FAIL_NO_USER(3, "未登录的用户"),
    FAIL_NULL_GOODS(4, "商品不存在"),
    FAIL_NOT_ENOUGH_GOODS(5, "商品库存不足");

    public final int VALUE;
    public final String EXTVALUE;

    private PurchaseMsg(int value, String extValue) {
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
