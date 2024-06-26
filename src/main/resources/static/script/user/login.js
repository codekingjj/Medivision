function onsubmitForm(){
    var id = document.getElementById("userId").value;
    var pwd = document.getElementById("userPassword").value;
    if (id.length === 0 || id === "") {
        alert("아이디를 입력하세요");
        logform.userId.focus();
    } else if (pwd.length === 0 || pwd === "") {
        alert("비밀번호를 입력하세요");
        logform.userPassword.focus();
    } else {
        fetch("/auth/sign-in", {
            method: 'post',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                userId: id,
                userPassword: pwd
            })
        }).then(res => res.json())
            .then(response  => {
                localStorage.setItem("jwt", response.token);
                if(response.code === "SU")
                    window.location.href = "/admin";
                else
                    alert("아이디 비밀번호를 확인해주세요");
            });
    }
}
function onClickPasswordTypeHandler(){
    const passwordInput = document.querySelector('.login-write-button');
    const imageBox = document.querySelector('.password-type-image-box');

    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        imageBox.classList.add('show');
    } else {
        passwordInput.type = 'password';
        imageBox.classList.remove('show');
    }
}
