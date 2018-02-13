<%@ page import="com.netease.util.ModelConstant" %>
<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 2/10/18
  Time: 8:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.netease.util.MyUtil" %>
<html>
<jsp:include page="../_partial/_head.jsp"/>
<title>发布 - 销售系统</title>

<body>
<jsp:include page="../_partial/_header.jsp"/>

    <div class="container">
        <div id="page">
            <a class="title" href="#">商品发布</a>
            <hr>

            <f:form method="post" modelAttribute="<%= ModelConstant.GOODS %>" cssClass="form-horizontal">
                <div class="row">
                    <div class="col-md-8">
                        <div class="form-group">
                            <label for="inputTitle" class="control-label col-xs-1">标题</label>
                            <div class="col-xs-11">
                                <f:input path="title" placeholder="2~80个字符" id="inputTitle" cssClass="form-control"/>
                                <f:errors path="title" cssClass="alert-danger"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputAbs" class="control-label col-xs-1">摘要</label>
                            <div class="col-xs-11">
                                <f:input path="abs" placeholder="2~140个字符" id="inputAbs" cssClass="form-control"/>
                                <f:errors path="abs" cssClass="alert-danger"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-1">图片</label>
                            <div class="col-xs-11">
                                <label class="radio-inline">
                                    <input type="radio" onchange="radioOnChange()" name="radioOptions" id="radio1" value="1" checked> URL
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" onchange="radioOnChange()" name="radioOptions" id="radio2" value="2"> 上传
                                </label>
                                <f:input path="image" id="inputImageUrl" cssClass="form-control" oninput="urlOnInput(this.value)"/>
                                <f:input path="image" id="inputImageFile" type="file" cssClass="form-control hidden"/>
                                <f:errors path="image" cssClass="alert-danger"/>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-4">
                        <%--<img src="<%=request.getContextPath()%>/resources/upload/default.jpeg" id="previewImg" style="width: 200px; height: 200px" alt="预览图片">--%>
                            <img src="<%=request.getContextPath()%>/resources/upload/default.jpeg" id="previewImg" style="width: 200px; height: 200px; float: right" alt="预览图片">
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-8">
                        <div class="form-group">
                            <label for="inputTextarea" class="control-label col-xs-1">正文</label>
                            <div class="col-xs-11">
                                <f:textarea path="des" placeholder="2~1000个字符" id="inputTextarea" cssClass="form-control" cssStyle="height: 192px"/>
                                <f:errors path="des" cssClass="alert-danger"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-8">
                        <div class="form-group">
                            <label for="inputPrice" class="control-label col-xs-1">单价</label>
                            <div class="col-xs-4">
                                <f:input path="price" id="inputPrice" cssClass="form-control"/>
                                <f:errors path="price" cssClass="alert-danger"/>
                            </div>
                            <div class="col-xs-7">
                                <span class="control-label" style="float: left">元</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-8">
                        <div class="form-group">
                            <label for="inputAmount" class="control-label col-xs-1">库存</label>
                            <div class="col-xs-4">
                                <f:input path="amount" id="inputAmount" cssClass="form-control"/>
                                <f:errors path="amount" cssClass="alert-danger"/>
                            </div>
                            <div class="col-xs-7">
                                <span class="control-label" style="float: left">件</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-10">
                        <input class="btn btn-success btn-lg" style="float: right" type="submit" value="发布">
                    </div>
                </div>
            </f:form>
        </div>
    </div>

<div class="result-msg">${PublishMsg}</div>

<jsp:include page="../_partial/_foot.jsp"/>

<script>

    function radioOnChange(){
        //var radio = document.getElementById("radio1");
        var val = $('input:radio[name="radioOptions"]:checked').val();
        if(val == 1){
            $('#inputImageUrl').removeClass("hidden");
            $('#inputImageFile').addClass("hidden");
        }
        if(val == 2){
            $('#inputImageUrl').addClass("hidden");
            $('#inputImageFile').removeClass("hidden");
        }
    }

    function urlOnInput(val){
        $('#previewImg').attr("src", val);
    }

    function uploadImg(){

    }
</script>

<jsp:include page="../_partial/_foot.jsp"/>
</body>
</html>
