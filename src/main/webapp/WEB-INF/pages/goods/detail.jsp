<%@ page import="com.netease.db.model.enums.UserType" %>
<%@ page import="com.netease.util.ModelConstant" %>
<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 2/11/18
  Time: 10:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
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
        <span class="title">商品详情</span>
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
                        <div class="row">
                            <div class="col-md-4">
                                <img src="${Goods.image}" id="previewImg" style="width: 300px; height: 300px;" alt="预览图片" title="图片">
                            </div>
                            <div class="col-md-8">
                                <p class="title" title="标题">${Goods.title}</p>
                                <p title="摘要">${Goods.abs}</p>
                                <p title="价格">¥${Goods.price}</p>
                                <a class="btn btn-success btn-lg" href="/goods/edit/${Goods.id}">编辑</a>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-md-12">
                                <p title="正文描述">${Goods.des}</p>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>

    </div>
</div>

</body>
</html>
