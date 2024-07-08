import DateConverter from "../utils/DateConverter.js";

class ChatroomElement {
    static getChatrooms(chatrooms) {
        const chatroomContainerList = chatrooms.map(chatroom => {
            return this.#create(chatroom);
        });

        return chatroomContainerList;
    }

    static #createChatroomContainer(roomId) {
        const chatroomContainer = document.createElement("div");
        chatroomContainer.classList.add("chatroom-container");

        chatroomContainer.addEventListener("click", () => {
            location.href = `/chatroom/${roomId}`;
        });

        return chatroomContainer;
    }

    static update(messageData) {
        const { roomId, message, createDate } = messageData;

        this.#updateLatestMessage(roomId, message);
        this.#updateLatestMessageDate(roomId, createDate);
        this.#updateNewMessageCounter(roomId);
    }

    static #updateNewMessageCounter(roomId) {
        const elementId = this.#getNewMessageCounterElementId(roomId);

        if ($(`#${elementId}`).hasClass("hidden"))
            $(`#${elementId}`).removeClass("hidden");

        const currentCountString = $(`#${elementId}`).html();
        let newCount = 1;

        if (currentCountString !== "") {
            newCount = parseInt(currentCountString) + 1;
        }

        $(`#${elementId}`).html(newCount);
    }

    static #updateLatestMessage(roomId, message) {
        const elementId = this.#getLatestMessageElementId(roomId);

        $(`#${elementId}`).html(message);
    }

    static #updateLatestMessageDate(roomId, createDate) {
        const elementId = this.#getLatestMessageDateElementId(roomId);

        $(`#${elementId}`).html(DateConverter.getDateFromTimestamp(createDate));
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

    static #createLatestMessageElement(roomId, latestMessage) {
        const latestMessageElement = document.createElement("span");

        latestMessageElement.classList.add("message");
        latestMessageElement.id = this.#getLatestMessageElementId(roomId);
        latestMessageElement.innerHTML = latestMessage;

        return latestMessageElement;
    }

    static #createLatestMessageDateElement(roomId, latestMessageSendDate) {
        const latestMessageDateElement = document.createElement("span");

        latestMessageDateElement.id = this.#getLatestMessageDateElementId(roomId);
        latestMessageDateElement.innerHTML = DateConverter.getDateFromTimestamp(latestMessageSendDate);

        return latestMessageDateElement;
    }

    static #createNewMessageCounterElement(roomId, numOfUnreadMessages) {
        const element= document.createElement("span");

        element.classList.add("new-message-counter");
        element.id = this.#getNewMessageCounterElementId(roomId);

        if (numOfUnreadMessages > 0) {
           element.innerHTML = numOfUnreadMessages;
        } else {
            element.classList.add("hidden");
        }

        return element;
    }

    static #createHeaderElement(roomId, roomName, numOfUnreadMessages) {
        const headerElement = document.createElement("div");

        const roomNameElement = document.createElement("span");
        const newMessageCounterElement= this.#createNewMessageCounterElement(roomId, numOfUnreadMessages);

        roomNameElement.innerHTML = roomName;

        headerElement.classList.add("chatroom-container-header");
        headerElement.append(roomNameElement);
        headerElement.append(newMessageCounterElement);

        return headerElement;
    }

    static #create(chatroomData) {
        const { roomId, name, latestMessage, latestMessageSendDate, numOfUnreadMessages } = chatroomData;

        const chatroomContainer = this.#createChatroomContainer(roomId);
        const contentContainer = document.createElement("div");
        const headerElement = this.#createHeaderElement(roomId, name, numOfUnreadMessages);
        const latestMessageElement = this.#createLatestMessageElement(roomId, latestMessage, latestMessageSendDate);
        const latestMessageDateElement = this.#createLatestMessageDateElement(roomId, latestMessageSendDate);

        contentContainer.classList.add("chatroom-content-container");

        contentContainer.append(latestMessageElement);
        contentContainer.append(latestMessageDateElement);

        chatroomContainer.append(headerElement);
        chatroomContainer.append(contentContainer);

        return chatroomContainer;
    }
}

export default ChatroomElement;