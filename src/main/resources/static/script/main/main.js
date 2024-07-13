$(document).ready(function() {

    // 페이징처리 변수
    let pageCnt;
    let now;
    let tbody = $('.results-section tbody');
    let countArea = $('#search-count');
    let list;
    let pagesize;

    function setting() {
        $.ajax({
            url: '/main/setting',
            type: 'GET',
            success: function(data) {
                list = data;
                tbody.empty();
                pageCnt = Math.ceil(data.length / 10);
                console.log(pageCnt);
                now = 1;
                if(pageCnt === 1){
                    paggten(data);
                }else{
                    pageSetting(data);
                }
                // let tbody = $('.results-section tbody');
                // tbody.empty();
                // console.log(data);
                // data.forEach(function(item) {
                //     if(item.reportstatus === 3){
                //         item.reportstatus = "읽지않음";
                //     }else if(item.reportstatus === 5){
                //         item.reportstatus = "예비판독";
                //     }else if(item.reportstatus === 6){
                //         item.reportstatus = "판독";
                //     }
                //     var row = '<tr id=' + item.studyKey + ' class="tr-area" >' +
                //         '<td>' + item.pid + '</td>' +
                //         '<td>' + item.pname + '</td>' +
                //         '<td>' + item.modality + '</td>' +
                //         '<td>' + item.studydesc + '</td>' +
                //         '<td>' + item.studydate + '</td>' +
                //         '<td>' + item.reportstatus + '</td>' +
                //         '<td>' + item.seriescnt + '</td>' +
                //         '<td>' + item.imagecnt + '</td>' +
                //         '</tr>';
                //     tbody.append(row);
                // });
            },
            error: function(error) {
                console.error('Error fetching data', error);
            }
        });
    }
    setting();
    // $('.results-section tbody').on('click', '.tr-area', function(e) {
    //     let id = $(this).attr('id');
    //     alert(id);
    // });


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
                list = data;
                tbody.empty();
                pageCnt = Math.ceil(data.length / 10);
                console.log(pageCnt);
                now = 1;
                if(pageCnt === 1){
                    paggten(data);
                }else{
                    pageSetting(data);
                }
                // let tbody = $('.results-section tbody');
                // tbody.empty();
                // data.forEach(function(item) {
                //     if(item.reportstatus === 3){
                //         item.reportstatus = "읽지않음";
                //     }else if(item.reportstatus === 5){
                //         item.reportstatus = "예비판독";
                //     }else if(item.reportstatus === 6){
                //         item.reportstatus = "판독";
                //     }
                //     var row = '<tr id=' + item.studyKey + ' class="tr-area" >' +
                //         '<td>' + item.pid + '</td>' +
                //         '<td>' + item.pname + '</td>' +
                //         '<td>' + item.modality + '</td>' +
                //         '<td>' + item.studydesc + '</td>' +
                //         '<td>' + item.studydate + '</td>' +
                //         '<td>' + item.reportstatus + '</td>' +
                //         '<td>' + item.seriescnt + '</td>' +
                //         '<td>' + item.imagecnt + '</td>' +
                //         '</tr>';
                //     tbody.append(row);
                // });
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
                list = data;
                tbody.empty();
                pageCnt = Math.ceil(data.length / 10);
                console.log(pageCnt);
                now = 1;
                if(pageCnt === 1){
                    paggten(data);
                }else{
                    pageSetting(data);
                }
            },

            //     let tbody = $('.results-section tbody');
            //     tbody.empty();
            //
            //     data.forEach(function (item){
            //         if(item.reportstatus === 3){
            //             item.reportstatus = "읽지않음";
            //         }else if(item.reportstatus === 5){
            //             item.reportstatus = "예비판독";
            //         }else if(item.reportstatus === 6){
            //             item.reportstatus = "판독";
            //         }
            //         var row = '<tr id=' + item.studyKey + ' class="tr-area" >' +
            //             '<td>' + item.pid + '</td>' +
            //             '<td>' + item.pname + '</td>' +
            //             '<td>' + item.modality + '</td>' +
            //             '<td>' + item.studydesc + '</td>' +
            //             '<td>' + item.studydate + '</td>' +
            //             '<td>' + item.reportstatus + '</td>' +
            //             '<td>' + item.seriescnt + '</td>' +
            //             '<td>' + item.imagecnt + '</td>' +
            //             '</tr>';
            //         tbody.append(row);
            //     })
            // },
            error: function (error){
                console.error('Error fetching date', error);
            }
        });
    });

    $('.results-section tbody').on('dblclick', '.tr-area', function(e) {
        let id = $(this).attr('id');
        fetch(`/log/${id}`,{
            method : 'GET',
            headers: {
                'Authorization': "Bearer "+localStorage.getItem("jwt")
            }
        })
        window.location.href=`viewer/${id}`;
    });

    // 페이징 처리

    // 10개 이하일때 처리
    function paggten(data){
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
    }

    //10개 이상일때 처리
    function pageSetting(data){
        console.log(now*10);
        let count = 0;
        for(let i=(now-1)*10; i<now*10; i++){
            count++;
            let item = data[i];
            if(item.reportstatus === 3){
                item.reportstatus = "읽지않음";
            }else if(item.reportstatus === 5){
                item.reportstatus = "예비판독";
            }else if(item.reportstatus === 6){
                item.reportstatus = "판독";
            }

            let row = '<tr id=' + item.studyKey + ' class="tr-area" >' +
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
        }
        console.log(count);
    }

    $('#page-down').on('click',function (e){
        if(now !== 1){
            now--;
            tbody.empty();
            pageSetting(list);
        }
    });

    $('#page-up').on('click',function (e){
        if(now !== pageCnt){
            now++;
            tbody.empty();
            pageSetting(list);
        }
    });
});
