<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>검색</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="script/search.js"></script>
    <link href="style/search.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="logo">
<%--        <img src="" alt="Medivision Logo">--%>
    </div>
    <nav>
        <ul>
            <li><a href="#">차트 검색</a></li>
            <li><a href="#">담장 환자 설정</a></li>
            <li><a href="#">로그 기록 확인</a></li>
        </ul>
    </nav>
    <div class="logout">
<%--        <img src="" alt="Logout Icon">--%>
    </div>
</header>
<main>
    <section class="search-section">
        <h2>검색</h2>
        <div id="search-count">
        </div>
        <form id="search-form">
            <label for="pid">환자 아이디</label>
            <input type="text" id="pid" name="pid">

            <label for="pname">환자이름</label>
            <input type="text" id="pname" name="pname">

            <label for="reportstatus">판독상태</label>
            <select id="reportstatus" name="reportstatus">
                <option value="999">선택</option>
                <option value="3">읽지않음</option>
                <option value="5">예비판독</option>
                <option value="6">판독</option>
                <!-- Add other options here -->
            </select>

            <label for="modality">장비</label>
            <select id="modality" name="modality">
                <option value="">선택</option>
                <option value="AS">AS</option>
                <option value="AU">AU</option>
                <option value="BI">BI</option>
                <option value="CD">CD</option>
                <option value="CF">CF</option>
                <option value="CP">CP</option>
                <option value="CR">CR</option>
                <option value="CS">CS</option>
                <option value="CT">CT</option>
                <option value="DD">DD</option>
                <option value="DF">DF</option>
                <option value="DG">DG</option>
                <option value="DM">DM</option>
                <option value="DR">DR</option>
                <option value="DS">DS</option>
                <option value="DX">DX</option>
                <option value="EC">EC</option>
                <option value="ES">ES</option>
                <option value="FA">FA</option>
                <option value="FS">FS</option>
                <option value="LS">LS</option>
                <option value="LP">LP</option>
                <option value="MA">MA</option>
                <option value="MR">MR</option>
                <option value="MS">MS</option>
                <option value="NM">NM</option>
                <option value="OT">OT</option>
                <option value="PT">PT</option>
                <option value="RF">RF</option>
                <option value="RG">RG</option>
                <option value="ST">ST</option>
                <option value="TG">TG</option>
                <option value="US">US</option>
                <option value="VF">VF</option>
            </select>

            <label >날짜</label>
<%--            <input type="date" id="start-date" name="start-date" value="2000-01-01">--%>
<%--            <input type="date" id="end-date" name="end-date" value="2000-01-01">--%>

            <button type="submit">검색</button>
        </form>
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
</html>
