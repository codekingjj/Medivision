<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>header</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/header.css">
</head>
<body>
<div class="header">
    <div class="header-top">
        <div class="logo">
            <div class="mark"></div>
        </div>
        <div class="logout-box">
            <div class="logout-img">
            </div>
            <div class="logout-text">
                로그아웃
            </div>
        </div>
    </div>
    <div class="header-bottom">
        <div class="menu diselected"></div>
        <div class="menu selected">차트 검색</div>
        <div class="menu option">담당 환자 설정</div>
        <div class="menu option">로그 기록 확인</div>
        <div class="menu option">마이 페이지</div>
        <div class="menu diselected side-menu">
            <div class="side-menu-icon side-menu-icon-chat"></div>
            <div class="side-menu-icon side-menu-icon-bell"></div>
        </div>
    </div>
</div>
<div class="content">

</div>
</body>
</html>