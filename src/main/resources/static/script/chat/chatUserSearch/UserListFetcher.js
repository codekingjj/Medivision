class UserListFetcher {
    static async fetchUserList(searchQuery, pageNumber) {
        const bodyData = {
            "pageNumber": pageNumber,
            "searchQuery": searchQuery,
        };

        return await fetch("/chat/userSearch", {
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
            alert(err);
        });
    }
}

export default UserListFetcher;