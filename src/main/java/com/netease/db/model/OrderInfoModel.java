package com.netease.db.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wanghao on 2/12/18.
 */
public class OrderInfoModel implements Serializable {

    private long id;

    private long buyerId;

    private long sellerId;

    private BigDecimal price;

    private int amount;

    private Date createTime;

    private Date payTime;

    /*public OrderInfoModel(long id, long buyerId, long sellerId, BigDecimal price, int amount, Date createTime, Date payTime) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.price = price;
        this.amount = amount;
        this.createTime = createTime;
        this.payTime = payTime;
    }*/

    /*---- 关联表信息 ----*/
    private GoodsInfoModel goods;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
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

    public GoodsInfoModel getGoods() {
        return goods;
    }

    public void setGoods(GoodsInfoModel goods) {
        this.goods = goods;
    }
}
