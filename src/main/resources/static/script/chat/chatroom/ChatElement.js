import DateConverter from "../utils/DateConverter.js";

class ChatElement {
    static create(userCode, data) {
        const { senderUserCode, senderUserName, senderUserId, message, createDate } = data;

        return (parseInt(senderUserCode) === parseInt(userCode)) ?
            this.#createSelfMessage(message, createDate) :
            this.#createOtherUserMessage(senderUserId, senderUserName, message, createDate);
    }
    
    static #createSelfMessage(message, createDate) {
        const chatWrapper = this.#createChatWrapper("right");

        const chatContainer = this.#createChatContainer();
        const chatMessageElement = this.#createChatMessageElement(message);
        const chatDateElement = this.#createChatDateElement(createDate);

        chatContainer.append(chatMessageElement);
        chatContainer.append(chatDateElement);
        chatWrapper.append(chatContainer);

        return chatWrapper;
    }

    static #createOtherUserMessage(senderUserId, senderUserName, message, createDate) {
        const chatWrapper = this.#createChatWrapper("left");

        const chatContainer = this.#createChatContainer();
        const chatMessageElement = this.#createChatMessageElement(message);
        const chatDateElement = this.#createChatDateElement(createDate);

        chatMessageElement.text(message);

        chatContainer.append(chatMessageElement);

        const chatOtherUserMessageContainer = $("<div>", {
            class: "chat-other-user-message-container",
        });

        const chatOtherUserInfo = $("<span>", {
            class: "chat-other-user-info",
            text: `${senderUserName} (${senderUserId})`,
        });

        chatOtherUserMessageContainer.append(chatOtherUserInfo);
        chatOtherUserMessageContainer.append(chatMessageElement);

        chatContainer.append(chatOtherUserMessageContainer);
        chatContainer.append(chatDateElement);
        chatWrapper.append(chatContainer);

        return chatWrapper;
    }

    static #createChatWrapper(className) {
        return $("<div>", {
            class: `chat-container-wrapper ${className}`,
        });
    }

    static #createChatContainer() {
        return $("<div>", {
            class: "chat-container",
        });
    }

    static #createChatMessageElement(message) {
        return $("<span>", {
            class: "chat",
            text: message,
        });
    }

    static #createChatDateElement(timestamp) {
        return $("<span>", {
            class: "chat-message-sent-date",
            text: DateConverter.getDateFromTimestamp(timestamp),
        });
    }
}

export default ChatElement;