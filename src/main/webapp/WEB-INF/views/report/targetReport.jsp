<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TargetReport</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/report/targetReport.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/targetReport.css">
</head>
<body>
<div id="content-container">
    <div>
        <h1 id="report-title"> 보고서</h1>
    </div>
    <div>
        <h3>진단의 : </h3>
        <h3 id="writer"></h3>
    </div>
    <div>
        <h3>진단 시간 : </h3>
        <h3 id="reg-date"></h3>
    </div>

    <div>[Finding]</div>
    <textarea id="finding" readonly></textarea>
    <div>[Conclusion]</div>
    <textarea id="conclusion" readonly></textarea>
    <div>[Recommend]</div>
    <textarea id="recommend" readonly></textarea>
    <div>[comment]</div>
    <textarea id="comment" readonly></textarea>
</div>

<div id="close">
    <input id="close-button" type="button" value="닫기" onclick="close()" />
</div>

</body>
</html>
