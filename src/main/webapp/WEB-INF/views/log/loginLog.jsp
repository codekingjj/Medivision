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

  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/main/alarm.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

  <!-- chat -->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/chat/main.css">
  <script src="${pageContext.request.contextPath}/script/chat/websocket/stomp.js"></script>
  <script src="${pageContext.request.contextPath}/script/chat/websocket/sockjs.min.js"></script>
  <script src="${pageContext.request.contextPath}/script/chat/main.js" type="module" defer></script>
</head>
<body>
<div class="sidebar-chat-root"></div>
<div class="container">
  <div class="header">
    <div class="header-top">
      <div class="logo">
        <div class="mark" onclick="location.href='/main'"></div>
      </div>
      <div class="logout-box">
        <div class="logout-img">
        </div>
        <div class="logout-text" onclick="location.href='/auth/select'">
          로그아웃
        </div>
      </div>
    </div>
    <div class="header-menu">
      <div class="menu diselected"></div>
      <div class="menu option" onclick="location.href='/search'">차트 검색</div>
      <div class="menu option" onclick="location.href='/patientBookmark'">담당 환자 설정</div>
      <div class="menu selected dropdown">
        <div class="dropdown-title">로그 기록 확인</div>
        <div class="dropdown-content-box">
          <div class="dropdown-content"><a class="dropdown-content-a" onclick="location.href='/log/login'">로그인 로그 기록</a></div>
          <div class="dropdown-content"><a class="dropdown-content-a" onclick="location.href='/log/studyKey'">환자 차트 열람 로그 기록</a></div>
          <div class="dropdown-content"><a class="dropdown-content-a" onclick="location.href='/log/report'">리포트 로그 기록</a></div>
        </div>
      </div>
      <div class="menu diselected-side-menu">
        <div class="side-menu">
          <div class="side-menu-icon side-menu-icon-chat" id="btnChatPageOpen"></div>
          <div class="side-menu-icon side-menu-icon-bell">
            <div class="nav-btn" id="notification"></div>
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
          <div id="myModal" class="modal">
            <div class="modal-content">
              <span class="modal-title">알림 내용</span>
              <span class="close">&times;</span>
              <p id="modal-text">Modal Content</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="aside">
    <div class="aside-content aside-selected" onclick="location.href='/log/login'">로그인 로그 기록</div>
    <div class="aside-content" onclick="location.href='/log/studyKey'">환자 차트 열람<br> 로그 기록</div>
    <div class="aside-content" onclick="location.href='/log/report'">리포트 로그 기록</div>
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
            </thead>
            <tbody>
            <tr>
              <th>sadf</th>
              <th>127.0.0.1</th>
              <th>2024.12.30 15:30</th>
            </tr>
            <tr>
              <th>sadf</th>
              <th>127.0.0.1</th>
              <th>2024.12.30 15:30</th>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <div class="footer">
    <div class="footer-left">
      <div class="footer-person">
        <div class="person">
          <div class="footer-right-title">강다연 : da092511@naver.com</div>
          <div class="footer-right-logo" onclick="location.href='https://github.com/da092511'"></div>
        </div>
        <div class="person">
          <div class="footer-right-title">이재정 : jaejung0413@naver.com</div>
          <div class="footer-right-logo" onclick="location.href='https://github.com/codekingjj'"></div>
        </div>
        <div class="person">
        </div>
      </div>
    </div>
    <div class="footer-right">
      <div class="footer-person">
        <div class="person">
          <div class="footer-right-title">주승재: 0719improvement@naver.com</div>
          <div class="footer-right-logo" onclick="location.href='https://github.com/IT-improvement'"></div>
        </div>
        <div class="person">
          <div class="footer-right-title">김민규 : minkyu.kim.contact@gmail.com</div>
          <div class="footer-right-logo" onclick="location.href='https://github.com/typoscript'"></div>
        </div>
        <div class="person">
          <div class="footer-right-title">이인선 : dlstjs7617@gmail.com</div>
          <div class="footer-right-logo" onclick="location.href='https://github.com/dlstjs7617'"></div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/script/main/alarm.js"></script>
</html>
