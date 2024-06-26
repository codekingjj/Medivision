<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Medivision</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/login.css">
    <script src="${pageContext.request.contextPath}/script/user/adminLogin.js"></script>
</head>
<body class="body">
<div class="container">
    <div class="header">
        <div class="logo">
            <div class="mark"></div>
        </div>
    </div>
    <div class="content">
        <form action="/admin/sign-in" method="post" id="login-form">
        <input type="text" name="userId" class="login-write-button" placeholder=" 로그인*">
        <input type="password" name="userPassword" class="login-write-button" placeholder=" 비밀번호*">
        <input type="button" class="login-button" value="로그인" onclick="onsubmitForm()">
        </form>
    </div>
</div>
</body>
</html>
