import ChatRootPage from "../pageElements/ChatRootPage.js";
import StompManager from "../websocket/StompManager.js";

class ChatFetcher {
    static async getChatList(roomId, pageNumber) {
        const bodyData = {
            "pageNumber": pageNumber,
        };

        return await fetch(`/chat/${roomId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                "Authorization": `Bearer ${localStorage.getItem("jwt")}`,
            },
            body: JSON.stringify(bodyData),
        })
        .then(response => {
            return response.json();
        })
        .then(data => {
            return data;
        })
        .catch(err => {
            console.log(err);
        });
    }

    static async leaveChatroom(roomId, userCode) {
        const bodyData = {
            roomId: roomId,
            userCode: userCode,
        };

        fetch(`/chatroomAndMember/delete`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem("jwt")}`
            },
            body: JSON.stringify(bodyData),
        })
        .then(response => {
            if (response.ok) {
                const data = {
                    roomId: roomId,
                    message: "",
                    systemMessage: true,
                    senderUserCode: userCode,
                    createDate: Date.now(),
                };

                ChatRootPage.render(ChatRootPage.PAGE_NAMES.CHAT_ROOM_LIST);
                StompManager.client.send("/chat", {}, JSON.stringify(data));
                StompManager.disconnct();
            }
        })
        .catch(err => {
            console.log(err);
        });
    }
}

export default ChatFetcher;