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
        <img src="path_to_logo_image" alt="Medivision Logo">
    </div>
    <nav>
        <ul>
            <li><a href="#">차트 검색</a></li>
            <li><a href="#">담장 환자 설정</a></li>
            <li><a href="#">로그 기록 확인</a></li>
        </ul>
    </nav>
    <div class="logout">
        <img src="path_to_logout_icon" alt="Logout Icon">
    </div>
</header>
<main>
    <section class="search-section">
        <h2>검색</h2>
        <p>0명의 환자를 찾았습니다</p>
        <form id="search-form">
            <label for="patient-id">환자 아이디</label>
            <input type="text" id="patient-id" name="patient-id">

            <label for="patient-name">환자이름</label>
            <input type="text" id="patient-name" name="patient-name">

            <label for="status">편측상태</label>
            <select id="status" name="status">
                <option value="preparation">예비판독</option>
                <!-- Add other options here -->
            </select>

            <label for="equipment">장비</label>
            <select id="equipment" name="equipment">
                <option value="CT">CT</option>
                <!-- Add other options here -->
            </select>

            <label for="date-range">날짜</label>
            <input type="date" id="start-date" name="start-date" value="2000-01-01">
            <input type="date" id="end-date" name="end-date" value="2000-01-01">

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
            <img src="path_to_thumbnail_image" alt="Thumbnail Image 1">
            <img src="path_to_thumbnail_image" alt="Thumbnail Image 2">
        </div>
    </section>
</main>
</body>
</html>
