<%--
  Created by IntelliJ IDEA.
  User: jujae
  Date: 2024-06-26
  Time: 오전 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Medivision</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/select.css">
</head>
<body class="body">
<div class="container">
    <div class="header">
        <div class="logo">
            <div class="mark"></div>
        </div>
    </div>
    <div class="content">
        <div class="box left">
            <input type="button" class="select-buuton" value="일반 사용자" onclick="location.href='/auth/sign-in'">
        </div>
        <div class="box right">
            <input type="button" class="select-buuton" value="관리자" onclick="location.href='/admin/sign-in'">
        </div>
    </div>
</div>
</body>
</html>
