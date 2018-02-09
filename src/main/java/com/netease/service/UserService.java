package com.netease.service;

import com.netease.db.model.UserInfoModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by wanghao on 8/5/17.
 */

public interface UserService {
    String register(UserInfoModel userInfoModel);

    String login(UserInfoModel loginUser, HttpServletRequest req);

    String logout(HttpSession session);

}
