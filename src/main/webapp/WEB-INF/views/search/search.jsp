<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>검색</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="script/search/search.js"></script>
    <link href="style/header.css" rel="stylesheet">
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
        <div class="sidebar">
            <div id="search-count">0명의 환자를 찾았습니다</div>
            <form id="search-form">
                <label for="pid">환자 아이디</label>
                <input type="text" id="pid" name="pid">

                <label for="pname">환자이름</label>
                <input type="text" id="pname" name="pname">

                <label for="reportstatus">판독상태</label>
                <select id="reportstatus" name="reportstatus">
                    <option value="-1">선택</option>
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

                <div class="date-range">
                    <input type="date" id="startDate" name="startDate" value="1990-01-01">
                    <span>~</span>
                    <input type="date" id="endDate" name="endDate" >
                </div>
                <button type="submit">검색</button>
            </form>
        </div>
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
        <div class="thumbnail">
            썸네일
        </div>
    </div>
</main>
</body>
</html>
