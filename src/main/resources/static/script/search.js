$(document).ready(function() {
    $('#search-form').submit(function(event) {
        event.preventDefault();
        fetchData();
    });

    function fetchData() {
        $.ajax({
            url: '/search/findall',
            type: 'GET',
            success: function(data) {
                var tbody = $('.results-section tbody');
                tbody.empty();
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
