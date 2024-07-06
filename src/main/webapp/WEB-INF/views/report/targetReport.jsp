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
        <table>
            <thead>
            <tr>
                <th colspan="2" id="report-title" ><c:out value="${report.typeDecode}" /> 보고서</th>
            </tr>
            <tr></tr>
            </thead>
            <tbody>
            <tr>
                <td class="name">진단의</td>
                <td id="writerName"><textarea  readonly><c:out value="${report.writerName}" /></textarea></td>
            </tr>
            <tr>
                <td class="name">진단시간</td>
                <td id="regDate"><textarea readonly><c:out value="${report.regDate}" /></textarea></td>
            </tr>
            <tr>
                <td class="name">Finding</td>
                <td id="finding"><textarea readonly><c:out value="${report.finding}" /></textarea></td>
            </tr>
            <tr>
                <td class="name">Conclusion</td>
                <td id="conclusion"><textarea readonly><c:out value="${report.conclusion}" /></textarea></td>
            </tr>
            <tr>
                <td class="name">Recommend</td>
                <td id="recommend"><textarea readonly><c:out value="${report.recommend}" /></textarea></td>
            </tr>
            <tr>
                <td class="name">Comment</td>
                <td id="comment"><textarea readonly><c:out value="${report.comment}" /></textarea></td>
            </tr>
            </tbody>
        </table>

    </div>
</div>

<div id="close">
    <input id="close-button" type="button" value="닫기" onclick="close()" />
</div>

</body>
</html>