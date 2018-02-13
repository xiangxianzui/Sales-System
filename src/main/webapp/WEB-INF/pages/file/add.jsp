<%--
  Created by IntelliJ IDEA.
  User: wanghao
  Date: 2/10/18
  Time: 9:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
</head>
<body>
<form:form method="post" enctype="multipart/form-data">
    file1: <input type="file" name="file"/><br>
    <input type="submit" value="submit"/>
</form:form>
</body>
</html>
