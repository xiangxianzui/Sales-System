package com.netease.service;

import com.netease.db.dao.OrderInfoDao;
import com.netease.db.model.OrderInfoModel;

import java.util.List;

/**
 * Created by wanghao on 2/12/18.
 */
public interface OrderService {
    public List<OrderInfoModel> listAll();

    public OrderInfoModel viewOrder(long id);
}
