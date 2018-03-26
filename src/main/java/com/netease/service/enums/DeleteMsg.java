package com.netease.service.enums;

/**
 * Created by wanghao on 3/25/18.
 */
public enum DeleteMsg {
    SUCCESS(0, "删除成功"),
    FAIL_UNCORRECT_USER(1, "无删除权限的用户"),
    FAIL_UNKNOWN_USER(2, "未知身份的用户"),
    FAIL_NO_USER(3, "未登录的用户"),
    FAIL_NULL_GOODS(4, "商品不存在"),
    FAIL_NOT_DELETABLE(5, "商品无法删除");

    public final int VALUE;
    public final String EXTVALUE;

    private DeleteMsg(int value, String extValue) {
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
