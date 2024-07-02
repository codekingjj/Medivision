<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="static/style/viewer.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <title>뷰어</title>

</head>
<body>
    <header>
        <nav>
            <ul>
                <li></li>
                <li><a href="#">차트 검색</a></li>
                <li><a href="#">담장 환자 설정</a></li>
                <li><a href="#">로그 기록 확인</a></li>
                <li></li>
            </ul>
        </nav>
    </header>

    <aside>
        <h3>medivision</h3>
    </aside>



<div id="content"></div>
<input id="file" type="file" accept="application/dicom">

    <button id="invertButton">Invert Colors</button>
</body>
<script type="module" src="static/dist/bundle.js"></script>
</html>