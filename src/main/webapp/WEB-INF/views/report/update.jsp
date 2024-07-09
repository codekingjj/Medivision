<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TargetReport</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/updateReport.css">
    <script src="${pageContext.request.contextPath}/script/report/updateReport.js"></script>
</head>
<body>
<div id="document">
    <input type="hidden" id="studyKey" value="${report.studyKey}">
    <input type="hidden" id="reportIndex" value=${report.reportIndex}>
    <div id="report" class="report">
        <form method="Post" action=`/report/update/${report.reportIndex}` >
            <table>
                <thead>
                <tr>
                    <th colspan="2" id="report-title" >${report.typeDecode} 보고서 수정</th>
                </tr>
                <tr></tr>
                </thead>
                <tbody>
                <tr>
                    <td class="name">진단의</td>
                    <td id="writerName"><textarea  readonly>${report.writerName}</textarea></td>
                </tr>
                <tr>
                    <td class="name">판독 유형</td>
                    <td id="decode">
                        <select name="typeDecode" id="typeDecode" >
                            <option value="예비판독" ${'예비판독' eq report.typeDecode ? 'selected' : ''}>예비판독</option>
                            <option value="판독" ${'판독' eq report.typeDecode ? 'selected' : ''}>판독</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="name">진단시간</td>
                    <td class="regDate" ><textarea id="regDate">${report.regDate}</textarea></td>
                </tr>
                <tr>
                    <td class="name">Finding</td>
                    <td class="finding"><textarea id="finding">${report.finding}</textarea></td>
                </tr>
                <tr>
                    <td class="name">Conclusion</td>
                    <td class="conclusion">
                        <textarea id="conclusion">${report.conclusion}</textarea>
                    </td>
                </tr>
                <tr>
                    <td class="name">Recommend</td>
                    <td class="recommend"><textarea id="recommend">${report.recommend}</textarea></td>
                </tr>
                <tr>
                    <td class="name">Comment</td>
                    <td class="comment"><textarea id="comment">${report.comment}</textarea></td>
                </tr>
                </tbody>
            </table>

            <div id="close">
                <input id="submit" type="submit" value="수정" />
                <input id="cancle" type="button" value="취소" onclick="close()" />
            </div>

        </form>
    </div>
</div>
</body>
</html>