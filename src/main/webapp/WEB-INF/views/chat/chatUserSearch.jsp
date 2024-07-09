<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>채팅 유저 검색</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/chat/chatUserSearch.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/chat/chatUserSearch/main.js" type="module"></script>
</head>
<body>
    <header class="chatroom-list-header">
        <div class="chatroom-list-header-side-container">
            <div>
                <button id="btnToChatroomList">뒤로가기</button>
            </div>
        </div>
        <div class="chatroom-list-header-side-container middle">
            <input type="text" id="inputUserSearch" />
            <button id="btnUserSearch">검색</button>
        </div>
        <div class="chatroom-list-header-side-container">
            <div>
<%--                <button id="btnChatTabClose">></button>--%>
            </div>
        </div>
    </header>
    <div class="main">
        <div class="user-list-container"></div>
        <button id="btnNextPage">V</button>
    </div>
</body>
</html>
