const token = localStorage.getItem("jwt");

$(document).ready(function() {
    let pop ;
    $("tbody").click(e =>{
        if("update" !== e.target.className) return;
        const index = e.target.parentNode.parentNode.id;
        console.log(index);
        popup(index);
    });

    function popup(index){
        var url = `/report/update?index=${index}`;
        var name = "updateReport";
        var option = "width=800, height=500, left=100, top=50, location=no";

        pop = window.open(url, name, option);
    }
});