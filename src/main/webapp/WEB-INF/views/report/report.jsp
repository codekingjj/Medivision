<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Medivision</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/report.css">
    <script>
        let pop;
        window.onunload = function() { pop.close(); }
        function popup() {
            var url = `/report/targetReport`;
            var name = "popup test";
            var option = "width=800, height=500, left=100, top=50, location=no"
            pop = window.open(url, name, option);
        }
        function close(){
            pop.close();
        }

        function autoResize(textarea) {
            textarea.style.height = 'auto';
            textarea.style.height = textarea.scrollHeight + 'px';
        }
    </script>
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
                <tr id="first">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr id="second">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr id="third">
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
                <textarea id="finding" onkeyup="autoResize(this)" onkeydown="autoResize(this)" rows="2" style="overflow:hidden;"></textarea>
                <a>[Conclusion]</a>
                <textarea id="conclusion" onkeyup="autoResize(this)" onkeydown="autoResize(this)" rows="2" style="overflow:hidden;"></textarea>
                <a>[Recommend]</a>
                <textarea id="recommend" onkeyup="autoResize(this)" onkeydown="autoResize(this)" rows="2" style="overflow:hidden;"></textarea>
                <a>[comment]</a>
                <textarea id="comment" onkeyup="autoResize(this)" onkeydown="autoResize(this)" rows="2" style="overflow:hidden;"></textarea>
            </div>
            <input type="submit" id="spare-report" value="예비판독">
            <input type="submit" id="report" value="판독">
        </form>
    </div>
    <div id="close-button">
        <input type="button" value="닫기" />
    </div>

<%--    <input type="button" value="팝업" onclick="popup(1);" />--%>
</div>
<script src="${pageContext.request.contextPath}/script/report/report.js" />
</body>
</html>
