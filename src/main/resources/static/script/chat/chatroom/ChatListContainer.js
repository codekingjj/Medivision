class ChatListContainer {
    static #instance = null;

    static get() {
        this.#instance = this.#createInstance();

        return this.#instance;
    }

    static #createInstance() {
        return $("<div>", {
            class: "chat-list-container",
            id: "chatListContainer",
        });
    }

    static append(element) {
        this.#instance.append(element);
    }

    static prepend(element) {
        this.#instance.prepend(element);
    }
}

export default ChatListContainer;