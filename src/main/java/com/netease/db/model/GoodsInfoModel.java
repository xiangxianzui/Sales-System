package com.netease.db.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wanghao on 2/10/18.
 */
public class GoodsInfoModel implements Serializable {

    private long id;

    private long sellerId;

    @NotEmpty(message = "标题不能为空")
    @Size(min = 2, max = 80, message = "2~80个字符")
    private String title;

    @NotEmpty(message = "摘要不能为空")
    @Size(min = 2, max = 140, message = "2~140个字符")
    private String abs;

    @NotEmpty(message = "图片不能为空")
    @Pattern(regexp=".*\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|svg)", message = "格式不正确")
    private String image;

    @NotEmpty(message = "正文不能为空")
    @Size(min = 2, max = 1000, message = "2~1000个字符")
    private String des;

    @NotNull(message = "单价不能为空")
    @Digits(integer = 64, fraction = 64, message = "请输入数字")
    @DecimalMin(value = "0", message = "单价不能为负")
    private BigDecimal price;

    @NotNull(message = "库存不能为空")
    @Min(value = 1, message = "库存不能少于1件")
    private int amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String desc) {
        this.des = desc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsInfoModel that = (GoodsInfoModel) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
