<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>담당 환자 목록</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/patientBookmark/patientBookmark.js"></script>
    <link href="style/content.css" rel="stylesheet">
    <link href="style/search.css" rel="stylesheet">
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
        <div class="header-menu">
            <div class="menu diselected"></div>
            <div class="menu option" onclick="location.href='/search'">차트 검색</div>
            <div class="menu option" onclick="location.hef='/patientBookmark'">담당 환자 설정</div>
            <div class="menu selected dropdown">
                <div class="dropdown-title">로그 기록 확인</div>
                <div class="dropdown-content-box">
                    <div class="dropdown-content"><a class="dropdown-content-a" onclick="location.href='/log/login'">로그인 로그 기록</a></div>
                    <div class="dropdown-content"><a class="dropdown-content-a" href="#">환자 차트 열람 로그 기록</a></div>
                    <div class="dropdown-content"><a class="dropdown-content-a" href="#">리포트 로그 기록</a></div>
                </div>
            </div>
            <div class="menu option">마이 페이지</div>
            <div class="menu diselected-side-menu">
                <div class="side-menu">
                    <div class="side-menu-icon side-menu-icon-chat" id="btnChatPageOpen"></div>
                    <div class="side-menu-icon side-menu-icon-bell"></div>
                </div>
            </div>
        </div>
    </div>
</header>
<main>
    <div class="container">
        <div class="sidebar">
            <div id="search-count">0명의 환자를 찾았습니다</div>
            <form id="search-form">
                <label for="pid">환자 아이디</label>
                <input type="text" id="pid" name="pid">

                <label for="pname">환자이름</label>
                <input type="text" id="pname" name="pname">

                <input type="submit" value="검색">
                <button type="button" id="btnDeleteCheckedPatients">선택한 담당 환자 삭제</button>
            </form>
        </div>
        <div class="main">
            <table class="results-section">
                <thead>
                    <tr>
                        <th>담당 환자 관리</th>
                        <th>환자 번호</th>
                        <th>환자 이름</th>
                    </tr>
                </thead>
                <tbody class="results-section tbody"></tbody>
            </table>
        </div>
    </div>
</main>
</body>
</html>
