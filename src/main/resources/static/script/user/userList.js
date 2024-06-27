function onsubmitForm(){
    const form = document.getElementById("userList");
    var userCode = document.querySelector('input[name="userCode"]:checked').value;
    console.log("userCode: "+userCode);
    if(userCode.length === 0 || userCode === ""){
        alert('사람을 선택해주세요.');
    }else {
        form.submit();
    }
}