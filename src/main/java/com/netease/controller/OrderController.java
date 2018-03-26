package com.netease.controller;

import com.google.common.base.Strings;
import com.netease.db.model.OrderInfoModel;
import com.netease.db.model.UserInfoModel;
import com.netease.db.model.beans.OrderGoodsBean;
import com.netease.db.model.enums.UserType;
import com.netease.service.OrderService;
import com.netease.util.ModelConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wanghao on 2/12/18.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/listByBuyerAndGoods", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderInfoModel> listByBuyerAndGoods(HttpServletRequest req,
                                @RequestParam(value = "goodsId", required = true) long goodsId){
        Object obj = req.getSession().getAttribute(ModelConstant.USER);
        if(obj == null || Strings.isNullOrEmpty(obj.toString())){
            return null;
        }
        UserInfoModel curUser = (UserInfoModel)obj;
        if(UserType.BUYER.VALUE != curUser.getUsertype()){
            return null;
        }

        long buyerId = curUser.getId();
        List<OrderInfoModel> orders = orderService.queryByBuyerAndGoods(buyerId, goodsId);

        return orders;
    }

    @RequestMapping(value = "/listBySellerAndGoods", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderInfoModel> listBySellerAndGoods(HttpServletRequest req,
                                                    @RequestParam(value = "goodsId", required = true) long goodsId){
        Object obj = req.getSession().getAttribute(ModelConstant.USER);
        if(obj == null || Strings.isNullOrEmpty(obj.toString())){
            return null;
        }
        UserInfoModel curUser = (UserInfoModel)obj;
        if(UserType.SELLER.VALUE != curUser.getUsertype()){
            return null;
        }

        long sellerId = curUser.getId();
        List<OrderInfoModel> orders = orderService.queryBySellerAndGoods(sellerId, goodsId);

        return orders;
    }

    @RequestMapping(value = "/listByBuyerAndStatus", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderGoodsBean> listByBuyerAndStatus(HttpServletRequest req,
                                                    @RequestParam(value = "status", required = true) int status){
        Object obj = req.getSession().getAttribute(ModelConstant.USER);
        if(obj == null || Strings.isNullOrEmpty(obj.toString())){
            return null;
        }
        UserInfoModel curUser = (UserInfoModel)obj;
        if(UserType.BUYER.VALUE != curUser.getUsertype()){
            return null;
        }

        long buyerId = curUser.getId();
        List<OrderGoodsBean> orders = orderService.queryByBuyerAndStatus(buyerId, status);

        return orders;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OrderInfoModel viewOrderDetail(@PathVariable long id){
        return orderService.viewOrder(id);
    }

    @RequestMapping(value = "/pay/{id}", method = RequestMethod.POST,
            produces = "application/text; charset=utf-8")
    @ResponseBody
    public String pay(@PathVariable long id){
        return orderService.payOrder(id);
    }
}
