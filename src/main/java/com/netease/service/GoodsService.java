package com.netease.service;

import com.netease.db.model.GoodsInfoModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghao on 2/10/18.
 */
public interface GoodsService {

    List<Map<String, Object>> listAll();

    List<Map<String, Object>> listAllBuyer(long buyerId);

    List<Map<String, Object>> listAllSeller(long sellerId);

    List<GoodsInfoModel> listPagination(int limit, int offset);

    //得到goods_info表中一共有多少条数据
    int getGoodsCount();

    String publish(GoodsInfoModel goodsInfoModel, HttpServletRequest req);

    String edit(GoodsInfoModel goodsInfoModel, HttpServletRequest req);

    String delete(GoodsInfoModel goodsInfoModel, HttpServletRequest req);

    GoodsInfoModel viewGoods(long id);

    String purchase(GoodsInfoModel goodsInfoModel, int amount, HttpServletRequest req);


    /**
     * @param buyerId
     * @param flag flag为true时返回已购买所有商品；为false时返回未购买所有商品
     * @return
     */
    List<Map<String, Object>> listByBuyer(long buyerId, boolean flag);

    List<Map<String, Object>> listBySeller(long sellerId, boolean flag);

}
