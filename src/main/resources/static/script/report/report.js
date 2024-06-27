$(document).ready(function() {
    //부모창 뷰어페이지가 지닌 스터디키 가져오기
    // const studyKey = window.opener.studyKey;
    const studyKey = 1
    let decodeType ="";

    var reportIndex = [];

    $.ajax({
        "url" : `/reports/${studyKey}`,
        "method" : 'GET',
        "headers": {
            "Content-Type": "application/json",
        }
    }).then(res => {
        const data = res.result;
        console.log(res);
        console.log(data);
        let num = 1;

        data.forEach(function(report){
            console.log(report);

            reportIndex.push(report.reportIndex);
            const tr = `tr${num}`;

            const trE = document.querySelector(`#${tr}`);
            console.log(trE);
            trE.replaceChildren();

            const td1 = document.createElement('td');
            td1.className = tr;
            td1.innerText = report.typeDecode;

            const td2 = document.createElement('td');
            td2.className = tr;
            td2.innerText = report.writerName;

            const td3 = document.createElement('td');
            td3.className = tr;
            td3.innerText = report.comment;

            const td4 = document.createElement('td');
            td4.className = tr;
            const date = timestamp(report.regDate);
            td4.innerText = date;

            trE.append(td1);
            trE.append(td2);
            trE.append(td3);
            trE.append(td4);

            num ++;
        });
    });


    function timestamp(dates){
        const regDate = new Date(dates);

        const year = regDate.getFullYear();
        const month = (regDate.getMonth()+1).length == 2 ? (regDate.getMonth()+1) : "0"+(regDate.getMonth()+1);
        const date = regDate.getDate().length > 3 ? ""+regDate.getDate() : "0"+ regDate.getDate();
        const hour = regDate.getHours().length > 3 ? ""+regDate.getHours() : "0"+ regDate.getHours();
        const min = regDate.getMinutes().length == 2 ? ""+regDate.getMinutes() : "0"+ regDate.getMinutes();
        const sec = regDate.getSeconds().length > 3 ? ""+regDate.getSeconds() : "0"+regDate.getSeconds();

        const result = year +"-"+month+"-"+date+" "+ hour+":"+min+":"+sec;

        return result;
    }


    $("form").click(e=>{
        if("예비판독" === e.target.value) decodeType = "예비판독";
        else if("판독" === e.target.value) decodeType = "판독";
    });

    $("form").submit(e=>{
        e.preventDefault();
        const finding = $('#finding').val();
        const conclusion = $('#conclusion').val();
        const recommend = $('#recommend').val();
        const comment = $('#comment').val();

        console.log("finding : "+ finding);
        console.log("conclusion : "+conclusion);
        console.log("recommend : "+recommend);
        console.log("comment : " + comment);
        console.log("decodeType : "+ decodeType);

        let isValid = false;

        if(finding !== "" || conclusion !== "" || recommend !== "" || comment !== ""){
            isValid = true;
        }

        if(!isValid){
            alert("빈 레포트는 제출 불가합니다.");
            return;
        }

        submit(finding,conclusion,recommend,comment);
    });

    function submit(finding,conclusion,recommend,comment){
        const req={
            "studyKey" : studyKey,
            "finding" : finding,
            "conclusion" : conclusion,
            "recommend" : recommend,
            "comment" : comment,
            "decodeType" : decodeType
        }

        $.ajax({
            "url" : "/report",
            "method" : 'POST',
            "headers": {
                "Content-Type": "application/json",
            },"data": JSON.stringify(req)
        }).then(res => {
            const response = res.json();
            console.log(response);
        });

        window.close();
    }


    let pop;

    window.onunload = function() { pop.close(); }

    function popup() {
        var url = `/report/targetReport`;
        var name = "popup test";
        var option = "width=800, height=500, left=100, top=50, location=no"
        pop = window.open(url, name, option);
    }

    $('#content-container').keyup( e => {
        const textarea = e.target;
        textarea.style.height = 'auto';
        textarea.style.height = textarea.scrollHeight + 'px';
    });

    $('#content-container').keydown( e => {
        const textarea = e.target;
        textarea.style.height = 'auto';
        textarea.style.height = textarea.scrollHeight + 'px';
    });

    $("#close-button").click(e =>{
        window.close();
    });

});


