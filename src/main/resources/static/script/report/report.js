$(document).ready(function() {
    //부모창 뷰어페이지가 지닌 스터디키 가져오기
    // const studyKey = window.opener.studyKey;
    const studyKey = 1
    let decodeType ="";
    let reportData = null;

    let reportIndex = [];
    getReport()

    function getReport(){
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
            reportIndex = [];
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
    }


    function timestamp(dates){
        const regDate = new Date(dates);

        const year = regDate.getFullYear();
        const month = (regDate.getMonth()+1) > 9 ? (regDate.getMonth()+1) : "0"+(regDate.getMonth()+1);
        const date = regDate.getDate() > 9 ? ""+regDate.getDate() : "0"+ regDate.getDate();
        const hour = regDate.getHours() > 9 ? ""+regDate.getHours() : "0"+ regDate.getHours();
        const min = regDate.getMinutes() > 9 ? ""+regDate.getMinutes() : "0"+ regDate.getMinutes();
        const sec = regDate.getSeconds() > 9 ? ""+regDate.getSeconds() : "0"+regDate.getSeconds();

        const result = year +"-"+month+"-"+date+" "+ hour+":"+min+":"+sec;

        return result;
    }


    $("table").click(e =>{
        console.log(e.target);

        let report = null;

        if("tr1" === e.target.id || "tr1" === e.target.className) report = 0;
        else if ("tr2" === e.target.id || "tr2" === e.target.className) report = 1;
        else if ("tr3" === e.target.id || "tr3" === e.target.className) report = 2;

        if(report == null) return;
        console.log(report);
        reportData = reportIndex.at(`${report}`);
        console.log(reportData);
        if(reportData != null) {
            popup();
            sendMessage(reportData);
        }
    })

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
        }else if(localStorage.getItem("jwt") == null){
            alert("유저가 없습니다.");
            return;
        }


        const req={
            "studyKey" : studyKey,
            "finding" : finding,
            "conclusion" : conclusion,
            "recommend" : recommend,
            "comment" : comment,
            "typeDecode" : decodeType
        }

        const token = localStorage.getItem("jwt")

        $.ajax({
            "url" : "/report",
            "method" : 'POST',
            "headers": {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`
            },"data": JSON.stringify(req)
        }).then(res => {
            if("SU" === res.code){
                $('#finding').val("");
                $('#conclusion').val("");
                $('#recommend').val("");
                $('#comment').val("");

                getReport();
            }
        });
    });



    let pop;

    window.onunload = function() { pop.close(); }

    function popup() {
        var url = `/report/targetReport`;
        var name = "targetReport";
        var option = "width=800, height=500, left=100, top=50" //, location=no
        pop = window.open(url, name, option);
    }



    // 2. 팝업창이 정상적으로 로드된 후 메세지 송신(부모창 A에게 메세지 송신)
    function sendMessage(index) {
        pop.postMessage(JSON.stringify({index: index}), window);
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