let stompClient = null;

function connect() {
    const WEBSOCKET_END_POINT = "/chat";

    let socket = new SockJS(WEBSOCKET_END_POINT);

    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        console.log("Connected");

        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(message.body);
        });
    });
}

function sendMessage() {
    let message = document.getElementById('messageInput').value;
    const roomId = 1;
    const userCode = 1;

    const data = {
        roomId: roomId,
        senderUserCode: userCode,
        message: message,
    };

    stompClient.send("/chat/sendMessage", {}, JSON.stringify(data));

    console.log("message sent");
}

function showMessage(message) {
    var messagesDiv = document.getElementById('messages');
    var messageElement = document.createElement('div');
    messageElement.appendChild(document.createTextNode(message));
    messagesDiv.appendChild(messageElement);
}

connect();

window.onload = () => {

};
