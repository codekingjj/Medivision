import ChatroomElement from "./ChatroomElement.js";
import ChatroomListFetcher from "./ChatroomListFetcher.js";
import ChatroomListContainer from "./ChatroomListContainer.js";
import ChatroomPage from "../chatroom/ChatroomPage.js";
import ChatRootPage from "../pageElements/ChatRootPage.js";
import Header from "./Header.js";
import StompManager from "../websocket/StompManager.js";

class ChatroomListPage {
    static render() {
        const root = $("<div>");
        const header = Header.get();

        root.append(header);
        root.append(ChatroomListContainer.get());

        this.#populateChatrooms();

        return root;
    }

    static async #populateChatrooms() {
        const chatrooms = await ChatroomListFetcher.fetchChatrooms();
        const chatroomElements = ChatroomElement.getChatrooms(chatrooms);

        for (const chatroomElement of chatroomElements)
            ChatroomListContainer.append(chatroomElement);

        this.#connectToStomp(chatrooms);
    }

    static #stompSubscribeCallbackFunc(messageData) {
        console.log("=== received (list page) ===")
        console.log(messageData);
        console.log("=== ======== ===");

        switch (ChatRootPage.getCurrentPageName()) {
            case ChatRootPage.PAGE_NAMES.CHAT_ROOM_LIST:
                ChatroomElement.update(messageData);
                break;
            case ChatRootPage.PAGE_NAMES.CHAT_ROOM:
                ChatroomPage.createChatElementThenScrollToBottom(messageData);
                break;
            default:
                break;
        }
    }

    static #connectToStomp(chatrooms) {
        const chatroomIds = chatrooms.map(chatroom => chatroom.roomId);

        StompManager.connectStompChatroomAll(chatroomIds, this.#stompSubscribeCallbackFunc);
    }
}

export default ChatroomListPage;