    fetch("/log/report",{
        method:"POST",
        headers:{
            'Authorization': "Bearer "+localStorage.getItem("jwt")
        }
    })
        .then(res => res.json())
        .then(response =>{
            displayStudyLogs(response.list);
        })
function displayStudyLogs(logData) {
    const tableBody = document.querySelector('.log-table tbody');
    tableBody.innerHTML = ''; // 기존 내용을 비웁니다.
    logData.forEach(log => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <th>${log.userId}</th>
            <th>${log.clientIp}</th>
            <th>${log.reportDate}</th>
            <th>${log.studyKey}</th>
            <th>${log.reportIndex}</th>
            <th>${log.reportType}</th>
        `;
        tableBody.appendChild(row);
    });
}