package com.netease.db.dao;

import com.netease.db.model.OrderInfoModel;
import com.netease.db.model.beans.OrderGoodsBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanghao on 2/12/18.
 */
public interface OrderInfoDao {

    int save(OrderInfoModel orderInfoModel);

    int update(OrderInfoModel orderInfoModel);

    OrderInfoModel findById(@Param("id") long id);

    List<OrderInfoModel> queryAll();

    //分页查询
    List<OrderInfoModel> queryPagination(@Param("limit") int limit, @Param("offset") int offset);

    List<OrderInfoModel> queryByBuyerAndGoods(@Param("buyerId") long buyerId, @Param("goodsId") long goodsId);

    List<OrderInfoModel> queryBySellerAndGoods(@Param("sellerId") long sellerId, @Param("goodsId") long goodsId);

    List<OrderGoodsBean> queryByBuyerAndStatus(@Param("buyerId") long buyerId, @Param("status") int status);
}
