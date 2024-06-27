<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>검색</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="script/search.js"></script>
    <link href="style/search.css" rel="stylesheet">
</head>
<body>
<header>
    <div>${studies.size()}</div>
    <div id="studyContainer">
        <table>
            <tbody>
                <tr>
                    <td>추가?</td>
                    <td>이름</td>
                    <td>검사장비</td>
                    <td>검사설명</td>
                    <td>검사일시</td>
                </tr>
                <c:forEach var="study" items="${studies}">
                    <tr>
                        <td><input type="checkbox" class="study-checkbox" id="${study.pid}" /></td>
                        <td>${study.pname}</td>
                        <td>${study.modality}</td>
                        <td>${study.studydesc}</td>
                        <td>${study.studydate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</header>
<main>
    <section class="search-section">
        <button onclick="addCheckedStudiesToPatientBookmark()">담당 환자 추가</button>
        <button onclick="deleteCheckedStudiesToPatientBookmark()">담당 환자 삭제</button>
    </section>
    <section class="results-section">
        <table>
            <thead>
            <tr>
                <th>환자 이름</th>
                <th>검사 장비</th>
                <th>검사설명</th>
                <th>검사의시</th>
            </tr>
            </thead>
            <tbody>
            <!-- Data rows will be inserted here -->
            </tbody>
        </table>
    </section>
    <section class="thumbnail-section">
        <h3>썸네일</h3>
        <div class="thumbnails">
<%--            <img src="path_to_thumbnail_image" alt="Thumbnail Image 1">--%>
<%--            <img src="path_to_thumbnail_image" alt="Thumbnail Image 2">--%>
        </div>
    </section>
</main>
</body>
<script src="${pageContext.request.contextPath}/script/patientBookmark/patientBookmark.js"></script>
</html>
