<%@ page import="com.netease.util.ModelConstant" %>
<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 3/22/18
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="../_partial/_head.jsp"/>
<title>编辑 - 销售系统</title>

<body>
<jsp:include page="../_partial/_header.jsp"/>

<div class="container">
    <div id="page">
        <span class="title">商品编辑</span>
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
                                <input type="radio" name="radioOptions" id="radio1" value="1" checked> URL
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="radioOptions" id="radio2" value="2" onclick="showModal()"> 上传
                            </label>
                            <f:input path="image" id="inputImageUrl" cssClass="form-control" oninput="urlOnInput(this.value)"/>
                            <f:errors path="image" cssClass="alert-danger"/>
                        </div>
                    </div>

                </div>
                <div class="col-md-4">
                        <%--<img src="<%=request.getContextPath()%>/resources/upload/default.jpeg" id="previewImg" style="width: 200px; height: 200px" alt="预览图片">--%>
                    <img src="${Goods.image}" id="previewImg" style="width: 200px; height: 200px; float: right" alt="预览图片">
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
                        <label for="inputPrice" class="control-label col-xs-1">单价(元)</label>
                        <div class="col-xs-4">
                            <f:input path="price" id="inputPrice" cssClass="form-control"/>
                            <f:errors path="price" cssClass="alert-danger"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label for="inputAmount" class="control-label col-xs-1">库存(件)</label>
                        <div class="input-group col-xs-4">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button" onclick="minusone()">-</button>
                                </span>
                            <f:input path="amount" id="inputAmount" cssClass="form-control text-center" onkeyup="value=value.replace(/[^\d]/g,'1')" placeholder="请输入数字"/>
                            <f:errors path="amount" cssClass="alert-danger"/>
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button" onclick="plusone()">+</button>
                                </span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <a class="btn btn-default btn-lg" href="/index" style="float: right">返回</a>
                </div>
                <div class="col-md-2">
                    <input class="btn btn-success btn-lg" style="float: right" type="submit" value="完成">
                </div>
            </div>
        </f:form>

    </div>
</div>

<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="uploadModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="uploadModalLabel">上传图片</h4>
            </div>
            <div class="modal-body">
                <input id="upload_input" type="file" name="attach"/>
            </div>
            <div class="modal-footer">
                <button id="upload_btn" type="button" class="btn btn-success" data-dismiss="modal">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<div class="result-msg">${EditMsg}</div>

<jsp:include page="../_partial/_foot.jsp"/>

<script>

    function showModal(){
        $('#uploadModal').modal('show');
    }

    $(document).ready(function(){
        var formData = new FormData();
        $('#upload_btn').on('click', function(){
            var file = $('#upload_input')[0].files[0];
            if(file){
                var suffix = file.name.substring(file.name.lastIndexOf(".")).toLowerCase();
                if(!suffix.match(/.jpg|.jpeg|.gif|.png|.bmp|.ico/i)){
                    alert("请上传图片(png,jpg,gif)");
                }
                else{
                    var max_size = 1048576;
                    if(file.size > max_size){
                        alert("文件超过1M");
                    }
                    else{
                        formData.append('attach', file);
                        $.ajax({
                            url: '/upload',
                            type: 'POST',
                            data: formData,
                            processData: false,
                            contentType: false,
                            success: function(data){
                                $('#inputImageUrl').val(data);
                                $('#previewImg').attr("src", data);
                            }
                        });
                    }
                }
            }
            else{
                alert("请先选择文件");
            }
        });
    });

    $(document).ready(function(){
        $('#inputAmount').val(${Goods.amount});
    });

    function urlOnInput(val){
        $('#previewImg').attr("src", val);
    }

    function minusone(){
        var val = $('#inputAmount').val();
        val = eval(val)-1;
        if(val <= 0){
            val = 0;
        }
        $('#inputAmount').val(val);
    }

    function plusone(){
        var val = $('#inputAmount').val();
        val = eval(val)+1;
        $('#inputAmount').val(val);
    }
</script>
</body>
</html>
