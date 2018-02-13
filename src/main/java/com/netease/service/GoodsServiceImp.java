package com.netease.service;

import com.netease.db.dao.GoodsInfoDao;
import com.netease.db.model.GoodsInfoModel;
import com.netease.db.model.UserInfoModel;
import com.netease.db.model.enums.UserType;
import com.netease.service.enums.PublishMsg;
import com.netease.util.ModelConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghao on 2/10/18.
 */
@Service
public class GoodsServiceImp implements GoodsService{
    @Resource
    private GoodsInfoDao goodsInfoDao;

    @Override
    public List<GoodsInfoModel> listAll() {
        return goodsInfoDao.queryAll();
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
            goodsInfoModel.setSellerId(curUser.getId());
            goodsInfoDao.save(goodsInfoModel);
            retMsg = PublishMsg.SUCCESS.EXTVALUE;
            return retMsg;
        }

        retMsg = PublishMsg.FAIL_UNKNOWN_USER.EXTVALUE;
        return retMsg;
    }

    @Override
    public GoodsInfoModel viewGoods(long id) {
        return goodsInfoDao.findById(id);
    }

    @Override
    public List<Map<String, Object>> listByBuyer(long buyerId, boolean flag) {
        if(flag){
            return goodsInfoDao.findByBuyer(buyerId);
        }
        else {
            return goodsInfoDao.findByBuyerNot(buyerId);
        }
    }

    @Override
    public List<Map<String, Object>> listBySeller(long sellerId, boolean flag) {
        if (flag) {
            return goodsInfoDao.findBySeller(sellerId);
        } else {
            return goodsInfoDao.findBySellerNot(sellerId);
        }
    }


}
