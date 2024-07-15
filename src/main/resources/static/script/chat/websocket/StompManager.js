class StompManager {
    static #WS_END_POINT = "/ws"
    static client;

    static connectStompChatroomAll(chatroomIds, subscribeCallbackFunc) {
        const sock = new SockJS(this.#WS_END_POINT);
        this.client = Stomp.over(sock);

       this.client.debug = null; // hide stomp connection info messages

        this.client.connect({}, options => {
            for (const chatroomId of chatroomIds) {
                this.client.subscribe(`/topic/chatroom/${chatroomId}`, (event) => {
                    subscribeCallbackFunc(JSON.parse(event.body));
                });
            }
        });
    }

    static disconnct() {
        this.client.disconnect(function () {
        });
    }
}

export default StompManager;