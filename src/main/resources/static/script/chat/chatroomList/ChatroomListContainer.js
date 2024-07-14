class ChatroomListContainer {
    static #instance = null;

    static #createInstance() {
        return $("<div>", {
            class: "chatroom-list-container",
        });
    }

    static get() {
        this.#instance = this.#createInstance();

        return this.#instance;
    }

    static append(element) {
        this.#instance.append(element);
    }
}

export default ChatroomListContainer;