function adjustTextareaHeight(textarea) {
    textarea.style.height = 'auto';
    textarea.style.height = textarea.scrollHeight + 'px';
}

$(document).ready(function(){

    $('textarea').each(function() {
        adjustTextareaHeight(this);
    });

    $("#close-button").click(e => {
        window.close();
    });

    $('tbody').keyup( e => {
        const textarea = e.target;
        textarea.style.height = 'auto';
        textarea.style.height = textarea.scrollHeight + 'px';
    });

    $('tbody').keydown( e => {
        const textarea = e.target;
        textarea.style.height = 'auto';
        textarea.style.height = textarea.scrollHeight + 'px';
    });

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
            if(days == 0 &&hour < 1) return true;

            return false;
        } else {
            return false;
        }
    }
    //날짜 확인
    const regDate = $('#regDate').val();
    remaindTime(regDate);


    $('#cancle').click(e =>{
        window.close();
    })

    $('form').submit(e => {
        e.preventDefault();
        const regDate = $('#regDate').val();
        if(!remaindTime(regDate)) {
            alert("수정 가능한 기간이 아닙니다.");
            alert(regDate);
            alert(remaindTime(regDate));
            window.close();
        }

        const reportIndex = $('#reportIndex').val();
        const finding = $('#finding').val();
        const conclusion = $('#conclusion').val();
        const recommend = $('#recommend').val();
        const comment = $('#comment').val();
        const typeDecode = $('#typeDecode').val();
        const studyKey = $('#studyKey').val();

        console.log("index : "+ reportIndex);
        console.log("finding : "+ finding);
        console.log("conclusion : "+ conclusion);
        console.log("recommend : "+recommend);
        console.log("comment : " + comment);
        console.log("decodeType : "+ typeDecode);


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
            "typeDecode" : typeDecode
        }

        const token = localStorage.getItem("jwt");
        $.ajax({
            "url" : `/report/update/${reportIndex}`,
            "method" : 'PUT',
            "headers": {
                "Content-Type": "application/json",
                "Authorization" : `Bearer ${token}`
            },"data": JSON.stringify(req)
        }).then(res => {
            if ("SU" === res.code) {
                alert("수정을 완료했습니다.");
                opener.location.reload();
                window.close();
            }else if("NU" === res.code){
                alert("해당 보고서를 작성한 유저가 아닙니다.");
                window.close();
            }
            else{
                alert("해당 판독으로 수정이 불가합니다.");
            }
        });
    });

});