package com.netease.service;

import com.netease.db.dao.GoodsInfoDao;
import com.netease.db.dao.OrderInfoDao;
import com.netease.db.model.GoodsInfoModel;
import com.netease.db.model.OrderInfoModel;
import com.netease.db.model.UserInfoModel;
import com.netease.db.model.enums.OrderStatus;
import com.netease.db.model.enums.UserType;
import com.netease.service.enums.DeleteMsg;
import com.netease.service.enums.EditMsg;
import com.netease.service.enums.PublishMsg;
import com.netease.service.enums.PurchaseMsg;
import com.netease.util.ModelConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghao on 2/10/18.
 */
@Service
public class GoodsServiceImp implements GoodsService{
    @Resource
    private GoodsInfoDao goodsInfoDao;

    @Resource
    private OrderInfoDao orderInfoDao;

    @Override
    public List<Map<String, Object>> listAll() {
        return goodsInfoDao.queryAll();
    }

    @Override
    public List<Map<String, Object>> listAllBuyer(long buyerId) {
        return goodsInfoDao.queryAllBuyer(buyerId);
    }

    @Override
    public List<Map<String, Object>> listAllSeller(long sellerId) {
        return goodsInfoDao.queryAllSeller(sellerId);
    }

    @Override
    public List<GoodsInfoModel> listPagination(int limit, int offset) {
        if(limit<0 || offset<0){
            return null;
        }
        return goodsInfoDao.queryPagination(limit, offset);
    }

    @Override
    public int getGoodsCount() {
        return goodsInfoDao.getCount();
    }

    @Override
    public String publish(GoodsInfoModel goodsInfoModel, HttpServletRequest req) {
        String retMsg;

        UserInfoModel curUser = (UserInfoModel)req.getSession().getAttribute(ModelConstant.USER);
        if(curUser == null){
            retMsg = PublishMsg.FAIL_NO_USER.EXTVALUE;
            return retMsg;
        }

        int userType = curUser.getUsertype();
        if(userType == UserType.BUYER.VALUE){
            retMsg = PublishMsg.FAIL_UNCORRECT_USER.EXTVALUE;
            return retMsg;
        }
        if(userType == UserType.SELLER.VALUE){
            if(goodsInfoModel == null){
                retMsg = PublishMsg.FAIL_NULL_GOODS.EXTVALUE;
                return retMsg;
            }

            int totalGoodsCount = getGoodsCount();
            if(totalGoodsCount >= 1000){
                retMsg = PublishMsg.FAIL_TOO_MANY_GOODS.EXTVALUE;
                return retMsg;
            }

            goodsInfoModel.setSellerId(curUser.getId());
            goodsInfoDao.save(goodsInfoModel);
            retMsg = PublishMsg.SUCCESS.EXTVALUE;
            return retMsg;
        }

        retMsg = PublishMsg.FAIL_UNKNOWN_USER.EXTVALUE;
        return retMsg;
    }

    @Override
    public String edit(GoodsInfoModel goodsInfoModel, HttpServletRequest req){
        String retMsg;

        UserInfoModel curUser = (UserInfoModel)req.getSession().getAttribute(ModelConstant.USER);
        if(curUser == null){
            retMsg = EditMsg.FAIL_NO_USER.EXTVALUE;
            return retMsg;
        }

        int userType = curUser.getUsertype();
        if(userType == UserType.BUYER.VALUE){
            retMsg = EditMsg.FAIL_UNCORRECT_USER.EXTVALUE;
            return retMsg;
        }

        if(userType == UserType.SELLER.VALUE){
            if(goodsInfoModel == null){
                retMsg = EditMsg.FAIL_NULL_GOODS.EXTVALUE;
                return retMsg;
            }

            goodsInfoModel.setSellerId(curUser.getId());
            goodsInfoDao.update(goodsInfoModel);
            retMsg = EditMsg.SUCCESS.EXTVALUE;
            return retMsg;
        }

        retMsg = EditMsg.FAIL_UNKNOWN_USER.EXTVALUE;
        return retMsg;
    }

    @Override
    public String delete(GoodsInfoModel goodsInfoModel, HttpServletRequest req) {
        String retMsg;

        UserInfoModel curUser = (UserInfoModel)req.getSession().getAttribute(ModelConstant.USER);
        if(curUser == null){
            retMsg = DeleteMsg.FAIL_NO_USER.EXTVALUE;
            return retMsg;
        }

        int userType = curUser.getUsertype();
        if(userType == UserType.BUYER.VALUE){
            retMsg = DeleteMsg.FAIL_UNCORRECT_USER.EXTVALUE;
            return retMsg;
        }

        if(userType == UserType.SELLER.VALUE){
            if(goodsInfoModel == null){
                retMsg = DeleteMsg.FAIL_NULL_GOODS.EXTVALUE;
                return retMsg;
            }

            List<OrderInfoModel> relatedOrders = orderInfoDao.queryBySellerAndGoods(curUser.getId(), goodsInfoModel.getId());
            if(relatedOrders != null && relatedOrders.size() != 0){
                retMsg = DeleteMsg.FAIL_NOT_DELETABLE.EXTVALUE;
                return retMsg;
            }

            goodsInfoDao.delete(goodsInfoModel);
            retMsg = DeleteMsg.SUCCESS.EXTVALUE;
            return retMsg;
        }

        retMsg = DeleteMsg.FAIL_UNKNOWN_USER.EXTVALUE;
        return retMsg;
    }

    @Override
    public GoodsInfoModel viewGoods(long id) {
        return goodsInfoDao.findById(id);
    }

    @Override
    public String purchase(GoodsInfoModel goodsInfoModel, int amount, HttpServletRequest req) {
        String retMsg;
        UserInfoModel curUser = (UserInfoModel)req.getSession().getAttribute(ModelConstant.USER);
        if(curUser == null){
            retMsg = PurchaseMsg.FAIL_NO_USER.EXTVALUE;
            return retMsg;
        }

        int userType = curUser.getUsertype();
        if(userType == UserType.SELLER.VALUE){
            retMsg = PurchaseMsg.FAIL_UNCORRECT_USER.EXTVALUE;
            return retMsg;
        }
        if(userType == UserType.BUYER.VALUE){
            if(goodsInfoModel == null){
                retMsg = PurchaseMsg.FAIL_NULL_GOODS.EXTVALUE;
                return retMsg;
            }
            int goodsAmount = goodsInfoModel.getAmount();
            if(goodsAmount < amount){
                retMsg = PurchaseMsg.FAIL_NOT_ENOUGH_GOODS.EXTVALUE;
                return retMsg;
            }
            OrderInfoModel orderInfoModel = new OrderInfoModel();
            orderInfoModel.setBuyerId(curUser.getId());
            orderInfoModel.setSellerId(goodsInfoModel.getSellerId());
            orderInfoModel.setGoodsId(goodsInfoModel.getId());
            orderInfoModel.setPrice(goodsInfoModel.getPrice());
            orderInfoModel.setAmount(amount);
            orderInfoModel.setCreateTime(new Date());
            orderInfoModel.setPayTime(new Date());
            orderInfoModel.setStatus(OrderStatus.CREATED.VALUE);
            orderInfoDao.save(orderInfoModel);
            goodsInfoModel.setAmount(goodsInfoModel.getAmount()-amount);
            goodsInfoDao.update(goodsInfoModel);
            retMsg = PurchaseMsg.SUCCESS.EXTVALUE;
            return retMsg;
        }

        retMsg = PurchaseMsg.FAIL_UNKNOWN_USER.EXTVALUE;
        return retMsg;
    }

    @Override
    public List<Map<String, Object>> listByBuyer(long buyerId, boolean flag) {
        if(flag){
            return goodsInfoDao.queryByBuyer(buyerId);
        }
        else {
            return goodsInfoDao.queryByBuyerNot(buyerId);
        }
    }

    @Override
    public List<Map<String, Object>> listBySeller(long sellerId, boolean flag) {
        if (flag) {
            return goodsInfoDao.queryBySeller(sellerId);
        } else {
            return goodsInfoDao.queryBySellerNot(sellerId);
        }
    }


}
