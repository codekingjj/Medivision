import DateConverter from "../utils/DateConverter.js";

class ChatElement {
    static create(userCode, data) {
        const { senderUserCode, senderUserName, message, createDate, systemMessage } = data;

        const chatWrapper = document.createElement("div");
        const chatContainer = document.createElement("div");
        const chatElement = document.createElement("p");
        const chatDateElement = this.#createChatDateElement(createDate);

        chatWrapper.classList.add("chat-container-wrapper");
        chatContainer.classList.add("chat-container");
        chatElement.classList.add("chat");

        if (parseInt(senderUserCode) === parseInt(userCode)) {
            chatWrapper.classList.add("right");
            chatElement.innerHTML = message;
        } else if (systemMessage) {
            chatWrapper.classList.add("center");
            chatElement.innerHTML = message;
        } else {
            chatWrapper.classList.add("left");
            chatElement.innerHTML = `${senderUserName}: ${message}`;
        }

        chatContainer.append(chatElement);
        chatContainer.append(chatDateElement);

        chatWrapper.appendChild(chatContainer);

        return chatWrapper;
    }

    static #createChatDateElement(timestamp) {
        const chatDateElement = document.createElement("span");

        chatDateElement.classList.add("chat-date");
        chatDateElement.innerHTML = DateConverter.getDateFromTimestamp(timestamp);

        return chatDateElement;
    }
}

export default ChatElement;