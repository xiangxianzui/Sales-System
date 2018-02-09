<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="_partial/_head.jsp"/>
<body>
<div id="background">
    <div id="page">
        <jsp:include page="_partial/_header.jsp"/>
        <div id="contents">
            <div id="main">
                <div id="adbox">
                    <img src="/resources/images/sale.jpg" alt="Img" />
                </div>
            </div>
            <div id="featured">
                <ul>
                    <li><img src="/resources/images/shirt-red.jpg" alt="shirt" /></li>
                    <li><img src="/resources/images/shirt-orange.jpg" alt="shirt" /></li>
                    <li><img src="/resources/images/shirt-green.jpg" alt="shirt" /></li>
                    <li class="last"><img src="/resources/images/shirt-blue.jpg" alt="shirt" /></li>
                </ul>
                <a href="shop.html" class="button">shop here!</a>
            </div>
        </div> <!-- /#contents -->
        <jsp:include page="_partial/_footer.jsp"/>
    </div> <!-- /#page -->
</div> <!-- /#background -->
<jsp:include page="./_partial/_foot.jsp"/>
</body>
</html>