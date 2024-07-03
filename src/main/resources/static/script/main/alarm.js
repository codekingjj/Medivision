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
        row.innerHTML= `${alarm.regDate} | ${alarm.content}`;
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
