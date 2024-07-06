function remaindTime(reportTime) {

    // 현재 시간을 구한다.
    var reportDate = new Date(reportTime);
    // 마감 기간을 가져온다.
    var end = new Date();

    // 현재 시간을 ms로 반환한다.
    var rt = reportDate.getTime();
    // 마감 기간을 ms로 반환한다.
    var et = end.getTime();

    // 마감 기간이 현재 시간보다 클 경우
    if (rt < et) {
        sec = parseInt(et - rt) / 1000;
        days = parseInt(sec / 60 / 60 / 24);
        sec = sec - days * 60 * 60 * 24;
        hour = parseInt(sec / 60 / 60);
        sec = sec - hour * 60 * 60;
        min = parseInt(sec / 60);
        sec = parseInt(sec - min * 60);

        if (hour < 10) {
            hour = '0' + hour;
        }
        if (min < 10) {
            min = '0' + min;
        }
        if (sec < 10) {
            sec = '0' + sec;
        }
        // return days + '일 ' + hour + '시간 ' + min + '분 ' + sec + '초';

        if(days < 1) return true;

        return false;
    } else {
        return false;
    }
}

const token = localStorage.getItem("jwt");

$(document).ready(function() {
    //부모창 뷰어페이지가 지닌 스터디키 가져오기
    // const studyKey = window.opener.studyKey;
    const studyKey = 1;

    let decodeType ="";
    let reportData = null;
    getReport()

    function getReport(){
        $.ajax({
            "url" : `/reports/${studyKey}`,
            "method" : 'GET',
            "headers": {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`
            }
        }).then(res => {
            const data = res.result;
            console.log(res);
            console.log(data);

            data.forEach(function(report){
                const trE = document.createElement("tr");
                trE.id = report.reportIndex;

                const td1 = document.createElement('td');
                td1.innerText = report.typeDecode;
                td1.className = "typeDecode";

                const td2 = document.createElement('td');
                td2.innerText = report.writerName;
                td2.className = "writerName";

                const td3 = document.createElement('td');
                td3.innerText = report.comment;
                td3.className = "comment";

                const td4 = document.createElement('td');
                const date = timestamp(report.regDate);
                td4.innerText = date;
                td4.className= "regDate";

                const td5 = document.createElement("td");
                td5.className = "update-button";
                if(remaindTime(report.regDate) && res.userName === report.writerName){
                    const update = document.createElement("button");
                    update.className = "update";
                    update.innerText = "✎";
                    td5.append(update);
                }

                trE.append(td1);
                trE.append(td2);
                trE.append(td3);
                trE.append(td4);
                trE.append(td5);
                $("tbody").append(trE);
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

    let pop;

    window.onunload = function() {pops.close();}
    // window.opener.onunload = function() {window.close();}

    function popup(index) {

        var url = `/report/targetReport?index=${index}`;
        var name = "targetReport";
        var option = "width=800, height=500, left=100, top=50, location=no";

        pop = window.open(url, name, option);
        console.log(pop);
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

    $("tbody").click(e =>{
        if("update" === e.target.className) return;
        const index = e.target.parentNode.id;

        if(index == null) return;
        console.log(index);

        popup(index);
    });

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

        $.ajax({
            "url" : "/createReport",
            "method" : 'POST',
            "headers": {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`
            },"data": JSON.stringify(req)
        }).then(res => {
            if("SR"===res.code || "RF" === res.code){
                alert("이미 작성했거나 해당 판독이 완료된 상태입니다.");
                return;
            }

            if("SU" === res.code){
                $('#finding').val("");
                $('#conclusion').val("");
                $('#recommend').val("");
                $('#comment').val("");

                window.close();
            }
        });
    });

});