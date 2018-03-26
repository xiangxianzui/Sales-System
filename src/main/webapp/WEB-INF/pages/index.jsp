<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.netease.db.model.enums.UserType" %>

<html>
<jsp:include page="_partial/_head.jsp"/>

<head>
    <title>首页 - 销售系统</title>
</head>
<body>
<jsp:include page="_partial/_header.jsp"/>


<div class="container">
    <div id="page">
        <c:choose>
            <c:when test="${User == null}">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a class="nav-link title active" href="#">全部商品</a>
                    </li>
                </ul>
            </c:when>
            <c:otherwise>
                <c:set var = "buyer" value = "<%= UserType.BUYER.VALUE %>"/>
                <c:set var = "seller" value = "<%= UserType.SELLER.VALUE %>"/>
                <c:choose>
                    <c:when test="${User.usertype == buyer}">
                        <ul class="nav nav-tabs">
                            <li class="nav-item">
                                <a class="nav-link title active" href="#" onclick="ajaxQueryAllBuyer()">全部商品</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link title" href="#" onclick="ajaxQueryBuy(true)">已购买</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link title" href="#" onclick="ajaxQueryBuy(false)">未购买</a>
                            </li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="nav nav-tabs">
                            <li class="nav-item">
                                <a class="nav-link title active" href="#" onclick="ajaxQueryAllSeller()">全部商品</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link title" href="#" onclick="ajaxQuerySell(true)">已出售</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link title" href="#" onclick="ajaxQuerySell(false)">未出售</a>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>

        <div>
            <div>
                <h4 class="right">总数：<span id='total-count'>0</span></h4>
            </div>
            <div class='row' id='all-goods-div'>

            </div>
        </div>
    </div>
</div>

<%--<div class="text-center">
    <nav>
        <ul class="pagination">
            <li><a href="<c:url value="/goods/list?page=1"/>">首页</a></li>
            <li><a href="<c:url value="/goods/list?page=${page-1}"/>">&laquo;</a></li>
            <c:choose>
                <c:when test="${pageNum > 10}">
                    <c:forEach begin="${page}" end="${page+9>pageNum ? pageNum : page+9}" varStatus="loop">
                        <c:set var="active" value="${loop.index==page?'active':''}"/>
                        <li class="${active}">
                            <a href="<c:url value="/goods/list?page=${loop.index}"/>">${loop.index}</a>
                        </li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach begin="1" end="${pageNum}" varStatus="loop">
                        <c:set var="active" value="${loop.index==page?'active':''}"/>
                        <li class="${active}">
                            <a href="<c:url value="/goods/list?page=${loop.index}"/>">${loop.index}</a>
                        </li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <li><a href="<c:url value="/goods/list?page=${page+1}"/>">&raquo;</a></li>
            <li><a href="<c:url value="/goods/list?page=${pageNum}"/>">尾页</a></li>

        </ul>
    </nav>
</div>--%>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="exampleModalLabel">信息</h4>
            </div>
            <div class="modal-body">
                <h3 class="modal-title">没有更多的商品了...</h3>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">好的</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="_partial/_footer.jsp"/>

<jsp:include page="./_partial/_foot.jsp"/>

<script>
    $(document).ready(function(){
        if(${User==null}){
            ajaxQueryAll();
            return;
        }
        var buyer = <%= UserType.BUYER.VALUE %>;
        var seller = <%= UserType.SELLER.VALUE %>;
        if(${User.usertype == buyer}){
            ajaxQueryAllBuyer();
            return;
        }
        if(${User.usertype == seller}){
            ajaxQueryAllSeller();
            return;
        }
    });


    function ajaxQueryAll(){
        $.ajax({
            url: 'goods/listAll',
            success: function(data){
                $('#all-goods-div').html("");
                var goodsList = data;
                if(goodsList !== null && goodsList.length !== 0){
                    goodsList.forEach(function(b){
                        var goodsId = b.id;
                        var goodsTitle = b.title;
                        var goodsImg = b.image;
                        var goodsPrice = b.price;
                        var html = "<div class='col-sm-6 col-md-3'><div class='thumbnail'><a href='/goods/detail/"+goodsId+"'><img style='width: 256px;height: 256px' src='"+goodsImg+"' alt='"+goodsTitle+"'></a><div class='caption'><p>"+goodsTitle+"</p><p>&yen"+goodsPrice+"</p></div></div></div>";
                        $('#all-goods-div').append(html);
                    });
                    $('#total-count').html(goodsList.length);
                }
                else{//没有商品
                    $('#exampleModal').modal('show');
                }
            }
        });
    }

    function ajaxQueryAllBuyer(){
        $.ajax({
            url: 'goods/listAllBuyer',
            success: function(data){
                $('#all-goods-div').html("");
                var goodsList = data;
                if(goodsList !== null && goodsList.length !== 0){
                    goodsList.forEach(function(b){
                        var goodsId = b.goodsId;
                        var goodsTitle = b.goodsTitle;
                        var goodsImg = b.goodsImage;
                        var goodsPrice = b.goodsPrice;
                        var isBuy = b.isBuy;
                        if(isBuy == 'true'){
                            var html = "<div class='col-sm-6 col-md-3'><div class='thumbnail'><a href='/goods/detail/"+goodsId+"'><img style='width: 256px;height: 256px' src='"+goodsImg+"' alt='"+goodsTitle+"'></a><div class='caption'><p>"+goodsTitle+"<span>(已购)</span></p><p>&yen;"+goodsPrice+"</p></div></div></div>";
                            $('#all-goods-div').append(html);
                        }
                        else{
                            var html = "<div class='col-sm-6 col-md-3'><div class='thumbnail'><a href='/goods/detail/"+goodsId+"'><img style='width: 256px;height: 256px' src='"+goodsImg+"' alt='"+goodsTitle+"'></a><div class='caption'><p>"+goodsTitle+"</p><p>&yen;"+goodsPrice+"</p></div></div></div>";
                            $('#all-goods-div').append(html);
                        }
                    });
                    $('#total-count').html(goodsList.length);
                }
                else{//没有商品
                    $('#exampleModal').modal('show');
                }
            }
        });
    }

    function ajaxQueryAllSeller(){
        $.ajax({
            url: 'goods/listAllSeller',
            success: function(data){
                $('#all-goods-div').html("");
                var goodsList = data;
                if(goodsList !== null && goodsList.length !== 0){
                    goodsList.forEach(function(b){
                        var goodsId = b.goodsId;
                        var goodsTitle = b.goodsTitle;
                        var goodsImg = b.goodsImage;
                        var goodsPrice = b.goodsPrice;
                        var isSell = b.isSell;
                        if(isSell == 'true'){
                            var html = "<div class='col-sm-6 col-md-3'><div class='thumbnail'><a href='/goods/detail/"+goodsId+"'><img style='width: 256px;height: 256px' src='"+goodsImg+"' alt='"+goodsTitle+"'></a><div class='caption'><p>"+goodsTitle+"<span>(已售)</span></p><p>&yen;"+goodsPrice+"</p></div></div></div>";
                            $('#all-goods-div').append(html);
                        }
                        else{
                            var html = "<div class='col-sm-6 col-md-3'><div class='thumbnail'><a href='/goods/detail/"+goodsId+"'><img style='width: 256px;height: 256px' src='"+goodsImg+"' alt='"+goodsTitle+"'></a><div class='caption'><p>"+goodsTitle+"</p><p>&yen;"+goodsPrice+"</p></div></div></div>";
                            $('#all-goods-div').append(html);
                        }
                    });
                    $('#total-count').html(goodsList.length);
                }
                else{//没有商品
                    $('#exampleModal').modal('show');
                }
            }
        });
    }

    function ajaxQueryBuy(flag){
        $.ajax({
            url: 'goods/listByBuyer?flag='+flag,
            success: function(data){
                $('#all-goods-div').html("");
                var goodsList = data;
                if(goodsList !== null && goodsList.length !== 0){
                    goodsList.forEach(function(b){
                        var goodsId = b.goodsId;
                        var goodsTitle = b.goodsTitle;
                        var goodsImg = b.goodsImage;
                        var goodsPrice = b.goodsPrice;
                        if(flag == true){
                            var buyAmount = b.buyAmount;
                            var html = "<div class='col-sm-6 col-md-3'><div class='thumbnail'><a href='/goods/detail/"+goodsId+"'><img style='width: 256px;height: 256px' src='"+goodsImg+"' alt='"+goodsTitle+"'></a><div class='caption'><p>"+goodsTitle+"<span>(已购:"+buyAmount+"件)</span></p><p>&yen;"+goodsPrice+"</p></div></div></div>";
                            $('#all-goods-div').append(html);
                        }
                        else{
                            var html = "<div class='col-sm-6 col-md-3'><div class='thumbnail'><a href='/goods/detail/"+goodsId+"'><img style='width: 256px;height: 256px' src='"+goodsImg+"' alt='"+goodsTitle+"'></a><div class='caption'><p>"+goodsTitle+"</p><p>&yen;"+goodsPrice+"</p></div></div></div>";
                            $('#all-goods-div').append(html);
                        }

                    });
                    $('#total-count').html(goodsList.length);
                }
                else{//没有商品
                    $('#exampleModal').modal('show');
                }
            }
        });
    }

    function ajaxQuerySell(flag){
        $.ajax({
            url: 'goods/listBySeller?flag='+flag,
            success: function(data){
                $('#all-goods-div').html("");
                var goodsList = data;
                if(goodsList !== null && goodsList.length !== 0){
                    goodsList.forEach(function(b){
                        var goodsId = b.goodsId;
                        var goodsTitle = b.goodsTitle;
                        var goodsImg = b.goodsImage;
                        var goodsPrice = b.goodsPrice;
                        if(flag == true){
                            var soldAmount = b.soldAmount;
                            var html = "<div class='col-sm-6 col-md-3'><div class='thumbnail'><a href='/goods/detail/"+goodsId+"'><img style='width: 256px;height: 256px' src='"+goodsImg+"' alt='"+goodsTitle+"'></a><div class='caption'><p>"+goodsTitle+"<a class='btn btn-link'>(已售:"+soldAmount+"件)</a></p><p>&yen;"+goodsPrice+"</p></div></div></div>";
                            $('#all-goods-div').append(html);
                        }
                        else{
                            var html = "<div class='col-sm-6 col-md-3'><div class='thumbnail'><a href='/goods/detail/"+goodsId+"'><img style='width: 256px;height: 256px' src='"+goodsImg+"' alt='"+goodsTitle+"'></a><div class='caption'><p>"+goodsTitle+"<a href='/goods/delete/"+goodsId+"' class='btn btn-link'>删除</a></p><p>&yen;"+goodsPrice+"</p></div></div></div>";
                            $('#all-goods-div').append(html);
                        }
                    });
                    $('#total-count').html(goodsList.length);
                }
                else{//没有商品
                    $('#exampleModal').modal('show');
                }
            }
        });
    }

</script>

</body>
</html>