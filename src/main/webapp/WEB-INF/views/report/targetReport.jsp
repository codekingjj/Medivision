<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TargetReport</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/targetReport.css">
    <script src="${pageContext.request.contextPath}/script/report/targetReport.js"></script>
</head>
<body>
<div id="document">
        <div id="report" class="report">
            <h1 id="report-title">${report.typeDecode} 보고서</h1>
        </div>
        <div>
            <h3>진단의 : ${report.writerName}</h3>
        </div>
        <div>
            <h3>진단 시간 : ${report.regDate}</h3>
        </div>

        <div>[Finding]</div>
        <pre><c:out value="${report.finding}" ></c:out></pre>
        <div>[Conclusion]</div>
        <pre><c:out value="${report.conclusion}" ></c:out></pre>
        <div>[Recommend]</div>
        <pre><c:out value="${report.recommend}" ></c:out></pre>
        <div>[comment]</div>
        <pre><c:out value="${report.comment}" ></c:out></pre>
</div>

<div id="close">
    <input id="close-button" type="button" value="닫기" onclick="close()" />
</div>

</body>
</html>
