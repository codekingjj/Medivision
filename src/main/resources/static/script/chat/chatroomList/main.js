import ChatroomElement from "./ChatroomElement.js";

window.onload = () => {
    loadChatroomData();
};

function loadChatroomData() {
    fetch("/chatroomAndMember/all", {
        method: "GET",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        const chatrooms = ChatroomElement.getChatrooms(data);

        for (const chatroom of chatrooms)
            $(".chatroom-list-container").append(chatroom);
    })
    .catch(err => {
        console.log(err);
    });
}