class ChatPage {
    static pops = [];

    static popup() {
        var url = `/chatroom`;
        var name = "targetReport";
        var option = "width=800, height=500, left=100, top=50, location=no";

        const pop = window.open(url, name, option);
        this.pops.add(pop);
    }
}

window.onload = () => {
   $("#btnChatPageOpen").on("click", () => {
       ChatPage.popup();
   });
}