import Fetch from "../utils/Fetch.js";
import ChatElement from "./ChatElement.js";
import StompManager from "./StompManager.js";
import RedirectPage from "../common/RedirectPage.js";

let userCode;
let isLastPage = false;
let pageNumber = 0;

window.onload = () => {
    loadEventListeners();

    init();
};

async function init() {
    const roomId = $("#roomId").val();
    userCode = await Fetch.getUserCode();
    console.log("userCode: " + userCode);

    StompManager.connectStomp(roomId, stompSubscribeCallbackFunc);

    populateChatHistory();
}

function stompSubscribeCallbackFunc(receivedData) {
    console.log("=== received ===")
    console.log(receivedData);
    console.log("=== ======== ===")

    const chatContainer = ChatElement.create(userCode, receivedData);

    $(".chat-list-container").append(chatContainer);
    scrollToBottomChatListContainer();
}

function loadEventListeners() {
    $("#chatListContainer").on("scroll", (e) => handleChatListScroll(e));

    $("#btnMessageSend").on("click", () => sendMessage());
    $("#btnChatroomToChatroomList").on("click", () => handleGoToChatroomList());
    $("#btnChatroomLeave").on("click", () => leaveChatroom());

    $("#inputMessage").on("keyup", (e) => {
        if (e.key !== "Enter")
            return;

        sendMessage();
    });
}

function handleGoToChatroomList() {
    const roomId = $("#roomId").val();

    const bodyData = {
        roomId: roomId,
        userCode: userCode,
        lastVisitedDate: new Date(),
        createDate: "",
    };

    fetch("/chatroomMember/saveLastVisitedDate", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
        body: JSON.stringify(bodyData),
    })
    .then(response => {
        if (response.ok)
            RedirectPage.toChatroomList();
    })
    .catch(err => {
        RedirectPage.toSignInPage();
    });
}

async function handleChatListScroll(e) {
    console.log(e.target.scrollTop); // container scroll y-position

    if (e.target.scrollTop === 0 && !isLastPage) {
        const currentHeight = e.target.scrollHeight;

        await populateChatHistory();

        const element = document.getElementById("chatListContainer");

        element.scrollTop = element.scrollHeight - currentHeight;
    }
}

async function populateChatHistory() {
    const data = await fetchChatList();
    const { chatDtoList, lastPage } = data;

    populateChat(chatDtoList);
    scrollToBottomChatListContainer();

    isLastPage = lastPage;
}

function scrollToBottomChatListContainer() {
    const element = document.getElementById("chatListContainer");
    element.scrollTop = element.scrollHeight;
}

function leaveChatroom() {
    const roomId = $("#roomId").val();

    const bodyData = {
        roomId: roomId,
        userCode: userCode,
    };

    fetch("/chatroomAndMember/delete", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
        body: JSON.stringify(bodyData),
    })
    .then(response => {
        if (response.ok) {
            const data = {
                roomId: roomId,
                message: "",
                systemMessage: true,
                senderUserCode: userCode,
                createDate: Date.now(),
            };

            StompManager.client.send("/chat", {}, JSON.stringify(data));
            window.location.href = "/chatroom";
        }
    })
    .catch(err => {
        console.log(err);
    });
}

function sendMessage() {
    const message = $("#inputMessage").val();
    const roomId = $("#roomId").val();

    if (!isValidMessage(message))
        return;

    const data = {
        roomId: roomId,
        message: message,
        systemMessage: false,
        senderUserCode: userCode,
        createDate: Date.now(),
    };

    console.log("=== sending ===");
    console.log(data);
    console.log("=== ====== ===");

    StompManager.client.send("/chat", {}, JSON.stringify(data));

    $("#inputMessage").val("");
}

async function populateChat(chats) {
    chats.map(chat => {
        const chatContainer = ChatElement.create(userCode, chat);
        $(".chat-list-container").prepend(chatContainer);
    });
}

async function fetchChatList() {
    const roomId = $("#roomId").val();

    const bodyData = {
        "pageNumber": pageNumber++,
    };

    const data = await fetch(`/chat/${roomId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`,
        },
        body: JSON.stringify(bodyData),
    })
    .then(response => {
        return response.json();
    })
    .then(data => {
        return data;
    })
    .catch(err => err);

    return data;
}

function isValidMessage(message) {
    return message && message.length > 0;
}