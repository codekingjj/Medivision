import ChatRootPage from "./ChatRootPage.js";

class ButtonChatPageToggle {
    static get() {
        const image = $("<img>", {
            class: "img-right-toggle",
            src: "../../../assets/right-toggle.png",
        });

        const button = $("<button>", {
            class: "color-theme btn-chat-page-toggle",
            click: () => {
                ChatRootPage.toggle();
            }
        });

        button.append(image);

        return button;
    }
}

export default ButtonChatPageToggle;