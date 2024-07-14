import RedirectPage from "../utils/RedirectPage.js";

class ChatroomListFetcher {
    static async fetchChatrooms() {
        return await fetch("/chatroomAndMember/all", {
            method: "GET",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                "Authorization": `Bearer ${localStorage.getItem("jwt")}`,
            },
        })
        .then(response => response.json())
        .then(data => {
            return data;
        })
        .catch(err => {
            RedirectPage.toSignInPage;
        });
    }
}

export default ChatroomListFetcher;