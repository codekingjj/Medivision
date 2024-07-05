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

    static #create(chatroom) {
        const { roomId, name, members, lastVisitedDate } = chatroom;

        const chatroomContainer = this.#createChatroomContainer(roomId);

        const content = document.createElement("span");
        const header = document.createElement("span");
        const memberElement = document.createElement("span");

        header.innerHTML = `${name} (ID: ${roomId})`;

        for (let i = 0; i < members.length; i++) {
            const member = members[i];

            memberElement.innerHTML += member.userName;

            if (i + 1 < members.length)
                memberElement.innerHTML += ", ";
        }

        chatroomContainer.append(header);
        chatroomContainer.append(content);
        chatroomContainer.append(memberElement);

        return chatroomContainer;
    }
}

export default ChatroomElement;