$(document).ready(function() {


    function setting() {
        $.ajax({
            url: '/main/setting',
            type: 'GET',
            success: function(data) {
                let tbody = $('.results-section tbody');
                tbody.empty();
                console.log(data);
                data.forEach(function(item) {
                    if(item.reportstatus === 3){
                        item.reportstatus = "읽지않음";
                    }else if(item.reportstatus === 5){
                        item.reportstatus = "예비판독";
                    }else if(item.reportstatus === 6){
                        item.reportstatus = "판독";
                    }
                    var row = '<tr>' +
                        '<td>' + item.pid + '</td>' +
                        '<td>' + item.pname + '</td>' +
                        '<td>' + item.modality + '</td>' +
                        '<td>' + item.studydesc + '</td>' +
                        '<td>' + item.studydate + '</td>' +
                        '<td>' + item.reportstatus + '</td>' +
                        '<td>' + item.seriescnt + '</td>' +
                        '<td>' + item.imagecnt + '</td>' +
                        '</tr>';
                    tbody.append(row);
                });
            },
            error: function(error) {
                console.error('Error fetching data', error);
            }
        });
    }
    setting();
});
