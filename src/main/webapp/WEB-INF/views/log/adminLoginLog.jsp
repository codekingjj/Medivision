<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Medivision</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/userList.css">
    <script src="${pageContext.request.contextPath}/script/user/userList.js"></script>
</head>
<body class="body">
<div class="sidebar">
    <div class="header">
        <div class="logo">
            <div class="mark"></div>
        </div>
    </div>
    <ul class="sidebar-menu">
        <li><a href="/admin">user list</a></li>
        <li><a href="/admin/log/login">login log</a></li>
        <li><a href="/admin/log/study">study log</a></li>
        <li><a href="/admin/log/report">report log</a></li>
        <li><a href="/auth/select">Logout</a></li>
    </ul>
</div>
<div class="container">
            <form action="/auth/sign-up" method="post" id="userList">
    <div class="content">
        <table class="user-table">
            <thead>
            <th>Index</th>
            <th>User ID</th>
            <th>Client IP</th>
            <th>Login Date</th>
            </thead>
            <tbody>
            <c:forEach items="${loginLists}" var="loginList">
                <tr>
                    <td><c:out value="${loginList.logIndex}" /></td>
                    <td><c:out value="${loginList.userId}" /></td>
                    <td><c:out value="${loginList.clientIp}" /></td>
                    <td><c:out value="${loginList.loginDate}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="page-button-box">
        ${ pagingImg }
        </div>
    </div>
            </form>
</div>
</body>
</html>
