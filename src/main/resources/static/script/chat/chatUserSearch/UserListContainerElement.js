class UserListContainerElement {
    static #instance = null;

    static get() {
        if (this.#instance === null)
            this.#instance = this.#createInstance();

        return this.#instance;
    }

    static clearUserList() {
        this.#instance.empty();
    }

    static #createInstance() {
        return $("<div>", {
            class: "chat-user-list-container",
        });
    }

    static append(element) {
        this.#instance.append(element);
    }
}

export default UserListContainerElement;