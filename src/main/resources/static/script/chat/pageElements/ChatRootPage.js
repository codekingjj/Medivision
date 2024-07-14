import ChatroomPage from "../chatroom/ChatroomPage.js";
import ChatroomListPage from "../chatroomList/ChatroomListPage.js";
import ChatUserSearchPage from "../chatUserSearch/ChatUserSearchPage.js";

class ChatRootPage {
    static #currentPage;
    static #isOpen = false;
    static #rootElement = $(".sidebar-chat-root");
    static PAGE_NAMES = {
        CHAT_ROOM: "chatroom",
        CHAT_ROOM_LIST: "chatroomList",
        CHAT_USER_SEARCH: "chatUserSearch",
    };

    static getCurrentPageName() {
       return this.#currentPage;
    }

    static renderChatroom(roomId) {
        this.#currentPage = this.PAGE_NAMES.CHAT_ROOM;
        let pageElement = ChatroomPage.render(roomId);

        this.#rootElement.empty();
        this.#rootElement.append(pageElement);
    }

    static render(pageName) {
        this.#currentPage = pageName;
        let pageElement;

        switch (this.#currentPage) {
            case this.PAGE_NAMES.CHAT_ROOM:
                pageElement = ChatroomPage.render();
                break;
            case this.PAGE_NAMES.CHAT_ROOM_LIST:
                pageElement = ChatroomListPage.render();
                break;
            case this.PAGE_NAMES.CHAT_USER_SEARCH:
                pageElement = ChatUserSearchPage.render();
                break;
        }

        this.#rootElement.empty();
        this.#rootElement.append(pageElement);
    }

    static toggle() {
        const PAGE_WIDTH = "350px";
        const TOGGLE_TIME_INTERVAL = 400;
        this.#isOpen = !this.#isOpen;

        if (this.#isOpen) {
            this.#rootElement.show();

            this.#rootElement.animate({
                width: PAGE_WIDTH,
            }, TOGGLE_TIME_INTERVAL);
        } else {
            this.#rootElement.animate({
                width: "0",
            }, TOGGLE_TIME_INTERVAL).fadeToggle();
        }
    }
}

export default ChatRootPage;