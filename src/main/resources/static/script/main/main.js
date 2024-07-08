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
                    var row = '<tr id=' + item.studyKey + ' class="tr-area" >' +
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
    $('.results-section tbody').on('click', '.tr-area', function(e) {
        let id = $(this).attr('id');
        alert(id);
    });


    $('#search-form').submit(function(event) {
        event.preventDefault();
    });

    $('#search').click(e =>{
        fetchData();
    });

    function fetchData() {
        // 폼 데이터를 가져옴
        let formData = $('#search-form').serialize();

        $.ajax({
            url: '/main/search',
            type: 'GET',
            data: formData, // 폼 데이터를 전송
            success: function(data) {
                let tbody = $('.results-section tbody');
                tbody.empty();
                data.forEach(function(item) {
                    if(item.reportstatus === 3){
                        item.reportstatus = "읽지않음";
                    }else if(item.reportstatus === 5){
                        item.reportstatus = "예비판독";
                    }else if(item.reportstatus === 6){
                        item.reportstatus = "판독";
                    }
                    var row = '<tr id=' + item.studyKey + ' class="tr-area" >' +
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
    $('.search-button').on('click',function(e) {
        let date = $(this).attr('value');

        $.ajax({
            url : '/main/date',
            type : 'GET',
            data: {date : date},
            success:function (data){
                let tbody = $('.results-section tbody');
                tbody.empty();

                data.forEach(function (item){
                    if(item.reportstatus === 3){
                        item.reportstatus = "읽지않음";
                    }else if(item.reportstatus === 5){
                        item.reportstatus = "예비판독";
                    }else if(item.reportstatus === 6){
                        item.reportstatus = "판독";
                    }
                    var row = '<tr id=' + item.studyKey + ' class="tr-area" >' +
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
                })
            },
            error: function (error){
                console.error('Error fetching date', error);
            }
        });
    });
});
