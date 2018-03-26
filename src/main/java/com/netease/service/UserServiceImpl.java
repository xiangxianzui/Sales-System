package com.netease.service;

import com.netease.db.dao.UserInfoDao;
import com.netease.db.model.UserInfoModel;
import com.netease.service.enums.LoginMsg;
import com.netease.service.enums.LogoutMsg;
import com.netease.util.ModelConstant;
import com.netease.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by wanghao on 2/9/18.
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public String register(UserInfoModel userInfoModel) {
        return null;
    }

    @Override
    public String login(UserInfoModel loginUser, HttpServletRequest req) {
        String retMsg;

        String nickname = loginUser.getNickname();
        UserInfoModel userInfoModel = userInfoDao.findByNickname(nickname);
        if(userInfoModel == null){
            retMsg = LoginMsg.FAIL_NO_USER.EXTVALUE;
            return retMsg;
        }
        String password = loginUser.getPassword();
        String MD5Pwd = MD5Util.MD5(password);
        if(!userInfoModel.getPassword().equalsIgnoreCase(MD5Pwd)){
            retMsg = LoginMsg.FAIL_WRONG_PWD.EXTVALUE;
            return retMsg;
        }

        req.getSession().setAttribute(ModelConstant.USER, userInfoModel);
        retMsg = LoginMsg.SUCCESS.EXTVALUE;
        return retMsg;
    }

    @Override
    public String logout(HttpSession session) {
        String retMsg;
        UserInfoModel curUser = (UserInfoModel)session.getAttribute(ModelConstant.USER);
        if(curUser == null){
            retMsg = LogoutMsg.FAIL.EXTVALUE;
        }
        else {
            session.invalidate();
            retMsg = LogoutMsg.SUCCESS.EXTVALUE;
        }
        return retMsg;
    }


}
