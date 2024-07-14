import ChatRootPage from "./pageElements/ChatRootPage.js";

ChatRootPage.render(ChatRootPage.PAGE_NAMES.CHAT_ROOM_LIST);

$("#btnChatPageOpen").on("click", () => {
    ChatRootPage.toggle();
});