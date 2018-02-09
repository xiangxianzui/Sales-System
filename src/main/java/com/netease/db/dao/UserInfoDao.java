package com.netease.db.dao;

import com.netease.db.model.UserInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanghao on 2/9/18.
 */
public interface UserInfoDao {

    int save(UserInfoModel userInfoModel);

    UserInfoModel findById(@Param("id") long id);

    UserInfoModel findByNickname(@Param("nickname") String nickname);

    UserInfoModel findByUniqCode(@Param("uniqcode") String uniqcode);

    List<UserInfoModel> queryAll();
}
