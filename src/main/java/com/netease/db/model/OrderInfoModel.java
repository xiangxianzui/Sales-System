package com.netease.db.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wanghao on 2/12/18.
 */
public class OrderInfoModel implements Serializable {

    private long id;

    private BigDecimal price;

    private int amount;

    private Date createTime;

    private Date payTime;

    public OrderInfoModel(Long id, BigDecimal price, Integer amount, Date createTime, Date payTime) {
        this.id = id;
        this.price = price;
        this.amount = amount;
        this.createTime = createTime;
        this.payTime = payTime;
    }

    /*---- 关联表信息 ----*/
    private UserInfoModel buyer;

    private UserInfoModel seller;

    private GoodsInfoModel goods;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public UserInfoModel getBuyer() {
        return buyer;
    }

    public void setBuyer(UserInfoModel buyer) {
        this.buyer = buyer;
    }

    public UserInfoModel getSeller() {
        return seller;
    }

    public void setSeller(UserInfoModel seller) {
        this.seller = seller;
    }

    public GoodsInfoModel getGoods() {
        return goods;
    }

    public void setGoods(GoodsInfoModel goods) {
        this.goods = goods;
    }
}
