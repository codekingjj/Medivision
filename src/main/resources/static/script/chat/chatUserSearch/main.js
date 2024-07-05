import UserElement from "./UserElement.js";

let isLastPage = false;
let pageNumber = 0;

window.onload = () => {
    populateUserList();

    //$("#chatListContainer").on("scroll", (e) => handleChatListScroll(e));
    $("#btnUserSearch").on("click", (e) => {
        populateUserList();
    })
};

async function populateUserList() {
    const data = await fetchUserList();
    console.log(data);

    const { chatUserSearchDtoList, lastPage } = data;

    const userElements = UserElement.getUsers(chatUserSearchDtoList);

    for (const userElement of userElements)
        $(".user-list-container").append(userElement);
}

async function fetchUserList() {
    const searchQuery = $("#inputUserSearch").val();

    const bodyData = {
        "pageNumber": pageNumber++,
        "searchQuery": searchQuery,
    };

    const data = await fetch("/chatUserSearch", {
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
        .catch(err => err);

    return data;
}