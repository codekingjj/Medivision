import ChatRootPage from "../pageElements/ChatRootPage.js";

class UserElement {
    static getUsers(loggedInUserCode, users) {
        const userContainerList = users.map(user => {
            return this.#create(loggedInUserCode, user);
        });

        return userContainerList;
    }

    static #create(loggedInUserCode, user) {
        const { userCode, userId, userName } = user;
        const userContainer = this.#createUserContainer(loggedInUserCode, userCode);

        const content = $("<span>", {
            text: `${userName} (${userId})`,
        });

        userContainer.append(content);

        return userContainer;
    }

    static #createUserContainer(loggedInUserCode, userCode) {
        const userContainer = $("<div>", {
            class: "chat-user-container",
            click: () => {
                this.#addUsersToChatroom(loggedInUserCode, userCode);
            }
        });

        return userContainer;
    }

    static #addUsersToChatroom(loggedInUserCode, userCode) {
        const bodyData = {
            name: loggedInUserCode + ", " + userCode,
            creatorUserCode: loggedInUserCode,
            members: [
                { userCode: loggedInUserCode },
                { userCode: userCode },
            ],
        };

        fetch("/chatroomAndMember/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem("jwt")}`
            },
            body: JSON.stringify(bodyData),
        })
        .then(response => {
            if (response.ok) {
                ChatRootPage.render(ChatRootPage.PAGE_NAMES.CHAT_ROOM_LIST);
            }
        })
        .catch(err => {
            console.log(err);
        });
    }
}

export default UserElement;