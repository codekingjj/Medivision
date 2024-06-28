$(document).ready(function() {

    let count = "";

    let startDate = $('#startDate').val();
    let endDate = $('#endDate').val();
    $("#startDate").on("change", function(event) {
        startDate = $('#startDate').val();
        console.log(startDate);
        console.log(typeof startDate);
    });


    $("#endDate").on("change", function(event) {
        endDate = $('#endDate').val();
        console.log(endDate);
        console.log(typeof endDate);
    });
    console.log(startDate);
    console.log(endDate);

    document.getElementById('endDate').value = new Date().toISOString().substring(0, 10);

    $('#search-form').submit(function(event) {
        event.preventDefault();
        fetchData();
    });

    function fetchData() {
        // 폼 데이터를 가져옴
        let formData = $('#search-form').serialize();

        $.ajax({
            url: '/search/detail',
            type: 'GET',
            data: formData, // 폼 데이터를 전송
            success: function(data) {
                let tbody = $('.results-section tbody');
                let countArea = $('#search-count');
                tbody.empty();
                countArea.empty();
                console.log(data);
                count = '<p>' + data.length + '명의 환자를 찾았습니다.</p>';
                countArea.append(count);
                data.forEach(function(item) {
                    var row = '<tr>' +
                        '<td>' + item.pname + '</td>' +
                        '<td>' + item.modality + '</td>' +
                        '<td>' + item.studydesc + '</td>' +
                        '<td>' + item.studydate + '</td>' +
                        '</tr>';
                    tbody.append(row);
                });
            },
            error: function(error) {
                console.error('Error fetching data', error);
            }
        });
    }
});
