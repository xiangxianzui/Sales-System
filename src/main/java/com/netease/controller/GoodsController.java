package com.netease.controller;

import com.google.common.base.Strings;
import com.netease.db.model.GoodsInfoModel;
import com.netease.db.model.OrderInfoModel;
import com.netease.db.model.UserInfoModel;
import com.netease.db.model.enums.UserType;
import com.netease.service.GoodsService;
import com.netease.service.OrderService;
import com.netease.service.enums.DeleteMsg;
import com.netease.service.enums.EditMsg;
import com.netease.service.enums.PublishMsg;
import com.netease.service.enums.PurchaseMsg;
import com.netease.util.ModelConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghao on 2/10/18.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    private final int PAGINATION_LIMIT_SHOW = 12;//每页最多展示的商品数量

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    /*@RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam(value="page",required=true) int page){
        int limit = PAGINATION_LIMIT_SHOW;
        int totalSize = goodsService.getGoodsCount();
        System.out.println(totalSize);
        int pageNum = (totalSize%limit==0) ? totalSize/limit : totalSize/limit+1;
        System.out.println(pageNum);
        if(page<=0){
            page = 1;
        }
        if(page>pageNum){
            page = pageNum;
        }
        int offset = ((page-1)*limit)%totalSize;
        List<GoodsInfoModel> goodsList = goodsService.listPagination(limit, offset);
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("goodsList", goodsList);
        mav.addObject("page", page);
        mav.addObject("pageNum", pageNum);
        return mav;
    }*/

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> listAll(){
        List<Map<String, Object>> goodsList = goodsService.listAll();
        return goodsList;
    }

    @RequestMapping(value = "/listAllBuyer", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> listAllBuyer(HttpServletRequest req){
        Object obj = req.getSession().getAttribute(ModelConstant.USER);
        if(obj == null || Strings.isNullOrEmpty(obj.toString())){
            return null;
        }
        UserInfoModel curUser = (UserInfoModel)obj;
        if(UserType.BUYER.VALUE != curUser.getUsertype()){
            return null;
        }

        long buyerId = curUser.getId();
        List<Map<String, Object>> goodsList = goodsService.listAllBuyer(buyerId);
        return goodsList;
    }

    @RequestMapping(value = "/listAllSeller", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> listAllSeller(HttpServletRequest req){
        Object obj = req.getSession().getAttribute(ModelConstant.USER);
        if(obj == null || Strings.isNullOrEmpty(obj.toString())){
            return null;
        }
        UserInfoModel curUser = (UserInfoModel)obj;
        if(UserType.SELLER.VALUE != curUser.getUsertype()){
            return null;
        }

        long sellerId = curUser.getId();
        List<Map<String, Object>> goodsList = goodsService.listAllSeller(sellerId);
        return goodsList;
    }

    @RequestMapping(value = "/listByBuyer", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> listByBuyer(HttpServletRequest req,
                                          @RequestParam(value="flag",required=true) boolean flag){
        Object obj = req.getSession().getAttribute(ModelConstant.USER);
        if(obj == null || Strings.isNullOrEmpty(obj.toString())){
            return null;
        }
        UserInfoModel curUser = (UserInfoModel)obj;
        if(UserType.BUYER.VALUE != curUser.getUsertype()){
            return null;
        }

        List<Map<String, Object>> goodsList = goodsService.listByBuyer(curUser.getId(), flag);

        return goodsList;
    }

    @RequestMapping(value = "/listBySeller", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> listBySeller(HttpServletRequest req,
                                          @RequestParam(value="flag",required=true) boolean flag){
        Object obj = req.getSession().getAttribute(ModelConstant.USER);
        if(obj == null || Strings.isNullOrEmpty(obj.toString())){
            return null;
        }
        UserInfoModel curUser = (UserInfoModel)obj;
        if(UserType.SELLER.VALUE != curUser.getUsertype()){
            return null;
        }

        List<Map<String, Object>> goodsList = goodsService.listBySeller(curUser.getId(), flag);

        return goodsList;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView viewGoodsDetail(@PathVariable long id){
        GoodsInfoModel goods = goodsService.viewGoods(id);
        if(goods != null){
            ModelAndView mav = new ModelAndView("goods/detail");
            mav.addObject(ModelConstant.GOODS, goods);
            return mav;
        }
        else {
            return new ModelAndView("error/404");
        }
    }

    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public ModelAndView publish(HttpServletRequest req) {
        Object obj = req.getSession().getAttribute(ModelConstant.USER);
        if(obj == null || Strings.isNullOrEmpty(obj.toString())){
            return new ModelAndView("error/403");
        }
        UserInfoModel curUser = (UserInfoModel)obj;
        if(UserType.SELLER.VALUE != curUser.getUsertype()){
            return new ModelAndView("error/403");
        }

        GoodsInfoModel goodsInfoModel = new GoodsInfoModel();
        ModelAndView mav = new ModelAndView("goods/publish");
        mav.addObject(ModelConstant.GOODS, goodsInfoModel);
        return mav;
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String publish(ModelMap modelMap, HttpServletRequest req,
                          @Validated @ModelAttribute(ModelConstant.GOODS) GoodsInfoModel goods,
                          BindingResult br) {
        if (br.hasErrors()) {
            return "goods/publish";
        } else {
            String msg = goodsService.publish(goods, req);
            if(PublishMsg.SUCCESS.EXTVALUE.equals(msg)){//发布成功
                return "redirect:../index";
            }
            else{//发布失败
                modelMap.addAttribute(ModelConstant.PUBLISH_MSG, msg);
                return "goods/publish";
            }
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(HttpServletRequest req,
                             @PathVariable("id") long id) {
        Object obj = req.getSession().getAttribute(ModelConstant.USER);
        if(obj == null || Strings.isNullOrEmpty(obj.toString())){
            return new ModelAndView("error/403");
        }
        UserInfoModel curUser = (UserInfoModel)obj;
        if(UserType.SELLER.VALUE != curUser.getUsertype()){
            return new ModelAndView("error/403");
        }

        GoodsInfoModel goods = goodsService.viewGoods(id);
        if(goods != null){
            ModelAndView mav = new ModelAndView("goods/edit");
            mav.addObject(ModelConstant.GOODS, goods);
            return mav;
        }
        else {
            return new ModelAndView("error/404");
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(ModelMap modelMap, HttpServletRequest req,
                       @PathVariable("id") long id,
                       @Validated @ModelAttribute(ModelConstant.GOODS) GoodsInfoModel goods,
                       BindingResult br) {
        if (br.hasErrors()) {
            return "goods/edit";
        } else {
            String msg = goodsService.edit(goods, req);
            if(EditMsg.SUCCESS.EXTVALUE.equals(msg)){//编辑成功
                return "redirect:/index";
            }
            else{//编辑失败
                modelMap.addAttribute(ModelConstant.EDIT_MSG, msg);
                return "goods/edit";
            }
        }
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST,
            produces = "application/text; charset=utf-8")
    @ResponseBody
    public String purchase(HttpServletRequest req,
                           @RequestParam(value = "goodsId", required = true) long goodsId,
                           @RequestParam(value = "amount", required = true) int amount){
        GoodsInfoModel goods = goodsService.viewGoods(goodsId);
        String msg = goodsService.purchase(goods, amount, req);
        return msg;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(HttpServletRequest req,
                         @PathVariable("id") long id){
        GoodsInfoModel goods = goodsService.viewGoods(id);
        String msg = goodsService.delete(goods, req);
        if(DeleteMsg.SUCCESS.EXTVALUE.equals(msg)){
            return "redirect:/index";
        }
        else if(DeleteMsg.FAIL_NO_USER.EXTVALUE.equals(msg)
                || DeleteMsg.FAIL_UNCORRECT_USER.EXTVALUE.equals(msg)
                || DeleteMsg.FAIL_UNKNOWN_USER.EXTVALUE.equals(msg)){
            return "error/403";
        }
        return "error/other";
    }
}
