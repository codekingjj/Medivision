import ChatPageHeader from "../pageElements/ChatPageHeader.js";
import ChatUserSearchPage from "./ChatUserSearchPage.js";
import ButtonChatPageToggle from "../pageElements/ButtonChatPageToggle.js";
import ButtonToChatroomList from "./ButtonToChatroomList.js";

class Header {
    static get() {
        const header = $("<div>", {
            class: "chat-user-search-header",
        });

        const chatroomListButtonContainer = this.#getSubContainer();
        chatroomListButtonContainer.append(ButtonToChatroomList.get());

        const searchContainer = this.#createSearchSection();

        const chatPageToggleButtonContainer = this.#getSubContainer();
        chatPageToggleButtonContainer.append(ButtonChatPageToggle.get());

        header.append(chatroomListButtonContainer);
        header.append(searchContainer);
        header.append(chatPageToggleButtonContainer);

        return header;
    }

    static #getSubContainer() {
        return $("<div>", {
            class: "chat-user-search-header-sub-container",
        });
    }

    static #getInputUserSearch() {
        return $("<input>", {
            type: "text",
            id: "inputUserSearch",
            keyup: function(e) {
                if (e.key !== "Enter")
                    return;

                ChatUserSearchPage.searchUser();
            },
        });
    }

    static #getButtonUserSearch() {
        return $("<button>", {
            id: "btnUserSearch",
            text: "검색",
            click: function() {
                ChatUserSearchPage.searchUser();
            },
        });
    }

    static #createSearchSection() {
        const searchContainer = ChatPageHeader.getSubContainer();
        const inputUserSearch = this.#getInputUserSearch();
        const buttonUserSearch = this.#getButtonUserSearch();

        searchContainer.append(inputUserSearch);
        searchContainer.append(buttonUserSearch);

        return searchContainer;
    }
}

export default Header;