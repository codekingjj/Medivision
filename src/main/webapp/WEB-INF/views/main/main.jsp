<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>검색</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="script/main/main.js"></script>
    <link href="style/header.css" rel="stylesheet">
    <link href="style/main.css" rel="stylesheet">

</head>
<body>
<header>
    <div class="header">
        <div class="header-top">
            <div class="logo">
                <div class="mark"></div>
            </div>
            <div class="logout-box">
                <div class="logout-img">
                </div>
                <div class="logout-text">
                    로그아웃
                </div>
            </div>
        </div>
        <div class="header-bottom">
            <div class="menu diselected"></div>
            <div class="menu selected">차트 검색</div>
            <div class="menu option">담당 환자 설정</div>
            <div class="menu option">로그 기록 확인</div>
            <div class="menu option">마이 페이지</div>
            <div class="menu diselected side-menu">
                <div class="side-menu-icon side-menu-icon-chat"></div>
                <div class="side-menu-icon side-menu-icon-bell"></div>
            </div>
        </div>
    </div>
</header>
<main>
    <div class="container">

        <div class="main">
            <table class="results-section">
                <thead>
                <tr>
                    <th>환자 번호</th>
                    <th>환자 이름</th>
                    <th>검사 장비</th>
                    <th>검사설명</th>
                    <th>검사일시</th>
                    <th>판  독</th>
                    <th>시리즈 갯수</th>
                    <th>이미지 갯수</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</main>
</body>
</html>
