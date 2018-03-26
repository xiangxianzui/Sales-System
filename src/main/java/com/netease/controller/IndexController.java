package com.netease.controller;

import com.google.common.base.Strings;
import com.netease.db.model.OrderInfoModel;
import com.netease.db.model.UserInfoModel;
import com.netease.db.model.beans.OrderGoodsBean;
import com.netease.db.model.enums.OrderStatus;
import com.netease.db.model.enums.UserType;
import com.netease.service.OrderService;
import com.netease.util.ModelConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wanghao on 2/9/18.
 */
@Controller
@RequestMapping(value = {"/"})
public class IndexController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView cart(HttpServletRequest req){
        Object obj = req.getSession().getAttribute(ModelConstant.USER);
        if(obj == null || Strings.isNullOrEmpty(obj.toString())){
            return new ModelAndView("error/403");
        }
        UserInfoModel curUser = (UserInfoModel)obj;
        if(UserType.BUYER.VALUE != curUser.getUsertype()){
            return new ModelAndView("error/403");
        }

        long buyerId = curUser.getId();
        List<OrderGoodsBean> orders = orderService.queryByBuyerAndStatus(buyerId, OrderStatus.CREATED.VALUE);

        ModelAndView mav = new ModelAndView("cart");
        mav.addObject(ModelConstant.ORDER_GOODS_BEAN, orders);
        return mav;
    }

    @RequestMapping(value = "/finance", method = RequestMethod.GET)
    public ModelAndView finance(HttpServletRequest req){
        Object obj = req.getSession().getAttribute(ModelConstant.USER);
        if(obj == null || Strings.isNullOrEmpty(obj.toString())){
            return new ModelAndView("error/403");
        }
        UserInfoModel curUser = (UserInfoModel)obj;
        if(UserType.BUYER.VALUE != curUser.getUsertype()){
            return new ModelAndView("error/403");
        }

        long buyerId = curUser.getId();
        List<OrderGoodsBean> orders = orderService.queryByBuyerAndStatus(buyerId, OrderStatus.PAID.VALUE);

        BigDecimal totalPrice = BigDecimal.valueOf(0);
        for (OrderGoodsBean ogb : orders){
            totalPrice = totalPrice.add(ogb.getOrderPrice().multiply(BigDecimal.valueOf(ogb.getOrderAmount())));
        }
        //System.out.println(totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
        ModelAndView mav = new ModelAndView("finance");
        mav.addObject(ModelConstant.ORDER_GOODS_BEAN, orders);
        mav.addObject(ModelConstant.TOTAL_PRICE, totalPrice);
        return mav;
    }
}
