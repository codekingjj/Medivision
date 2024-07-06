class UserElement {
    static getUsers(users) {
        const userContainerList = users.map(user => {
            return this.#create(user);
        });

        return userContainerList;
    }

    static #createUserContainer(userCode) {
        const userContainer = document.createElement("div");

        userContainer.classList.add("user-container");

        userContainer.addEventListener("click", () => {
            //location.href = `/chatroom/${userCode}`;
            console.log(userCode);
        });

        return userContainer;
    }

    static #create(user) {
        const { userCode, userName } = user;

        const userContainer = this.#createUserContainer(userCode);

        const content = document.createElement("span");

        content.innerHTML = `${userName} (${userCode})`;

        userContainer.append(content);

        return userContainer;
    }
}

export default UserElement;