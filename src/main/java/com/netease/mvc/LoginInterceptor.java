package com.netease.mvc;

import com.netease.util.ModelConstant;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wanghao on 3/3/18.
 */
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = Logger.getLogger(LoginInterceptor.class);

    private static final String INDEX_URL="/index";
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
        if (obj != null && !"".equals(obj.toString())) {
            logger.info("已经登录了，不要重复登录");
            response.sendRedirect(request.getSession().getServletContext().getContextPath()+INDEX_URL);
            return false;
        }
        return true;
    }
}
