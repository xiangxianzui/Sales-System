package com.netease.service.enums;

/**
 * Created by wanghao on 3/25/18.
 */
public enum UploadMsg {
    SUCCESS(0, "上传成功"),
    FAIL_NULL_FILE(1, "文件为空"),
    FAIL_EXCEED_MAX_SIZE(2, "文件超过限制"),
    FAIL_UNEXPECTED_TYPE(3, "文件类型不是图片");

    public final int VALUE;
    public final String EXTVALUE;

    private UploadMsg(int value, String extValue) {
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
