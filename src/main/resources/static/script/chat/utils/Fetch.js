class Fetch {
    static async getUserCode() {
        return fetch("/chat/userCode", {
            method: "GET",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                "Authorization": `Bearer ${localStorage.getItem("jwt")}`
            },
        })
        .then(response => {
            return response.json();
        })
        .then(data => {
            return data;
        })
        .catch(err => {
            window.location.href = "/auth/sign-in";
        });
    }
}

export default Fetch;