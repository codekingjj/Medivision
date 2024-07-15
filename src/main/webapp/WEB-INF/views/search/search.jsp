<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>검색</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script type="module" src="${pageContext.request.contextPath}/static/dist/search.bundle.js"></script>
    <script src="${pageContext.request.contextPath}/static/script/patientBookmark/patientBookmarkAdd.js" defer></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/content.css">
    <link href="style/search.css" rel="stylesheet">
    <link href="style/patientBookmark/search.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/main/alarm.css">
    <script src="${pageContext.request.contextPath}/script/main/alarm.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <!-- chat -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/chat/main.css">
    <script src="${pageContext.request.contextPath}/script/chat/websocket/stomp.js"></script>
    <script src="${pageContext.request.contextPath}/script/chat/websocket/sockjs.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/chat/main.js" type="module" defer></script>
</head>
    <body class="body">
    <div class="sidebar-chat-root"></div>
    <div class="container">
        <div class="header">
            <div class="header-top">
                <div class="logo">
                    <div class="mark" onclick="location.href='/main'"></div>
                </div>
                <div class="logout-box">
                    <div class="logout-img">
                    </div>
                    <div class="logout-text" onclick="location.href='/auth/select'">
                        로그아웃
                    </div>
                </div>
            </div>
            <div class="header-menu">
                <div class="menu diselected"></div>
                <div class="menu selected option" onclick="location.href='/search'">차트 검색</div>
                <div class="menu option" onclick="location.href='/patientBookmark'">담당 환자 설정</div>
                <div class="menu dropdown">
                    <div class="dropdown-title">로그 기록 확인</div>
                    <div class="dropdown-content-box">
                        <div class="dropdown-content"><a class="dropdown-content-a" onclick="location.href='/log/login'">로그인 로그 기록</a></div>
                        <div class="dropdown-content"><a class="dropdown-content-a" onclick="location.href='/log/studyKey'">환자 차트 열람 로그 기록</a></div>
                        <div class="dropdown-content"><a class="dropdown-content-a" onclick="location.href='/log/report'">리포트 로그 기록</a></div>
                    </div>
                </div>
                <div class="menu option" onclick="location.href='#'">마이페이지</div>
                <div class="menu diselected-side-menu">
                    <div class="side-menu">
                        <div class="side-menu-icon side-menu-icon-chat" id="btnChatPageOpen"></div>
                        <div class="side-menu-icon side-menu-icon-bell">
                            <div class="nav-btn" id="notification"></div>
                        </div>
                        <div class="alarm-box">
                            <div class="alarm-header">알림</div>
                            <div class="alarm-content">
                                <p>알림 내용1알림 내용1알림 내용1알림 내용1알림</p>
                                <p>알림 내용2</p>
                                <p>알림 내용3</p>
                                <p>알림 내용2</p>
                                <p>알림 내용3</p>
                            </div>
                        </div>
                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <span class="modal-title">알림 내용</span>
                                <span class="close">&times;</span>
                                <p id="modal-text">Modal Content</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
<%--            <button id="0" class="search-button">전체</button>--%>
<%--            <button id="1" class="search-button">1일</button>--%>
<%--            <button id="3" class="search-button">3일</button>--%>
<%--            <button id="7" class="search-button">1주일</button>--%>
                <button id="btnToggleAddPatientBookmarkCheckboxes">담당 환자 추가</button>
                <button id="btnAddCheckedStudiesToPatientBookmark">선택한 환자 추가</button>
        </div>
        <div class="content">
            <div>
                <table class="results-section">
                    <thead>
                    <tr>
                        <th class="table-th-patientBookmark">담당 환자 추가</th>
                        <th>환자 번호</th>
                        <th>환자 이름</th>
                        <th>검사 장비</th>
                        <th>검사설명</th>
                        <th>검사일시</th>
                        <th>판  독</th>
                        <th>시리즈</th>
                        <th>이미지</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <div id="page-scope" class="page-scope">
                    <div id="page-down"></div>
                    <div class="pageCount"></div>
                    <div id="page-up"></div>
                </div>
            </div>
            <div id="thumbnail" class="thumbnail"></div>
        </div>
        <div class="footer">
            <div class="footer-left">
                copyright@Megastudy
            </div>
            <div class="footer-right"></div>
        </div>
    </div>
</body>
</html>
