class UserElement {
    static getUsers(loggedInUserCode, users) {
        const userContainerList = users.map(user => {
            return this.#create(loggedInUserCode, user);
        });

        return userContainerList;
    }

    static #createUserContainer(loggedInUserCode, userCode) {
        const userContainer = document.createElement("div");

        userContainer.classList.add("user-container");

        userContainer.addEventListener("click", () => {
            this.#addUsersToChatroom(loggedInUserCode, userCode);
        });

        return userContainer;
    }

    static #create(loggedInUserCode, user) {
        const { userCode, userName } = user;

        const userContainer = this.#createUserContainer(loggedInUserCode, userCode);

        const content = document.createElement("span");

        content.innerHTML = `${userName} (${userCode})`;

        userContainer.append(content);

        return userContainer;
    }

    static #addUsersToChatroom(loggedInUserCode, userCode) {
        console.log("my userCode: " + loggedInUserCode);
        console.log("adding userCode: " + userCode);

        const bodyData = {
            name: loggedInUserCode + ", " +userCode,
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
                if (response.ok)
                    window.location.href = "/chatroom";
            })
            .catch(err => {
                console.log(err);
            });
    }
}

export default UserElement;