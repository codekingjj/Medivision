
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Medivision</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body class="body">
<div class="container">
    <div class="header">
        <div class="logo">
            <div class="mark"></div>
        </div>
        <div class="menu">
            <div class="logout-img">
            </div>
            <div class="logout-text">
                로그아웃
            </div>
        </div>
    </div>
    <div class="content">
        <table class="user-table">
            <thead>
            <th>선택</th>
            <th>이름</th>
            <th>핸드폰 번호</th>
            <th>성별</th>
            <th>면허번호(있을시)</th>
            <th>회원가입 여부</th>
            </thead>
            <tbody>
            <tr>
                <td>
                    <input type="radio" name="checkuser">
                </td>
                <td>주승재</td>
                <td>010-0000-0000</td>
                <td>남</td>
                <td>-</td>
                <td><img src="check-round-fill.png" alt="Profile Image" class="ok-img"></td></td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="checkuser">
                </td>
                <td>주승재</td>
                <td>010-0000-0000</td>
                <td>남</td>
                <td>110-111111-1111</td>
                <td>-</td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="checkuser">
                </td>
                <td>주승재</td>
                <td>010-0000-0000</td>
                <td>남</td>
                <td>110-111111-1111</td>
                <td>-</td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="checkuser">
                </td>
                <td>주승재</td>
                <td>010-0000-0000</td>
                <td>남</td>
                <td>-</td>
                <td>
                    <img src="check-round-fill.png" alt="Profile Image" class="ok-img">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="checkuser">
                </td>
                <td>주승재</td>
                <td>010-0000-0000</td>
                <td>남</td>
                <td>-</td>
                <td>-</td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="checkuser">
                </td>
                <td>주승재</td>
                <td>010-0000-0000</td>
                <td>남</td>
                <td>110-111111-1111</td>
                <td>-</td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="checkuser">
                </td>
                <td>주승재</td>
                <td>010-0000-0000</td>
                <td>남</td>
                <td>-</td>
                <td>
                    <img src="check-round-fill.png" alt="Profile Image" class="ok-img">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="checkuser">
                </td>
                <td>주승재</td>
                <td>010-0000-0000</td>
                <td>남</td>
                <td>-</td>
                <td>
                    <img src="check-round-fill.png" alt="Profile Image" class="ok-img">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="page-button-box">
            <div class="page-button-left">
                < 이전
            </div>
            <div class="page-button-right">
                다음 >
            </div>
        </div>
        <input type="button" class="join-button" value="계정 만들기">
    </div>
</div>
</body>
</html>
