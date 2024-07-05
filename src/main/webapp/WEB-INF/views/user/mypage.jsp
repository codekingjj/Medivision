<%--
  Created by IntelliJ IDEA.
  User: jujae
  Date: 2024-07-05
  Time: 오후 4:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/main/alarm.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/content.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/main/mypage.css">
</head>
<body>
<div class="container">
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
            <div class="menu diselected-pc"></div>
            <div class="menu option">차트 검색</div>
            <div class="menu option">담당 환자 설정</div>
            <div class="menu selected dropdown">
                <div class="dropdown-title">로그 기록 확인</div>
                <div class="dropdown-content-box">
                    <div class="dropdown-content"><a class="dropdown-content-a" href="#">로그인 로그 기록</a></div>
                    <div class="dropdown-content"><a class="dropdown-content-a" href="#">환자 차트 열람 로그 기록</a></div>
                    <div class="dropdown-content"><a class="dropdown-content-a" href="#">리포트 로그 기록</a></div>
                </div>
            </div>
            <div class="menu option">마이 페이지</div>
            <div class="menu diselected-side-menu">
                <div class="side-menu">
                    <div class="side-menu-icon side-menu-icon-chat"></div>
                    <div class="side-menu-icon side-menu-icon-bell">
                        <div class="nav-btn" id="notification"><span class="note-num" id="#notification">99+</span></div>
                    </div>
                    <div class="alarm-box">
                        <div class="alarm-header">알림</div>
                        <div class="alarm-content">
                            <p class="check">알림 내용1알림 내용1알림 내용1알림 내용1알림<br>dfsdf</p>
                            <p class="no-check">알림 내용2<br>sdfsdf</p>
                            <p class="check">알림 내용1알림 내용1알림 내용1알림 내용1알림<br>dfsdf</p>
                            <p class="no-check">알림 내용2<br>sdfsdf</p>
                            <p class="check">알림 내용1알림 내용1알림 내용1알림 내용1알림<br>dfsdf</p>
                            <p class="check">알림 내용1알림 내용1알림 내용1알림 내용1알림<br>dfsdf</p>
                            <p class="no-check">알림 내용2<br>sdfsdf</p>
                            <p class="no-check">알림 내용2<br>sdfsdf</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="aside">
        <div class="aside-content">담당 환자 보기</div>
        <div class="aside-content">내가 쓴 리포트 보기</div>
        <div class="aside-content aside-selected">내 정보</div>
    </div>
    <div class="content">
        <div class="content-top">
            <div class="content-top-title">내 정보</div>
        </div>
        <div class="content-bottom">
            <table id="mypage-table">
                <tr>
                    <td rowspan="3">사진</td>
                    <td class="mypage-type">이 름</td>
                    <td class="mypage-content"></td>
                    <td class="mypage-type">영 문</td>
                    <td class="mypage-content"></td>
                </tr>
                <tr>
                    <td class="mypage-type">e-mail</td>
                    <td class="mypage-content" colspan="3"></td>
                </tr>
                <tr>
                    <td class="mypage-type">생 년 월 일</td>
                    <td class="mypage-content"></td>
                    <td class="mypage-type">핸 드 폰 번 호</td>
                    <td class="mypage-content"></td>
                </tr>
                <tr>
                    <td>
                        <label for="file">
                            <div class="btn-upload">사진 업로드</div>
                        </label>
                        <input type="file" name="file" id="file">
                    </td>
                    <td class="mypage-type">주 소</td>
                    <td class="mypage-content" colspan="3"></td>
                </tr>
                <tr>
                    <td class="blink"></td>
                </tr>
                <tr>
                    <td class="mypage-type" colspan="2">아 이 디</td>
                    <td class="mypage-content" colspan="2">아 이 디</td>
                </tr>
                <tr>
                    <td class="mypage-type" colspan="2">현재 비 밀 번 호</td>
                    <td class="mypage-content" colspan="2">****************</td>
                </tr>
                <tr>
                    <td class="mypage-type" colspan="2">새 비 밀 번 호</td>
                    <td class="mypage-content" colspan="2">****************</td>
                </tr>
                <tr>
                    <td class="mypage-type" colspan="2">새 비 밀 번 호 확 인</td>
                    <td class="mypage-content" colspan="2">****************</td>
                </tr>
                <tr>
                    <td class="blink"></td>
                </tr>
                <tr>
                    <td class="mypage-type">면허 번호</td>
                    <td class="mypage-content" colspan="2">면허 번호</td>
                </tr>
                <tr>
                    <td class="blink"></td>
                </tr>
                <tr>
                    <td class="mypage-type">전문의 번호</td>
                    <td class="mypage-content" colspan="2">전문의 번호</td>
                </tr>
                <tr>
                    <td class="mypage-type">전공 과</td>
                    <td class="mypage-content" colspan="2">신경외과(CS)</td>
                </tr>
            </table>
        </div>
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
