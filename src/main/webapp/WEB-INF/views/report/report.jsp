<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Medivision</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/report.css">
    <script>
        var pop;
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
    </script>
</head>
<body>
<div id="container">
    <div id="report-container">
        <h1>Report List</h1>
        <table>
            <thead>
                <tr>
                    <th>판독 유형</th>
                    <th>판독의</th>
                    <th>코멘트</th>
                    <th>판독 시간</th>
                </tr>
            </thead>
            <tbody id="report-list">
            </tbody>
        </table>
    </div>

    <div id="report-form">
        <h1>Write</h1>
        <form method="POST" action="/report">
            <div id="content-container">
                <div>[Finding]</div>
                <textarea id="finding"></textarea>
                <div>[Conclusion]</div>
                <textarea id="conclusion"></textarea>
                <div>[Recommend]</div>
                <textarea id="recommend"></textarea>
                <div>[comment]</div>
                <textarea id="comment"></textarea>
            </div>
            <input type="button" value="예비판독">
            <input type="button" value="판독">
            <input type="button" value="닫기" />
            <input type="button" value="팝업" onclick="popup(1);" />
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/script/report/report.js" />
</body>
</html>
