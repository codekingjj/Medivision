import ChatPageHeader from "../pageElements/ChatPageHeader.js";
import ChatRootPage from "../pageElements/ChatRootPage.js";
import ButtonChatPageToggle from "../pageElements/ButtonChatPageToggle.js";
import StompManager from "../websocket/StompManager.js";

class Header {
    static get() {
        const header = ChatPageHeader.get();

        const searchSearchButtonContainer = ChatPageHeader.getSubContainer();
        searchSearchButtonContainer.append(this.#createUserSearchButton());

        const chatPageToggleButtonContainer = ChatPageHeader.getSubContainer();
        chatPageToggleButtonContainer.append(ButtonChatPageToggle.get());

        header.append(searchSearchButtonContainer);
        header.append(chatPageToggleButtonContainer);

        return header;
    }

    static #createUserSearchButton() {
        const image = $("<img>", {
            class: "img-add",
            src: "../../../assets/add.png",
        });

        const button = $("<button>", {
            class: "color-theme",
            click: function() {
                StompManager.disconnct();
                ChatRootPage.render(ChatRootPage.PAGE_NAMES.CHAT_USER_SEARCH);
            }
        });

        button.append(image);

        return button;
    }
}

export default Header;