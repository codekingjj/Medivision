fetch("http://192.168.40.97:8080/alarm",{
    method:"GET",
    headers:{
        'Authorization': "Bearer "+localStorage.getItem("jwt")
    }
})
.then(res=>res.json())
.then(response=>{
    console.log(response)
    console.log(response.alarmList);
    displayAlarm(response.alarmList);
    displayAlarmCount(response.check);
})

function displayAlarm(data){
    const content = document.querySelector('.alarm-content');
    content.innerHTML="";
    data.forEach(alarm =>{
        const row = document.createElement('p');
        if(data.check)
            row.className="check";
        else
            row.className="no-check";
        row.innerHTML= `${alarm.regDate} | ${alarm.content}`;

        row.onclick = function() {
            handleAlarmClick(alarm.alarmIndex);
        };

        content.appendChild(row);
    })
}

function displayAlarmCount(data){
    const content = document.querySelector('#notification');
    content.innerHTML="";
    if(data !== 0){
    const count = document.createElement('span');
        count.className="note-num";
        count.innerHTML=`${data}`;
        content.appendChild(count);
    }
}
function handleAlarmClick(index) {
    console.log(`Alarm index: ${index}`);
    // 여기에 원하는 로직을 추가할 수 있습니다.
}