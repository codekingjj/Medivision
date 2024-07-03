fetch("http://192.168.40.97:8080/alarm",{
    method:"GET",
    headers:{
        'Authorization': "Bearer "+localStorage.getItem("jwt")
    }
})
.then(res=>res.json())
.then(response=>{
    console.log(response.alarmList);
    displayAlarm(response.alarmList);
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
