package com.netease.db.dao;

import com.netease.db.model.GoodsInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by wanghao on 2/10/18.
 */
public interface GoodsInfoDao {

    int save(GoodsInfoModel goodsInfoModel);

    GoodsInfoModel findById(@Param("id") long id);

    GoodsInfoModel findByTitle(@Param("title") String title);

    List<GoodsInfoModel> queryAll();

    //分页查询
    List<GoodsInfoModel> queryPagination(@Param("limit") int limit, @Param("offset") int offset);

    //得到goods_info表一共有多少条数据
    int getCount();

    List<Map<String, Object>> findByBuyer(long buyerId);

    List<Map<String, Object>> findByBuyerNot(long buyerId);

    List<Map<String, Object>> findBySeller(long buyerId);

    List<Map<String, Object>> findBySellerNot(long buyerId);

}
