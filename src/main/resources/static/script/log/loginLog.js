    fetch("http://192.168.40.97:8081/log/login",{
        method:"POST",
        headers:{
            'Authorization': "Bearer "+localStorage.getItem("jwt")
        }
    })
        .then(res => res.json())
        .then(response =>{
            console.log(response);
            console.log(response.list);
            displayLoginLogs(response.list);
        })
function displayLoginLogs(logData) {
    const tableBody = document.querySelector('.log-table tbody');
    tableBody.innerHTML = ''; // 기존 내용을 비웁니다.
    logData.forEach(log => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <th>${log.userId}</th>
            <th>${log.clientIp}</th>
            <th>${log.loginDate}</th>
        `;
        tableBody.appendChild(row);
    });
}