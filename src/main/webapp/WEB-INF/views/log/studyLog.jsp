<%--
  Created by IntelliJ IDEA.
  User: jujae
  Date: 2024-07-02
  Time: 오전 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/content.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/log/log.css">
  <script src="${pageContext.request.contextPath}/script/log/loginLog.js"></script>
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
      <div class="menu diselected-pc"></div>
      <div class="menu option">차트 검색</div>
      <div class="menu option">담당 환자 설정</div>
      <div class="menu selected dropdown">
        <div class="dropdown-title">로그 기록 확인</div>
        <div class="dropdown-content-box">
          <div class="dropdown-content"><a class="dropdown-content-a" onclick="loginLog()">로그인 로그 기록</a></div>
          <div class="dropdown-content"><a class="dropdown-content-a" href="#">환자 차트 열람 로그 기록</a></div>
          <div class="dropdown-content"><a class="dropdown-content-a" href="#">리포트 로그 기록</a></div>
        </div>
      </div>
      <div class="menu option">마이 페이지</div>
      <div class="menu diselected-side-menu">
        <div class="side-menu">
          <div class="side-menu-icon side-menu-icon-chat"></div>
          <div class="side-menu-icon side-menu-icon-bell"></div>
        </div>
      </div>
    </div>
  </div>
  <div class="aside">
    <div class="aside-content">로그인 로그 기록</div>
    <div class="aside-content aside-selected">환자 차트 열람<br> 로그 기록</div>
    <div class="aside-content">리포트 로그 기록</div>
  </div>
  <div class="content">
    <div class="content-top">
      <div class="content-top-title">로그인 로그 기록</div>
    </div>
    <div class="content-bottom">
      <div class="content-bottom-box">
        <div class="content-bottom-table">
          <table class="log-table">
            <thead>
            <th>아이디</th>
            <th>아이피</th>
            <th>접속시간</th>
            <th>환자 차트</th>
            </thead>
            <tbody>
            <tr>
              <th>sadf</th>
              <th>127.0.0.1</th>
              <th>2024.12.30 15:30</th>
              <th>123</th>
            </tr>
            <tr>
              <th>sadf</th>
              <th>127.0.0.1</th>
              <th>2024.12.30 15:30</th>
              <th>123</th>
            </tr>
            <tr>
              <th>sadf</th>
              <th>127.0.0.1</th>
              <th>2024.12.30 15:30</th>
              <th>123</th>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <div class="footer">
    <div class="footer-left">
      copyright@Megastudy
    </div>
    <div class="footer-right"></div>
  </div>
</div>
</body>
</html>
