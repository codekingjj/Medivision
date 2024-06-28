<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Medivision</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/login.css">
    <script src="${pageContext.request.contextPath}/script/user/login.js"></script>
</head>
<body class="body">
<div class="container">
    <div class="content-box">
        <div class="mark"></div>
    </div>
    <div class="content-box content-bottom-box">
        <div class="content-top">
            <div class="content-title">
                로 그 인
            </div>
        </div>
        <div class="content-bottom">
            <div class="content-input-box">
                <input type="text" name="userId" id="userId" class="login-id-button" placeholder=" 로그인*">
                <div class="password-box">
                    <input type="password" id="userPassword" name="userPassword" class="login-write-button" placeholder=" 비밀번호*">
                    <div class="password-type-image-box" onclick="onClickPasswordTypeHandler()"></div>
                </div>
                <input type="button" class="login-button" value="로그인" onclick="onsubmitForm()">
            </div>
            <div class="content-error-box"></div>
        </div>
    </div>
</div>
</body>
</html>
