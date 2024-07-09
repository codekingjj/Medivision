<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TargetReport</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/targetReport.css">
    <script src="${pageContext.request.contextPath}/script/report/updateReport.js"></script>
</head>
<body>
<div id="document">
    <div id="report" class="report">
        <form>
            <table>
                <thead>
                <tr>
                    <th colspan="2" id="report-title" ><c:out value="${report.typeDecode}" /> 보고서 수정</th>
                </tr>
                <tr></tr>
                </thead>
                <tbody>
                <tr>
                    <td class="name">진단의</td>
                    <td id="writerName"><textarea  readonly><c:out value="${report.writerName}" /></textarea></td>
                </tr>
                <tr>
                    <td class="name">판독 유형</td>
                    <td>
                        <select id="typeDecode" name="typeDecode">
                            <option value="예비판독" ${report.typeDecode eq '예비판독' ? selected : ''}>예비판독</option>
                            <optoin value="판독" ${report.typeDecode eq '판독' ? selected : ''}>판독</optoin>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="name">진단시간</td>
                    <td id="regDate"><textarea readonly><c:out value="${report.regDate}" /></textarea></td>
                </tr>
                <tr>
                    <td class="name">Finding</td>
                    <td id="finding"><textarea ><c:out value="${report.finding}" /></textarea></td>
                </tr>
                <tr>
                    <td class="name">Conclusion</td>
                    <td id="conclusion"><textarea ><c:out value="${report.conclusion}" /></textarea></td>
                </tr>
                <tr>
                    <td class="name">Recommend</td>
                    <td id="recommend"><textarea ><c:out value="${report.recommend}" /></textarea></td>
                </tr>
                <tr>
                    <td class="name">Comment</td>
                    <td id="comment"><textarea ><c:out value="${report.comment}" /></textarea></td>
                </tr>
                </tbody>
            </table>

            <div id="close">
                <input id="submit" type="button" value="수정" />
                <input id="cancle" type="button" value="취소" onclick="close()" />
            </div>

        </form>
    </div>
</div>
</body>
</html>