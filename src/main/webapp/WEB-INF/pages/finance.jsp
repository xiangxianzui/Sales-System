<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 3/24/18
  Time: 2:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="_partial/_head.jsp"/>

<head>
    <title>财务 - 销售系统</title>
</head>

<body>
<jsp:include page="_partial/_header.jsp"/>

<div class="container">
    <div id="page">
        <span class="title">财务</span>
        <hr>

        <c:set var="orders" value="${Order_Goods_Bean}"/>
        <table class="table table-hover table-responsive table-striped table-bordered text-center">
            <thead>
            <tr>
                <td>图片</td>
                <td>标题</td>
                <td>购买时间</td>
                <td>支付时间</td>
                <td>成交价格</td>
                <td>成交数量</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td><img src="${order.goodsImage}" style="width: 100px;height: 100px"></td>
                    <td><a class="btn-link" href="/goods/detail/${order.goodsId}">${order.goodsTitle}</a></td>
                    <td>${order.orderCreateTime.toLocaleString()}</td>
                    <td>${order.orderPayTime.toLocaleString()}</td>
                    <td>&yen;${order.orderPrice}</td>
                    <td>${order.orderAmount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="row">
            <div class="col-md-8"></div>
            <div class="col-md-4 title">
                <p style="float: right">总计:&yen;${Total_Price}</p>
            </div>
        </div>

    </div>
</div>

<div class="modal fade" id="confirmPayModal" tabindex="-1" role="dialog" aria-labelledby="confirmPayModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="confirmPayModalLabel">确认支付</h4>
            </div>
            <div class="modal-body">
                <div class="hide" id="modal_orderid"></div>
                <div class="row">
                    <div class="col-md-4"></div>
                    <div class="col-md-4">支付金额：<span id="modal_pay_info"></span></div>
                    <div class="col-md-4"></div>
                </div>
                <div class="row">
                    <div class="col-md-4"></div>
                    <div class="col-md-4">支付方式：<label>
                        <input type="radio" value="0" checked/>
                    </label>网易钱包</div>
                    <div class="col-md-4"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="confirmPay()">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="_partial/_foot.jsp"/>

<script>

</script>
</body>
</html>
