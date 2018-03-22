package com.netease.service;

import com.netease.db.dao.OrderInfoDao;
import com.netease.db.model.OrderInfoModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public List<OrderInfoModel> queryByBuyerAndGoods(long buyerId, long goodsId) {
        return orderInfoDao.queryByBuyerAndGoods(buyerId, goodsId);
    }
}
