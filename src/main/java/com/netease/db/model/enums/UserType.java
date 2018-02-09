package com.netease.db.model.enums;

public enum UserType {
    BUYER(0, "买家"),
    SELLER(1, "卖家");

    public final int VALUE;
    public final String EXTVALUE;

    private UserType(int value, String extValue) {
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
