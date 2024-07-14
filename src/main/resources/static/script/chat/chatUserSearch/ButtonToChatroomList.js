import ChatRootPage from "../pageElements/ChatRootPage.js";

class ButtonToChatroomList {
    static get() {
        const image = $("<img>", {
            class: "img-left-arrow",
            src: "../../../assets/left-arrow.png",
        });

        const button = $("<button>", {
            class: "color-theme",
            id: "btnToChatroomList",
            click: function() {
                ChatRootPage.render(ChatRootPage.PAGE_NAMES.CHAT_ROOM_LIST);
            },
        });

        button.append(image);

        return button;
    }
}

export default ButtonToChatroomList;