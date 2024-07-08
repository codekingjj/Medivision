<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/style/viewer.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <title>뷰어</title>

</head>
<body>
    <header>
        <nav>
            <ul>
                <li></li>
                <li><a href="#">차트 검색</a></li>
                <li><a href="#">담장 환자 설정</a></li>
                <li><a href="#">로그 기록 확인</a></li>
                <li></li>
            </ul>
        </nav>
    </header>

    <aside>
        <h3>medivision</h3>
    </aside>



<div id="dicomImage"></div>
<input id="file" type="file" accept="application/dicom">

<%--    <button id="invertButton">Invert Colors</button>--%>

    <c:forEach var="series" items="${seriesArray}">
<%--            <tr>--%>
<%--                <td>studyKey : ${series.studyKey}</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>seriesKey : ${series.seriesKey}</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>path : ${series.path}</td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>fname : ${series.FName}</td>--%>
<%--            </tr>--%>
    <c:forEach var="images" items="${series.imageList}">
        <tr>
            <td>studyKey : ${images.studyKey}</td>
        </tr>
        <tr>
            <td>seriesKey : ${images.seriesKey}</td>
        </tr>
        <tr>
            <td>imageKey : ${images.imageKey}</td>
        </tr>
        <tr>
            <td>path : ${images.path}</td>
        </tr>
        <tr>
            <td>fname : ${images.fname}</td>
        </tr>
        <tr>
            <td style="color : red">totalPath : ${images.totalPath}</td>
        </tr>
    </c:forEach>
    </c:forEach>
</body>
<script type="module" src="${pageContext.request.contextPath}/static/dist/viewer.bundle.js"></script>
</html>