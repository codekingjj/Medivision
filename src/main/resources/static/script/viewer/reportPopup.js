$(document).ready(function () {
    let pop = null;
    window.onunload = function() { pop.close(); }

    function popup() {
        var url = `/reportPage`;
        var name = "report";
        var option = "width=800, height=500, left=100, top=50, location=no"
        pop = window.open(url, name, option);
    }

    $('#report').click(event =>{
        popup();
    });

});