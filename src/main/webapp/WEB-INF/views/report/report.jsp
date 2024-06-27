<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Medivision</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/report.css">
    <script src="${pageContext.request.contextPath}/script/report/report.js"></script>
</head>
<body>
<div id="container">
    <input type="hidden" id="study-key">
    <div id="report-container">
        <h1>Report List</h1>
        <table>
            <thead>
                <tr>
                    <th id="th-1">판독 유형</th>
                    <th id="th-2">판독의</th>
                    <th id="th-3">코멘트</th>
                    <th id="th-4">판독 시간</th>
                </tr>
            </thead>
            <tbody id="report-list">
                <tr id="tr1">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr id="tr2">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr id="tr3">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </div>

    <div id="report-form">
        <h1>Write</h1>
        <form method="POST" action="/report">
            <div id="content-container">
                <a>[Finding]</a>
                <textarea id="finding"  rows="2" style="overflow:hidden;"></textarea>
                <a>[Conclusion]</a>
                <textarea id="conclusion"  rows="2" style="overflow:hidden;"></textarea>
                <a>[Recommend]</a>
                <textarea id="recommend"  rows="2" style="overflow:hidden;"></textarea>
                <a>[comment]</a>
                <textarea id="comment"  rows="2" style="overflow:hidden;"></textarea>
                <textarea id="empty" rows="1" readonly></textarea>
            </div>
            <input type="submit" id="spare-report" value="예비판독">
            <input type="submit" id="report" value="판독">
        </form>
    </div>
    <div id="close">
        <input id="close-button" type="button" value="닫기" onclick="close()" />
    </div>

<%--    <input type="button" value="팝업" onclick="popup(1);" />--%>
</div>

</body>
</html>
