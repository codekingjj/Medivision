window.onload = () => {
    console.log("patientBookmark.js loaded");

    populatePatients();

    $("#btnDeleteCheckedPatients").on("click", () => {
        deleteCheckedPatients();
    });

    $("#search-form").submit(e => {
        e.preventDefault();
        populateSearchedPatients();
    });
}

function createPatientElementRow(patient) {
    const { pid, pname } = patient;

    const rowElement =
        '<tr class="tr-area" >' +
            '<td class="td-patientBookmark-checkbox-container">' +
                `<input type="checkbox" class="checkbox-patientBookmark" id=${pid} />` +
            '</td>' +
            '<td>' + pid + '</td>' +
            '<td>' + pname + '</td>' +
        '</tr>';

    return rowElement;
}

function appendPatientsToTableBody(patients) {
    for (const patient of patients) {
        const patientRow = createPatientElementRow(patient);

        $(".results-section tbody").append(patientRow);
    }
}

async function populatePatients() {
    const patients = await fetchPatients();

    $(".results-section tbody").empty();
    appendPatientsToTableBody(patients);
}

async function populateSearchedPatients() {
    const patients = await fetchPatientsBySearch();

    $(".results-section tbody").empty();
    appendPatientsToTableBody(patients);
}

async function fetchPatientsBySearch() {
    const bodyData = {
        pid: $("#pid").val(),
        pname: $("#pname").val(),
    };

    return await fetch("/patientBookmark/search", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
        body: JSON.stringify(bodyData),
    })
        .then(response => {
            return response.json();
        })
        .then(data => {
            console.log(data);
            return data;
        })
        .catch(err => {
            window.location.href = "/auth/sign-in";
        });
}

async function fetchPatients() {
    return await fetch("/patientBookmark/list", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
    })
    .then(response => {
        return response.json();
    })
    .then(data => {
        console.log(data);
        return data;
    })
    .catch(err => {
        window.location.href = "/auth/sign-in";
    });
}

function deleteCheckedPatients() {
    const checkedCheckboxes = $(".checkbox-patientBookmark:checked");

    if (checkedCheckboxes.length === 0) {
        alert("선택한 담당 환자가 없습니다.");
        return;
    }

    const bodyData = { pids: getIdsOfCheckedCheckboxes(checkedCheckboxes) };

    fetch("/patientBookmark/delete", {
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

    checkboxes.each(function() {
        checkedCheckboxIds.push(this.id);
    });

    return checkedCheckboxIds;
}