<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script type="module" src="${pageContext.request.contextPath}/static/dist/search.bundle.js"></script>
    <link href="style/content.css" rel="stylesheet">
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
                <div class="menu option">차트 검색</div>
                <div class="menu option">담당 환자 설정</div>
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
                        <div class="side-menu-icon side-menu-icon-chat"></div>
                        <div class="side-menu-icon side-menu-icon-bell"></div>
                    </div>
                </div>
            </div>
        </div>
    </header>
</body>
</html>
