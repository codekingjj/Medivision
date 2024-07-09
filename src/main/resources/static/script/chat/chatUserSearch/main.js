import Fetch from "../utils/Fetch.js";
import UserElement from "./UserElement.js";
import RedirectPage from "../common/RedirectPage.js";

let pageNumber = 0;
let userCode;

window.onload = () => {
    init();

    //$("#chatListContainer").on("scroll", (e) => handleChatListScroll(e));
};

async function init() {
    userCode = await Fetch.getUserCode();
    populateUserList();
    loadEventListeners();
}

function clearUserList() {
    $(".user-list-container").empty();
}

function searchUser() {
    clearUserList();
    pageNumber = 0;
    populateUserList();
}

function loadEventListeners() {
    $("#btnToChatroomList").on("click", () => RedirectPage.toChatroomList());

    $("#btnUserSearch").on("click", (e) => {
        searchUser();
    });

    $("#inputUserSearch").on("keyup", (e) => {
        if (e.key !== "Enter")
            return;

        searchUser();
    });

    $("#btnNextPage").on("click", (e) => {
        populateUserList();
    });
}

async function populateUserList() {
    const data = await fetchUserList();
    console.log(data);

    const { chatUserSearchDtoList, lastPage } = data;

    const userElements = UserElement.getUsers(userCode, chatUserSearchDtoList);

    for (const userElement of userElements)
        $(".user-list-container").append(userElement);

    if (lastPage) {
        $("#btnNextPage").hide();
    } else {
        $("#btnNextPage").show();
    }
}

async function fetchUserList() {
    const searchQuery = $("#inputUserSearch").val();

    const bodyData = {
        "pageNumber": pageNumber++,
        "searchQuery": searchQuery,
    };

    const data = await fetch("/chat/userSearch", {
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
            console.log(err)
        });

    return data;
}