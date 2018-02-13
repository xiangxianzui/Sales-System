<%@ page import="com.netease.db.model.enums.UserType" %>
<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 2/11/18
  Time: 10:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="../_partial/_head.jsp"/>
<head>
    <title>商品详情 - 销售系统</title>
</head>
<body>
<jsp:include page="../_partial/_header.jsp"/>

<div class="container">
    <div id="page">
        <a class="title" href="#">商品详情</a>
        <hr>

        <c:choose>
            <c:when test="${User == null}">
                未登录
            </c:when>
            <c:otherwise>
                <c:set var = "buyer" value = "<%= UserType.BUYER.VALUE %>"/>
                <c:set var = "seller" value = "<%= UserType.SELLER.VALUE %>"/>
                <c:choose>
                    <c:when test="${User.usertype == buyer}">
                        买家
                    </c:when>
                    <c:otherwise>
                        <a class="title" href="#">编辑</a>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>

    </div>
</div>

</body>
</html>
