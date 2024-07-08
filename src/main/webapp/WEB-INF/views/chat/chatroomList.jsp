<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>채팅방 목록</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/chat/chatroomList.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js" integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js" integrity="sha512-1QvjE7BtotQjkq8PxLeF6P46gEpBRXuskzIVgjFpekzFVF4yjRgrQvTG1MTOJ3yQgvTteKAcO7DSZI92+u/yZw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="${pageContext.request.contextPath}/script/chat/chatroomList/main.js" type="module"></script>
<body>
    <header class="chatroom-list-header">
        <div class="chatroom-list-header-side-container">
            <div onclick="location.href = '/chat/userSearch'">+</div>
        </div>
        <div class="chatroom-list-header-side-container">
            <div>></div>
        </div>
    </header>
    <div class="chatroom-list-container"></div>
</body>
</html>
