import ChatroomElement from "./ChatroomElement.js";
import Fetch from "../utils/Fetch.js";
import StompManager from "../chatroom/StompManager.js";

let userCode;

window.onload = () => {
    init();
};

function stompSubscribeCallbackFunc(messageData) {
    console.log("=== received ===")
    console.log(messageData);
    console.log("=== ======== ===");

    ChatroomElement.update(messageData);
    //ChatroomElement.updateChatroomLatestMessage(receivedData);
}

async function init() {
    userCode = await Fetch.getUserCode();
    //StompManager.connectStompUser(userCode);
    const chatrooms = await fetchChatrooms();
    const chatroomElements = ChatroomElement.getChatrooms(chatrooms);

    for (const chatroomElement of chatroomElements) {
        $(".chatroom-list-container").append(chatroomElement);
    }

    let chatroomIds = [];

    for (const chatroom of chatrooms) {
        chatroomIds.push(chatroom.roomId);
    }

    console.log(chatroomIds);

    StompManager.connectStompChatroomAll(chatroomIds, stompSubscribeCallbackFunc);

    console.log(userCode);
    console.log(chatrooms);
}

async function fetchChatrooms() {
    return await fetch("/chatroomAndMember/all", {
        method: "GET",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
    })
    .then(response => response.json())
    .then(data => {
        return data;
    })
    .catch(err => {
        console.log(err);
    });
}