// window.addEventListener('message',function(event){
//     if(event.origin !== window.location.origin)return;
//     const report = event.data;
//     reportI = report.index;
// });
const urlParams = new URLSearchParams(window.location.search);
const index = JSON.parse(decodeURIComponent(urlParams.get('index')));

$(document).ready(function () {
    urlParams.delete("index",index);
    autoSize();

    $("#close-button").click(e => {
        window.close();
    });

    function autoSize(){
        $('#finding').height($('#finding').scrollHeight);

        $('#conclusion').height($('#conclusion').scrollHeight);
    }

});