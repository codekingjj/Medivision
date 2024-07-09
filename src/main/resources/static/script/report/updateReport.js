function adjustTextareaHeight(textarea) {
    textarea.style.height = 'auto';
    textarea.style.height = textarea.scrollHeight + 'px';
}

$(document).ready(function(){
    $('textarea[readonly]').each(function() {
        adjustTextareaHeight(this);
    });

    $("#close-button").click(e => {
        window.close();
    });

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


    $('#submit').click(event =>{
        const regDate = $('#regDate').val();
        if(!timestamp(regDate)) {
            alert("수정가능한 시간이 아닙니다.");
            window.close();
        }


    });

});