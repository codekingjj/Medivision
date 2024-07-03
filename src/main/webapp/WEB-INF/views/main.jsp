<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>header</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/main/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/main/alarm.css">
</head>
<body>
<div class="container">
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
        <div class="header-menu">
            <div class="menu diselected"></div>
            <div class="menu option">차트 검색</div>
            <div class="menu option">담당 환자 설정</div>
            <div class="menu selected dropdown">
                <div class="dropdown-title">로그 기록 확인</div>
                <div class="dropdown-content-box">
                    <div class="dropdown-content"><a class="dropdown-content-a" href="#">로그인 로그 기록</a></div>
                    <div class="dropdown-content"><a class="dropdown-content-a" href="#">환자 차트 열람 로그 기록</a></div>
                    <div class="dropdown-content"><a class="dropdown-content-a" href="#">리포트 로그 기록</a></div>
                </div>
            </div>
            <div class="menu option">마이 페이지</div>
            <div class="menu diselected-side-menu">
                <div class="side-menu">
                    <div class="side-menu-icon side-menu-icon-chat"></div>
                    <div class="side-menu-icon side-menu-icon-bell">
                        <div class="nav-btn" id="notification"><span class="note-num">3</span></div>
                    </div>
                    <div class="alarm-box">
                        <div class="alarm-header">알림</div>
                        <div class="alarm-content">
                            <p>알림 내용1알림 내용1알림 내용1알림 내용1알림</p>
                            <p>알림 내용2</p>
                            <p>알림 내용3</p>
                            <p>알림 내용2</p>
                            <p>알림 내용3</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="content"></div>
    <div class="footer"></div>
</div>
</body>
</html>