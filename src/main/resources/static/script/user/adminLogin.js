function onsubmitForm(){
    var logform = document.getElementById("login-form");
    var id = logform.userId.value;
    var pwd = logform.userPassword.value;
    if (id.length === 0 || id === "") {
        alert("아이디를 입력하세요");
        logform.id.focus();
    } else if (pwd.length === 0 || pwd === "") {
        alert("비밀번호를 입력하세요");
        logform.pwd.focus();
    } else {
        logform.submit();
    }
}