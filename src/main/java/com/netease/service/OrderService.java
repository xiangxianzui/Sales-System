package com.netease.service;

import com.netease.db.dao.OrderInfoDao;
import com.netease.db.model.OrderInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanghao on 2/12/18.
 */
public interface OrderService {
    public List<OrderInfoModel> listAll();

    public OrderInfoModel viewOrder(long id);

    public List<OrderInfoModel> queryByBuyerAndGoods(@Param("buyerId") long buyerId, @Param("goodsId") long goodsId);
}
