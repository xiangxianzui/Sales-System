<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 2/9/18
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.netease.db.model.enums.UserType" %>

<header>
    <!--navbar starts-->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <a title="销售系统" class="navbar-brand" href="#"><span>销售系统</span></a>
        </div>
        <div class="pull-right">
            <ul class="nav navbar-nav">
                <c:choose>
                    <c:when test="${User == null}">
                        <li title='登录'><a href="/user/login"><span class="glyphicon glyphicon-log-in"> 登录</span></a></li>
                    </c:when>
                    <c:otherwise>
                        <li title='昵称'><a href="#"><span class="glyphicon glyphicon-user"> ${User.nickname}</span></a></li>
                        <c:set var = "buyer" value = "<%= UserType.BUYER.VALUE %>"/>
                        <c:set var = "seller" value = "<%= UserType.SELLER.VALUE %>"/>
                        <c:choose>
                            <c:when test="${User.usertype == buyer}">
                                <li title='财务'><a href="#"><span class="glyphicon glyphicon-usd"> 财务</span></a></li>
                                <li title='购物车'><a href='#'><span class="glyphicon glyphicon-shopping-cart"> 购物车</span></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li title='发布'><a href="#"><span class="glyphicon glyphicon-send"> 发布</span></a></li>
                            </c:otherwise>
                        </c:choose>
                        <li title='退出'><a href="/user/logout"><span class="glyphicon glyphicon-off"> 退出</span></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
    <!--navbar ends-->
</header>
