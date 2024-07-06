const WS_END_POINT = "/websocket"

let userCode;
let client;
let isLastPage = false;
let pageNumber = 0;

window.onload = () => {
    fetchUserCode();
    connectStomp();
    populateChatHistory();

    $("#chatListContainer").on("scroll", (e) => handleChatListScroll(e));
};

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

function exitChatroom() {
    window.location.href = "/chatroomAndMember";
}

function sendMessage() {
    const message = $("#inputMessage").val();
    const roomId = $("#roomId").val();

    if (!isValidMessage(message))
        return;

    const data = {
        roomId: roomId,
        message: message,
        senderUserCode: userCode,
        createDate: Date.now(),
    };

    client.send("/chat", {}, JSON.stringify(data));

    const chatContainer = createChatElement(data);
    $(".chat-list-container").append(chatContainer);
    $("#inputMessage").val("");

    scrollToBottomChatListContainer();
}

async function populateChat(chats) {
    chats.map(chat => {
        const chatContainer = createChatElement(chat);
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

function fetchUserCode() {
    fetch("/chat/userCode", {
        method: "GET",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
    })
    .then(response => {
        return response.json();
    })
    .then(data => {
        userCode = data;
    })
    .catch(err => {
        console.log(err);
    });
}

function isValidMessage(message) {
    return message && message.length > 0;
}

function createChatElement(data) {
    const { senderUserCode, senderUserName, message, createDate } = data;

    const chatWrapper = document.createElement("div");
    const chatContainer = document.createElement("div");
    const chatElement = document.createElement("p");
    const chatDateElement = createChatDateElement(createDate);

    chatWrapper.classList.add("chat-container-wrapper");
    chatContainer.classList.add("chat-container");
    chatElement.classList.add("chat");

    console.log("usercode: " + userCode)

    if (parseInt(senderUserCode) === parseInt(userCode)) {
        chatWrapper.classList.add("right");
        chatElement.innerHTML = message;
    } else {
        chatWrapper.classList.add("left");
        chatElement.innerHTML = `${senderUserName}: ${message}`;
    }

    chatDateElement.innerHTML = getDateFromTimestamp(createDate);

    chatContainer.append(chatElement);
    chatContainer.append(chatDateElement);

    chatWrapper.appendChild(chatContainer);

    return chatWrapper;
}

function createChatDateElement(timestamp) {
    const chatDateElement = document.createElement("span");

    chatDateElement.classList.add("chat-date");
    chatDateElement.innerHTML = getDateFromTimestamp(timestamp);

    return chatDateElement;
}

// websocket
function connectStomp() {
    const sock = new SockJS(WS_END_POINT);
    client = Stomp.over(sock);

    client.connect({}, () => {
        console.log("stomp conntected");

        const roomId = $("#roomId").val();

        client.subscribe(`/chatroom/${roomId}`, (event) => {
            const receivedData = JSON.parse(event.body);
            const chatContainer = createChatElement(receivedData);

            if (receivedData.senderUserCode !== Number(userCode)) {
                console.log("received other message from server")
                $(".chat-list-container").append(chatContainer);
            }
        });
    });
}

function getDateFromTimestamp(timestamp) {
    const date = new Date(timestamp);
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const seconds = date.getSeconds();

    const formattedDate = `${year}-${String(month).padStart(2, '0')}-${String(day).padStart(2, '0')}`;
    const formattedTime = `${String(hours).padStart(2, '0')}:${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;

    return formattedDate + " " + formattedTime;
}
