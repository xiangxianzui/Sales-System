package com.netease.db.model.enums;

/**
 * Created by wanghao on 3/23/18.
 */
public enum OrderStatus {
    CREATED(0, "未支付"),
    PAID(1, "已支付");

    public final int VALUE;
    public final String EXTVALUE;

    private OrderStatus(int value, String extValue) {
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
