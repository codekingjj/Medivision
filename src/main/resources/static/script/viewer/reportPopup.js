$(document).ready(function () {
    let pop = null;
    window.onunload = function() { pop.close(); }
    const url = window.location.pathname.split('/');
    const studyKey = url.at(2);
    console.log(studyKey);
    $('#studykey').val(studyKey);

    function popup() {
        var url = `/reportPage`;
        var name = "report";
        var option = "width=900, height=700, left=100, top=50, location=no"
        pop = window.open(url, name, option);
    }

    $('#report').click(event =>{
        popup();
    });

});