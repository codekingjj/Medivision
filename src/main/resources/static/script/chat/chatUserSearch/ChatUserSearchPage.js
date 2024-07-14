import Header from "./Header.js";

import UserListContainerElement from "./UserListContainerElement.js";
import UserListFetcher from "./UserListFetcher.js";
import UserElement from "./UserElement.js";

import Fetch from "../utils/Fetch.js";
import MainContainer from "./MainContainer.js";

class ChatUserSearchPage {
    static #pageNumber = 0;
    static #userCode;
    static #buttonUserListNextPage = $("#btnNextPage");

    static getButtonNextPage() {
        return this.#buttonUserListNextPage;
    }

    static #resetPageProperties() {
        this.#pageNumber = 0;
    }

    static render() {
        const root = $("<div>");

        this.#resetPageProperties();

        const headerContainer = Header.get();
        const mainContainer = MainContainer.get();

        root.append(headerContainer);
        root.append(mainContainer);

        this.populateUserList();

        return root;
    }

    static searchUser() {
        UserListContainerElement.clearUserList();
        this.#pageNumber = 0;
        this.populateUserList();
    }

    static async populateUserList() {
        if (!this.#userCode)
            this.#userCode = await Fetch.getUserCode();

        const searchQuery = $("#inputUserSearch").val();
        const data = await UserListFetcher.fetchUserList(searchQuery, this.#pageNumber++);

        const { chatUserSearchDtoList, lastPage } = data;

        const userElements = UserElement.getUsers(this.#userCode, chatUserSearchDtoList);

        for (const userElement of userElements)
            UserListContainerElement.append(userElement);

        const buttonUserListNextPage = this.getButtonNextPage();

        if (lastPage) {
            buttonUserListNextPage.hide();
        } else {
            buttonUserListNextPage.show();
        }
    }
}

export default ChatUserSearchPage;