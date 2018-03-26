package com.netease.service;

import com.netease.db.dao.OrderInfoDao;
import com.netease.db.model.OrderInfoModel;
import com.netease.db.model.beans.OrderGoodsBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanghao on 2/12/18.
 */
public interface OrderService {
    public List<OrderInfoModel> listAll();

    public OrderInfoModel viewOrder(long id);

    public String payOrder(long id);

    public List<OrderInfoModel> queryByBuyerAndGoods(@Param("buyerId") long buyerId, @Param("goodsId") long goodsId);

    public List<OrderInfoModel> queryBySellerAndGoods(@Param("sellerId") long sellerId, @Param("goodsId") long goodsId);

    public List<OrderGoodsBean> queryByBuyerAndStatus(@Param("buyerId") long buyerId, @Param("status") int status);
}
