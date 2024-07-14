import ChatUserSearchPage from "./ChatUserSearchPage.js";
import UserListContainerElement from "./UserListContainerElement.js";

class MainContainer {
    static get() {
        const mainContainer = $("<div>", {
            class: "chat-user-search-main",
        });

        const ButtonUserListNextpage = $("<button>", {
            id: "btnNextPage",
            text: "V",
            click: () => {
                ChatUserSearchPage.populateUserList();
            }
        });

        mainContainer.append(UserListContainerElement.get());
        mainContainer.append(ButtonUserListNextpage);

        return mainContainer;
    }
}

export default MainContainer;