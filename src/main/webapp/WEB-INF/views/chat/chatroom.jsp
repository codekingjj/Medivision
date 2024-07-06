<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>chatroom</title>
</head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/chat/chat.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js" integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js" integrity="sha512-1QvjE7BtotQjkq8PxLeF6P46gEpBRXuskzIVgjFpekzFVF4yjRgrQvTG1MTOJ3yQgvTteKAcO7DSZI92+u/yZw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="${pageContext.request.contextPath}/script/chat/chatroom/main.js"></script>
<body>
    <div class="root">
        <div class="chat-header">
            <div>
                <button class="btn-chatroom-exit" onclick="exitChatroom()">나가기</button>
            </div>
        </div>
        <div class="chat-content">
            <div class="chat-list-container" id="chatListContainer"></div>
            <div class="chat-input-container">
                <input type="text" id="inputMessage" />
                <button class="btn-message-send" onclick="sendMessage()">></button>
            </div>
        </div>
    </div>
    <input type="hidden" id="roomId" value="${roomId}" />
</body>
</html>
