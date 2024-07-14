class StompManager {
    static #WS_END_POINT = "/ws"
    static client;

    static connectStomp(roomId, subscribeCallbackFunc) {
        const sock = new SockJS(this.#WS_END_POINT);
        this.client = Stomp.over(sock);

        this.client.debug = null; // hide stomp connection info messages

        this.client.connect({}, options => {
            console.log("stomp conntected");

            this.client.subscribe(`/topic/chatroom/${roomId}`, (event) => {
                subscribeCallbackFunc(JSON.parse(event.body));
            });
        });
    }

    static connectStompChatroomAll(chatroomIds, subscribeCallbackFunc) {
        const sock = new SockJS(this.#WS_END_POINT);
        this.client = Stomp.over(sock);

        //this.client.debug = null; // hide stomp connection info messages

        this.client.connect({}, options => {
            console.log("stomp conntected");

            for (const chatroomId of chatroomIds) {
                this.client.subscribe(`/topic/chatroom/${chatroomId}`, (event) => {
                    subscribeCallbackFunc(JSON.parse(event.body));
                });
            }
        });
    }

    static disconnct() {
        this.client.disconnect(function () {
            console.log('Disconnected');
        });
    }
}

export default StompManager;