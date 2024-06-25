let stompClient = null;

function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/chat/messages', function (message) {
            showMessage(message.body);
        });
    });
}

function sendMessage() {
    var message = document.getElementById('messageInput').value;
    stompClient.send("/chat/sendMessage", {}, message);
}

function showMessage(message) {
    var messagesDiv = document.getElementById('messages');
    var messageElement = document.createElement('div');
    messageElement.appendChild(document.createTextNode(message));
    messagesDiv.appendChild(messageElement);
}

connect();
