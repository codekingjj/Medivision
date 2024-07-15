<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/style/content.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/main/alarm.css">
    <script src="${pageContext.request.contextPath}/script/main/alarm.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/style/viewer.css">
    <script src="${pageContext.request.contextPath}/script/main/alarm.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/main/alarm.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <title>뷰어</title>
    <!-- Chat CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/chat/main.css">
</head>
<body>
<div class="sidebar-chat-root"></div>
<input type="hidden" id="studyKey" value="${studyKey}">

<main>
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
                <div class="aside">
                    <div>
                        <button id="report" class="tool_button"></button>
                        <div>리포트</div>
                    </div>

                    <div>
                        <div class="imageAndName">
                            <button id="previous" class="tool_button"></button>
                            <div>이전</div>
                        </div>
                        <div class="imageAndName">
                            <button id="next" class="tool_button"></button>
                            <div>다음</div>
                        </div>
                    </div>
                    <div>
                        <div class="imageAndName">
                            <button id="workList" class="tool_button"></button>
                            <div>워크리스트</div>
                        </div>
                        <div class="imageAndName">
                            <button id="defaultTool" class="tool_button"></button>
                            <div>기본툴</div>
                        </div>
                    </div>
                    <div>
                        <div class="imageAndName">
                            <button id="windowLevel" class="tool_button"></button>
                            <div>윈도우 레벨</div>
                        </div>
                    </div>
                    <div>
                        <div class="imageAndName">
                            <div class="annotation-select">
                                <button id="annotationBtn" class="tool_button"></button>
                                <div>주석</div>
                                <div class="dropdown-annotation">
                                    <div class="annotation-content">
                                        <button class="annotationBtn1" id="activateAngle">각도</button>
                                        <button class="annotationBtn2" id="activateArrowAnnotate">화살표</button>
                                    </div>
                                    <div>
                                        <div class="annotation-content">
                                            <button id="activateProbe">Probe</button>
                                            <button id="activateLength">길이</button>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="annotation-content">
                                            <button id="activateRectangleRIO">사각형 그리기</button>
                                            <button id="activateEllipticalROI">원 그리기</button>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="annotation-content">
                                            <button id="activateFreeHand">자율 그리기</button>
                                            <button id="activateBidirectional">Bidirectional</button>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="annotation-content">
                                            <button class="annotationBtn3" id="activateCobbAngle">콥 각도</button>
                                            <button class="annotationBtn4" id="activateEraser">선택 삭제</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="imageAndName">
                            <div class="tools-select">
                                <button id="toolsBtn" class="tool_button"></button>
                                <div>도구</div>
                                <div class="dropdown-tools">
                                    <div class="tools-content">
                                        <button class="toolsBtn1" id="activateZoom">확대축소</button>
                                    </div>
                                    <div class="tools-content">
                                        <button class="toolsBtn2" id="activateRotate">회전</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            <div class="content-image">

            </div>


        <div class="footer">
            <div class="footer-left">
                copyright@Megastudy
            </div>
            <div class="footer-right"></div>
        </div>
    </div>

</main>


<script type="module" src="${pageContext.request.contextPath}/static/dist/viewer.bundle.js"></script>
<script type="module" src="${pageContext.request.contextPath}/static/dist/tools.bundle.js"></script>
<script type="module" src="${pageContext.request.contextPath}/static/dist/previous.bundle.js"></script>
<script type="module" src="${pageContext.request.contextPath}/static/dist/next.bundle.js"></script>
</body>
    <script src="${pageContext.request.contextPath}/script/chat/websocket/stomp.js"></script>
    <script src="${pageContext.request.contextPath}/script/chat/websocket/sockjs.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/chat/main.js" type="module" defer></script>
    <script src="${pageContext.request.contextPath}/script/viewer/reportPopup.js"></script>
</html>