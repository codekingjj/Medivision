import ButtonChatPageToggle from "../pageElements/ButtonChatPageToggle.js";
import ChatRootPage from "../pageElements/ChatRootPage.js";
import ChatElement from "./ChatElement.js";
import ChatListContainer from "./ChatListContainer.js";
import ChatFetcher from "./ChatFetcher.js";
import Fetch from "../utils/Fetch.js";
import StompManager from "../websocket/StompManager.js";

class ChatroomPage {
    static #isLastPage = false;
    static #root = null;
    static #userCode;
    static #roomId;
    static #pageNumber = 0;

    static #resetPageProperties() {
        this.#isLastPage = false;
        this.#pageNumber = 0;
    }

    static render(roomId) {
        this.#root = $("<div>", {
            scroll: (e) => {
                this.#handleChatListScroll(e);
            }
        });

        this.#resetPageProperties();

        this.#roomId = roomId;

        const headerContainer = this.#createHeaderContainer();
        const chatContentContainer = this.#createChatContentContainer();
        const roomIdElement = $("<input>", {
            type: "hidden",
            id: "roomId",
            value: this.#roomId,
        });

        this.#root.append(headerContainer);
        this.#root.append(chatContentContainer);
        this.#root.append(roomIdElement);

        Fetch.getUserCode().then(userCode => {
            this.#userCode = userCode;
        }).then(() => {
            this.populateChatHistory();
        }).then(() => {
            this.saveLastVisitedDate();
        })

        return this.#root;
    }

    static createChatElementThenScrollToBottom(receivedData) {
        const chatContainer = ChatElement.create(this.#userCode, receivedData);
        ChatListContainer.append(chatContainer);
        this.#scrollToBottom();
    }

    static async #handleChatListScroll(e) {
        if (e.currentTarget.scrollTop === 0 && !this.#isLastPage) {
            const prevHeight = e.currentTarget.scrollHeight;

            await this.populateChatHistory();

            const element = document.getElementById("chatListContainer");

            this.#root.scrollTop(this.#root.prop("scrollHeight") - prevHeight);
        }
    }

    static #scrollToBottom() {
        this.#root.scrollTop(this.#root.prop('scrollHeight'));
    }

    static async populateChatHistory() {
        const data = await ChatFetcher.getChatList(this.#roomId, this.#pageNumber++);
        const { chatDtoList, lastPage } = data;

        this.#populateChat(chatDtoList);
        this.#scrollToBottom();

        this.#isLastPage = lastPage;
    }

    static #populateChat(chats) {
        for (const chat of chats) {
            const chatContainer = ChatElement.create(this.#userCode, chat);
            ChatListContainer.prepend(chatContainer);
        }
    }

    static #leaveChatroom() {
        ChatFetcher.leaveChatroom(this.#roomId, this.#userCode);
    }

    static async #isValidMessage(message) {
        return message && message.length > 0;
    }

    static async #sendMessage() {
        const message = $("#inputMessage").val();

        if (!this.#isValidMessage(message))
            return;

        const data = {
            roomId: this.#roomId,
            message: message,
            systemMessage: false,
            senderUserCode: this.#userCode,
            createDate: Date.now(),
        };

        StompManager.client.send("/chat", {}, JSON.stringify(data));

        $("#inputMessage").val("");
    }

    static async saveLastVisitedDate() {
        const bodyData = {
            roomId: this.#roomId,
            userCode: this.#userCode,
            lastVisitedDate: new Date(),
            createDate: "",
        };

        await ChatFetcher.saveLastVisitedDate(bodyData);
    }

    static async #handleGoToChatroomList() {
        await this.saveLastVisitedDate();
        StompManager.disconnct();
        ChatRootPage.render(ChatRootPage.PAGE_NAMES.CHAT_ROOM_LIST);
    }

    static #createHeaderContainer() {
        const header = $("<div>", {
            class: "chat-header",
        });
        
        const headerSubContainer = $("<div>", {
            class: "chat-header-sub-container",
        });

        const imageLeftArrow = $("<img>", {
            class: "img-left-arrow",
            src: "../../../assets/left-arrow.png",
        });

        const buttonToChatroomList = $("<button>", {
            class: "color-theme btn-chatroom-to-chatroom-list",
            id: "btnChatroomToChatroomList",
            click: () => {
                this.#handleGoToChatroomList();
            },
        });

        buttonToChatroomList.append(imageLeftArrow);

        const buttonChatroomLeave = $("<button>", {
            class: "btn-chatroom-leave",
            id: "btnChatroomLeave",
            text: "방 나가기",
            click: () => {
                this.#leaveChatroom();
            },
        });

        const headerRightSideContainer = $("<div>", {
            class: "chat-header-sub-container",
        });


        headerSubContainer.append(buttonToChatroomList);
        headerSubContainer.append(buttonChatroomLeave);
        headerRightSideContainer.append(ButtonChatPageToggle.get());

        header.append(headerSubContainer);
        header.append(headerRightSideContainer);

        return header;
    }

    static #createChatContentContainer() {
        const chatContentContainer = $("<div>", {
            class: "chat-content",
        });

        const chatListContainer = ChatListContainer.get();

        const chatInputContainer = $("<div>", {
            class: "chat-input-container",
        });

        const inputMessage = $("<input>", {
            type: "text",
            class: "chat-input-message",
            id: "inputMessage",
        });

        inputMessage.on("keyup", (e) => {
            if (e.key !== "Enter")
                return;

            this.#sendMessage();
        });

        const imageMessageSend = $("<img>", {
            class: "img-message-send",
            src: "../../../assets/paper-plane.png",
        });

        const buttonMessageSend = $("<button>", {
            class: "btn-message-send",
            id: "btnMessageSend",
            click: () => {
                this.#sendMessage();
            },
        });

        buttonMessageSend.append(imageMessageSend);

        chatInputContainer.append(inputMessage);
        chatInputContainer.append(buttonMessageSend);

        chatContentContainer.append(chatListContainer)
        chatContentContainer.append(chatInputContainer)

        return chatContentContainer;
    }
}

export default ChatroomPage;