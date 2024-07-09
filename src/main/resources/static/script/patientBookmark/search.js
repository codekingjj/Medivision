window.onload = () => {
    $("#btnToggleAddPatientBookmarkCheckboxes").on("click", () => {
        toggleAddPatientBookmarkCheckboxes();
    });

    $("#btnAddCheckedStudiesToPatientBookmark").on("click", () => {
        addCheckedStudiesToPatientBookmark();
    });
}

function toggleAddPatientBookmarkCheckboxes(e) {
    if ($(".table-th-patientBookmark").is(":hidden")) {
        $("#btnToggleAddPatientBookmarkCheckboxes").html("담당 환자 추가 취소");

        $(".table-th-patientBookmark").show();
        $(".td-patientBookmark-checkbox-container").show();
        $("#btnAddCheckedStudiesToPatientBookmark").show();

    } else {
        $("#btnToggleAddPatientBookmarkCheckboxes").html("담당 환자 추가");

        $(".table-th-patientBookmark").hide();
        $(".td-patientBookmark-checkbox-container").hide();
        $("#btnAddCheckedStudiesToPatientBookmark").hide();
    }
}

function addCheckedStudiesToPatientBookmark() {
    const checkedCheckboxes = $(".checkbox-patientBookmark:checked");
    const bodyData = { pids: getIdsOfCheckedCheckboxes(checkedCheckboxes) };

    console.log(bodyData);

    fetch("/patientBookmark/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
        body: JSON.stringify(bodyData)
    })
    .then(response => {
        if (response.ok)
            window.location.href = "/patientBookmark";
    })
    .catch(err => {
        window.location.href = "/auth/sign-in";
    });
}

function getIdsOfCheckedCheckboxes(checkboxes) {
    const checkedCheckboxIds = [];

    console.log(checkedCheckboxIds);

    checkboxes.each(function() {
        checkedCheckboxIds.push(this.id);
    });

    return checkedCheckboxIds;
}
