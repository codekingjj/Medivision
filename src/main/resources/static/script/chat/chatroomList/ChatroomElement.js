import DateConverter from "../utils/DateConverter.js";
import ChatRootPage from "../pageElements/ChatRootPage.js";

class ChatroomElement {
    static getChatrooms(chatrooms) {
        return chatrooms.map(chatroom => this.#create(chatroom));
    }

    static update(messageData) {
        const { roomId, message, createDate } = messageData;

        this.#updateLatestMessage(roomId, message);
        this.#updateLatestMessageDate(roomId, createDate);
        this.#updateNewMessageCounter(roomId);
    }

    static #updateNewMessageCounter(roomId) {
        const elementId = this.#getNewMessageCounterElementId(roomId);

        const element = $(`#${elementId}`);
        const currentCountString = element.text();
        let newCount = 1;

        if (element.hasClass("hidden"))
            element.removeClass("hidden");

        if (currentCountString !== "")
            newCount = parseInt(currentCountString) + 1;

        element.text(newCount);
    }

    static #updateLatestMessage(roomId, message) {
        const elementId = this.#getLatestMessageElementId(roomId);

        $(`#${elementId}`).text(message);
    }

    static #updateLatestMessageDate(roomId, createDate) {
        const elementId = this.#getLatestMessageDateElementId(roomId);

        $(`#${elementId}`).text(DateConverter.getDateFromTimestamp(createDate));
    }

    static #create(chatroomData) {
        const { roomId, name, latestMessage, latestMessageSendDate, numOfUnreadMessages } = chatroomData;

        const chatroomContainer = this.#createChatroomContainer(roomId);
        const headerElement = this.#createHeaderElement(roomId, name, numOfUnreadMessages);
        const contentContainer = this.#createContentElement(roomId, latestMessage, latestMessageSendDate);

        chatroomContainer.append(headerElement);
        chatroomContainer.append(contentContainer);

        return chatroomContainer;
    }

    static #createChatroomContainer(roomId) {
        const chatroomContainer = $("<div>", {
            class: "chatroom-container",
        });

        chatroomContainer.on("click", () => {
            ChatRootPage.renderChatroom(roomId);
        });

        return chatroomContainer;
    }

    static #createLatestMessageElement(roomId, latestMessage) {
        const latestMessageElement = $("<span>", {
            class: "message",
            id: this.#getLatestMessageElementId(roomId),
            text: latestMessage,
        });

        return latestMessageElement;
    }

    static #createLatestMessageDateElement(roomId, latestMessageSendDate) {
        const latestMessageDateElement = $("<span>", {
            class: "latest-message-date",
            id: this.#getLatestMessageDateElementId(roomId),
            text: DateConverter.getDateFromTimestamp(latestMessageSendDate),
        });

        return latestMessageDateElement;
    }

    static #createNewMessageCounterElement(roomId, numOfUnreadMessages) {
        const element = $("<span>", {
            class: "new-message-counter",
            id: this.#getNewMessageCounterElementId(roomId),
        });

        if (numOfUnreadMessages > 0) {
            element.text(numOfUnreadMessages);
        } else {
            element.addClass("hidden");
        }

        return element;
    }

    static #createHeaderElement(roomId, roomName, numOfUnreadMessages) {
        const header = $("<div>", {
            class: "chatroom-header-container",
        });

        const roomNameElement = $("<span>", {
            class: "chatroom-name",
            text: roomName,
        });

        const newMessageCounterElement = this.#createNewMessageCounterElement(roomId, numOfUnreadMessages);

        header.append(roomNameElement);
        header.append(newMessageCounterElement);

        return header;
    }

    static #createContentElement(roomId, latestMessage, latestMessageSendDate) {
        const contentContainer = $("<div>", {
            class: "chatroom-content-container",
        });

        const latestMessageElement = this.#createLatestMessageElement(roomId, latestMessage, latestMessageSendDate);
        const latestMessageDateElement = this.#createLatestMessageDateElement(roomId, latestMessageSendDate);

        contentContainer.append(latestMessageElement);
        contentContainer.append(latestMessageDateElement);

        return contentContainer;
    }

    static #getLatestMessageElementId(roomId) {
        return `latestMessage_${roomId}`;
    }

    static #getLatestMessageDateElementId(roomId) {
        return `latestMessageDate_${roomId}`;
    }

    static #getNewMessageCounterElementId(roomId) {
        return `newMessageCounter_${roomId}`;
    }
}

export default ChatroomElement;