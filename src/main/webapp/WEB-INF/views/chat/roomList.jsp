<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Room List</h1>
    <c:forEach var="room" items="${rooms}">
        <div>
            <p>${room.roomId}</p>
            <p>${room.name}</p>
        </div>
    </c:forEach>
</body>
</html>
