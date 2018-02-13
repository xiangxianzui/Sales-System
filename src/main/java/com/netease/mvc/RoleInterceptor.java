package com.netease.mvc;

import com.google.common.base.Strings;
import com.netease.db.model.UserInfoModel;
import com.netease.db.model.enums.UserType;
import com.netease.util.ModelConstant;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wanghao on 2/11/18.
 */
public class RoleInterceptor implements HandlerInterceptor {
    private static final Logger logger = Logger.getLogger(RoleInterceptor.class);

    private static final String ERROR_403_URL="/error/403";
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        /* @param create	<code>true</code> to create
        *        			a new session for this request if necessary;
        *			        <code>false</code> to return <code>null</code>
        *			        if there's no current session
        *			        */
        HttpSession session = request.getSession(true);
        //session中获取用户信息
        Object obj = session.getAttribute(ModelConstant.USER);
        if(obj == null || Strings.isNullOrEmpty(obj.toString())){//用户尚未登录
            response.sendRedirect(request.getSession().getServletContext().getContextPath()+ERROR_403_URL);
            return false;
        }

        UserInfoModel curUser = (UserInfoModel)obj;

        final String PUBLISH_URL = "/goods/publish";
        String[] buyerForbids = {PUBLISH_URL};
        if(curUser.getUsertype() == UserType.BUYER.VALUE){//用户角色为“买家”
            for (String buyerForbid : buyerForbids){
                if(buyerForbid.equals(request.getRequestURI())){
                    response.sendRedirect(request.getSession().getServletContext().getContextPath()+ERROR_403_URL);
                    return false;
                }
            }
            return true;
        }

        String[] sellerForbids = {};
        if(curUser.getUsertype() == UserType.SELLER.VALUE){//用户角色为“卖家”
            for (String sellerForbid : sellerForbids){
                if(sellerForbid.equals(request.getRequestURI())){
                    response.sendRedirect(request.getSession().getServletContext().getContextPath()+ERROR_403_URL);
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}