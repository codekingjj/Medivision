<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>채팅방 목록</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/chat/chatroomList.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/script/chat/chatroomList/main.js" type="module"></script>
<body>
    <header class="chatroom-list-header">
        <div class="chatroom-list-header-side-container">
            <div>+</div>
            <div>++</div>
        </div>
        <div class="chatroom-list-header-side-container">
            <div onclick="location.href = '/chatUserSearch'">&#x1F50E;</div>
            <div>></div>
        </div>
    </header>
    <div class="chatroom-list-container"></div>
</body>
</html>
