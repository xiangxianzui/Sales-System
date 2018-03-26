package com.netease.service;

import com.netease.db.dao.OrderInfoDao;
import com.netease.db.model.OrderInfoModel;
import com.netease.db.model.beans.OrderGoodsBean;
import com.netease.db.model.enums.OrderStatus;
import com.netease.service.enums.PayMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by wanghao on 2/12/18.
 */
@Service
public class OrderServiceImp implements OrderService {

    @Resource
    private OrderInfoDao orderInfoDao;

    @Override
    public List<OrderInfoModel> listAll() {
        return null;
    }

    @Override
    public OrderInfoModel viewOrder(long id) {
        return orderInfoDao.findById(id);
    }

    @Override
    public String payOrder(long id) {
        String retMsg;
        OrderInfoModel order = orderInfoDao.findById(id);
        if(order == null){
            retMsg = PayMsg.FAIL_NULL_ORDER.EXTVALUE;
            return retMsg;
        }

        Date curTime = new Date();
        order.setPayTime(curTime);
        order.setStatus(OrderStatus.PAID.VALUE);
        orderInfoDao.update(order);
        retMsg = PayMsg.SUCCESS.EXTVALUE;
        return retMsg;
    }

    @Override
    public List<OrderInfoModel> queryByBuyerAndGoods(long buyerId, long goodsId) {
        return orderInfoDao.queryByBuyerAndGoods(buyerId, goodsId);
    }

    @Override
    public List<OrderInfoModel> queryBySellerAndGoods(@Param("sellerId") long sellerId, @Param("goodsId") long goodsId) {
        return orderInfoDao.queryBySellerAndGoods(sellerId, goodsId);
    }

    @Override
    public List<OrderGoodsBean> queryByBuyerAndStatus(@Param("buyerId") long buyerId, @Param("status") int status) {
        return orderInfoDao.queryByBuyerAndStatus(buyerId, status);
    }
}
