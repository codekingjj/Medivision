<!DOCTYPE html>
<html>
<head>
    <title>WebSocket Chat</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>
<%--
<link href="${pageContext.request.contextPath}/style/chat.css" rel="stylesheet" />
--%>
<body>
<div id="messages"></div>
<input type="text" id="messageInput" placeholder="Type a message..." />
<button onclick="sendMessage()">Send</button>
<script src="${pageContext.request.contextPath}/script/chat/chat.js"></script>
</body>
</html>