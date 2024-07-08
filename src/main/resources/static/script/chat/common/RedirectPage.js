class RedirectPage {
    static toChatroomList() {
        window.location.href = "/chatroom";
    }

    static toSignInPage() {
        window.location.href = "/auth/sign-in";
    }
}

export default RedirectPage;