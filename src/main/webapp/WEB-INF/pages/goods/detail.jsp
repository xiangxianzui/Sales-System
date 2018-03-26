<%@ page import="com.netease.db.model.enums.UserType" %>
<%@ page import="com.netease.db.model.enums.OrderStatus" %>
<%@ page import="com.netease.service.enums.PurchaseMsg" %>
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
                <div class="row">
                    <div class="col-md-4">
                        <img src="${Goods.image}" id="previewImg1" style="width: 300px; height: 300px;" alt="预览图片" title="图片">
                    </div>
                    <div class="col-md-8">
                        <p class="title" title="标题">${Goods.title}</p>
                        <p title="摘要">${Goods.abs}</p>
                        <p title="价格">¥${Goods.price}</p>
                        <p title="库存">库存:${Goods.amount}件</p>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-md-12">
                        <p title="正文描述">${Goods.des}</p>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <c:set var = "buyer" value = "<%= UserType.BUYER.VALUE %>"/>
                <c:set var = "seller" value = "<%= UserType.SELLER.VALUE %>"/>
                <div class="row">
                    <div class="col-md-4">
                        <img src="${Goods.image}" id="previewImg2" style="width: 300px; height: 300px;" alt="预览图片" title="图片">
                    </div>
                    <div class="col-md-8">
                        <p class="title" title="标题">${Goods.title}</p>
                        <p title="摘要">${Goods.abs}</p>
                        <p title="价格">¥${Goods.price}</p>
                        <p title="库存">库存:${Goods.amount}件</p>
                        <c:choose>
                        <c:when test="${User.usertype == buyer}">
                            <div id="havebuy"></div>
                            <div id="havenotbuy"></div>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-success btn-lg" href="/goods/edit/${Goods.id}">编辑</a>
                        </c:otherwise>
                        </c:choose>
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

    </div>
</div>

<div class="modal fade" id="confirmBuyModal" tabindex="-1" role="dialog" aria-labelledby="confirmBuyModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="confirmBuyModalLabel">选择数量</h4>
            </div>
            <div class="modal-body">
                <div class="row text-center">
                    <div class="col-md-4"></div>
                    <div class="input-group col-md-4">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" onclick="minusone()">-</button>
                    </span>
                        <input id="input_amount" type="text" class="form-control text-center" value="1" onkeyup="value=value.replace(/[^\d]/g,'1')" placeholder="请输入数字">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" onclick="plusone()">+</button>
                    </span>
                    </div>
                    <div class="col-md-4"></div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="confirmBuy()">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="viewOrderModal" tabindex="-1" role="dialog" aria-labelledby="viewOrderModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="viewOrderModalLabel">查看此商品已购订单</h4>
            </div>
            <div class="modal-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th>创建时间</th>
                        <th>单价</th>
                        <th>数量</th>
                        <th>支付情况</th>
                    </tr>
                    </thead>
                    <tbody id="orders_tbody">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../_partial/_foot.jsp"/>

<script>

    $(document).ready(function(){
        $.ajax({
            url: '/order/listByBuyerAndGoods?goodsId='+"${Goods.id}",
            success: function(data){
                var orderList = data;
                if(orderList !== null && orderList.length !== 0){
                    var orders_tbody_html = "";
                    orderList.forEach(function(b){
                        var orderId = b.id;
                        var orderCreateTime = b.createTime;
                        var orderPrice = b.price;
                        var orderAmount = b.amount;
                        var orderStatus = b.status;
                        var orderStatusInfo;
                        if(orderStatus=="<%= OrderStatus.PAID.VALUE %>"){
                            orderStatusInfo = "已支付，支付时间："+new Date(b.payTime).toLocaleString();
                        }
                        else if(orderStatus=="<%= OrderStatus.CREATED.VALUE %>"){
                            orderStatusInfo = "未支付";
                        }
                        var row_html = "<tr>" +
                                "<td>"+new Date(orderCreateTime).toLocaleString()+"</td>" +
                                "<td>&yen;"+orderPrice+"</td>" +
                                "<td>"+orderAmount+"</td>" +
                                "<td>"+orderStatusInfo+"</td>" +
                                "</tr>";
                        orders_tbody_html += row_html;
                    });
                    $('#orders_tbody').html(orders_tbody_html);
                    var buyBtn_html = "<button class='btn btn-success btn-lg' onclick='showModal(1)'>再次购买</button>";
                    var viewBtn_html = "<button class='btn btn-info btn-lg' onclick='showModal(2)'>此商品已购订单</button>";
                    $('#havebuy').html(buyBtn_html + "&nbsp;&nbsp;&nbsp;&nbsp;" + viewBtn_html);
                }
                else{//买家从未购买过本商品
                    //alert("买家从未购买过本商品");
                    $('#havenotbuy').html("<button class='btn btn-success btn-lg' onclick='showModal(1)'>购买</button>");
                }
            }
        });
    });

    function showModal(val){
        if(val == 1){
            $('#confirmBuyModal').modal('show');
        }
        if(val == 2){
            $('#viewOrderModal').modal('show');
        }
    }

    function minusone(){
        var val = $('#input_amount').val();
        val = eval(val)-1;
        $('#input_amount').val(val);
    }

    function plusone(){
        var val = $('#input_amount').val();
        val = eval(val)+1;
        $('#input_amount').val(val);
    }

    function confirmBuy(){
        var val = $('#input_amount').val();
        val = eval(val);
        var min = 1;
        var max = ${Goods.amount};
        if(val<min) {
            alert("请输入正整数");
            return;
        }
        if(val>max) {
            alert("库存："+max+"，库存不足，无法购买");
            return;
        }

        $.ajax({
            url: '/goods/purchase?goodsId=${Goods.id}'+'&amount='+val,
            type: 'POST',
            data: [],
            success: function(data){
                var msg = data;
                if(msg == "<%= PurchaseMsg.SUCCESS.EXTVALUE %>"){
                    alert(msg);
                    window.location.href='/index';
                }
                else{
                    alert(msg);
                }
            }
        });

    }
</script>

</body>
</html>
