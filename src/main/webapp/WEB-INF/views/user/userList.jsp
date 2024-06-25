<%--
  Created by IntelliJ IDEA.
  User: jujae
  Date: 2024-06-25
  Time: 오후 5:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Medivision</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/userList.css">
</head>
<body class="body">
<div class="container">
    <div class="header">
        <div class="logo">
            <div class="mark"></div>
        </div>
        <div class="menu">
            <div class="logout-img">
            </div>
            <div class="logout-text">
                로그아웃
            </div>
        </div>
    </div>
    <div class="content">
        <div class="notice-box">
            <div class="notice-title">계정 아이디는</div>
            <div class="notice-id">[xxxxxxxx]</div>
            <div class="notice-title">입니다.</div>
            <div class="notice-password">[초기 비밀번호는 주민번호 뒷자리]</div>
        </div>
        <input type="button" class="join-button" value="확인">
    </div>
</div>
</body>
</html>
