package com.netease.controller;

import com.google.common.base.Strings;
import com.netease.db.model.UserInfoModel;
import com.netease.service.UserService;
import com.netease.service.enums.LoginMsg;
import com.netease.service.enums.LogoutMsg;
import com.netease.util.ModelConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by wanghao on 2/9/18.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        UserInfoModel userInfoModel = new UserInfoModel();
        ModelAndView mav = new ModelAndView("user/login");
        mav.addObject(ModelConstant.USER, userInfoModel);
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest req, ModelMap modelMap,
                        @Validated @ModelAttribute(ModelConstant.USER) UserInfoModel loginUser,
                        BindingResult br) {//验证结果一定要紧跟@Validated参数后面写
        String redirectUrl = req.getParameter("redirect");
        if (br.hasErrors()) {
            return "user/login";
        } else {
            String msg = userService.login(loginUser, req);
            if(LoginMsg.SUCCESS.EXTVALUE.equals(msg)){//登录成功
                if(!Strings.isNullOrEmpty(redirectUrl)){
                    return "redirect:"+redirectUrl;
                }
                else{
                    return "redirect:../index";
                }
            }
            else{//登录失败
                modelMap.addAttribute(ModelConstant.LOGIN_MSG, msg);
            }
            return "user/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        String msg = userService.logout(session);
        if(LogoutMsg.SUCCESS.EXTVALUE.equals(msg)){

        }
        if(LogoutMsg.FAIL.EXTVALUE.equals(msg)){

        }
        return "redirect:../index";
    }

}
