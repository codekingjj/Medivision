class ChatPageHeader {
    static get() {
        return $("<div>", {
            class: "chatroom-list-header",
        });
    }

    static getSubContainer() {
        return $("<div>", {
            class: "chatroom-list-header-sub-container",
        });
    }
}

export default ChatPageHeader;